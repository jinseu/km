## 调度策略

### Taint和Toleration

taint 和 toleration 相互配合，可以用来避免 pod 被分配到不合适的节点上。每个节点上都可以应用一个或多个 taint ，这表示对于那些不能容忍这些 taint 的 pod，是不会被该节点接受的。如果将 toleration 应用于 pod 上，则表示这些 pod 可以（但不要求）被调度到具有匹配 taint 的节点上。

#### Taint

Taint 的定义如下

```
// The node this Taint is attached to has the "effect" on
// any pod that does not tolerate the Taint.
type Taint struct {
	// Required. The taint key to be applied to a node.
	Key string `json:"key" protobuf:"bytes,1,opt,name=key"`
	// Required. The taint value corresponding to the taint key.
	// +optional
	Value string `json:"value,omitempty" protobuf:"bytes,2,opt,name=value"`
	// Required. The effect of the taint on pods
	// that do not tolerate the taint.
	// Valid effects are NoSchedule, PreferNoSchedule and NoExecute.
	Effect TaintEffect `json:"effect" protobuf:"bytes,3,opt,name=effect,casttype=TaintEffect"`
	// TimeAdded represents the time at which the taint was added.
	// It is only written for NoExecute taints.
	// +optional
	TimeAdded *metav1.Time `json:"timeAdded,omitempty" protobuf:"bytes,4,opt,name=timeAdded"`
}

type TaintEffect string

const (
	// Do not allow new pods to schedule onto the node unless they tolerate the taint,
	// but allow all pods submitted to Kubelet without going through the scheduler
	// to start, and allow all already-running pods to continue running.
	// Enforced by the scheduler.
	TaintEffectNoSchedule TaintEffect = "NoSchedule"
	// Like TaintEffectNoSchedule, but the scheduler tries not to schedule
	// new pods onto the node, rather than prohibiting new pods from scheduling
	// onto the node entirely. Enforced by the scheduler.
	TaintEffectPreferNoSchedule TaintEffect = "PreferNoSchedule"
	// NOT YET IMPLEMENTED. TODO: Uncomment field once it is implemented.
	// Like TaintEffectNoSchedule, but additionally do not allow pods submitted to
	// Kubelet without going through the scheduler to start.
	// Enforced by Kubelet and the scheduler.
	// TaintEffectNoScheduleNoAdmit TaintEffect = "NoScheduleNoAdmit"

	// Evict any already-running pods that do not tolerate the taint.
	// Currently enforced by NodeController.
	TaintEffectNoExecute TaintEffect = "NoExecute"
)
```

其中key和value作为标签，value可以为空，effect描述taint的作用。当前taint effect支持如下三个选项：

- NoSchedule：表示k8s将不会将Pod调度到具有该taint所在的Node上
- PreferNoSchedule：表示k8s将尽量避免将Pod调度到具有该taint所在的Node上
- NoExecute：表示k8s将不会将Pod调度到具有该taint的Node上，同时会将Node上已经存在的Pod驱逐出去

#### Toleration

```
// The pod this Toleration is attached to tolerates any taint that matches
// the triple <key,value,effect> using the matching operator <operator>.
type Toleration struct {
	// Key is the taint key that the toleration applies to. Empty means match all taint keys.
	// If the key is empty, operator must be Exists; this combination means to match all values and all keys.
	// +optional
	Key string `json:"key,omitempty" protobuf:"bytes,1,opt,name=key"`
	// Operator represents a key's relationship to the value.
	// Valid operators are Exists and Equal. Defaults to Equal.
	// Exists is equivalent to wildcard for value, so that a pod can
	// tolerate all taints of a particular category.
	// +optional
	Operator TolerationOperator `json:"operator,omitempty" protobuf:"bytes,2,opt,name=operator,casttype=TolerationOperator"`
	// Value is the taint value the toleration matches to.
	// If the operator is Exists, the value should be empty, otherwise just a regular string.
	// +optional
	Value string `json:"value,omitempty" protobuf:"bytes,3,opt,name=value"`
	// Effect indicates the taint effect to match. Empty means match all taint effects.
	// When specified, allowed values are NoSchedule, PreferNoSchedule and NoExecute.
	// +optional
	Effect TaintEffect `json:"effect,omitempty" protobuf:"bytes,4,opt,name=effect,casttype=TaintEffect"`
	// TolerationSeconds represents the period of time the toleration (which must be
	// of effect NoExecute, otherwise this field is ignored) tolerates the taint. By default,
	// it is not set, which means tolerate the taint forever (do not evict). Zero and
	// negative values will be treated as 0 (evict immediately) by the system.
	// +optional
	TolerationSeconds *int64 `json:"tolerationSeconds,omitempty" protobuf:"varint,5,opt,name=tolerationSeconds"`
}

// A toleration operator is the set of operators that can be used in a toleration.
type TolerationOperator string

const (
	TolerationOpExists TolerationOperator = "Exists"
	TolerationOpEqual  TolerationOperator = "Equal"
)
```

其中，key，value，effect的含义和Taint是相同的。需要注意


1. operator的值为Exists将会忽略value值
2. TolerationSeconds用于描述当Pod被驱逐前可以在Pod上继续保留运行的时间，只会对NoExecute effect 才会生效

#### 具体调度实现

```
// PodToleratesNodeTaints checks if a pod tolerations can tolerate the node taints
func PodToleratesNodeTaints(pod *v1.Pod, meta PredicateMetadata, nodeInfo *schedulernodeinfo.NodeInfo) (bool, []PredicateFailureReason, error) {
	if nodeInfo == nil || nodeInfo.Node() == nil {
		return false, []PredicateFailureReason{ErrNodeUnknownCondition}, nil
	}

	return podToleratesNodeTaints(pod, nodeInfo, func(t *v1.Taint) bool {
		// PodToleratesNodeTaints is only interested in NoSchedule and NoExecute taints.
		return t.Effect == v1.TaintEffectNoSchedule || t.Effect == v1.TaintEffectNoExecute
	})
}

// PodToleratesNodeNoExecuteTaints checks if a pod tolerations can tolerate the node's NoExecute taints
func PodToleratesNodeNoExecuteTaints(pod *v1.Pod, meta PredicateMetadata, nodeInfo *schedulernodeinfo.NodeInfo) (bool, []PredicateFailureReason, error) {
	return podToleratesNodeTaints(pod, nodeInfo, func(t *v1.Taint) bool {
		return t.Effect == v1.TaintEffectNoExecute
	})
}

func podToleratesNodeTaints(pod *v1.Pod, nodeInfo *schedulernodeinfo.NodeInfo, filter func(t *v1.Taint) bool) (bool, []PredicateFailureReason, error) {
	taints, err := nodeInfo.Taints()
	if err != nil {
		return false, nil, err
	}

	if v1helper.TolerationsTolerateTaintsWithFilter(pod.Spec.Tolerations, taints, filter) {
		return true, nil, nil
	}
	return false, []PredicateFailureReason{ErrTaintsTolerationsNotMatch}, nil
}
```

其中PodToleratesNodeTaints 是默认的调度筛选逻辑之一，通过调用`v1helper.TolerationsTolerateTaintsWithFilter` 来检查pod是否可以tolerate待过滤Node的Taint。在`v1helper.TolerationsTolerateTaintsWithFilter` 会通过`filter`参数对taints进行过滤，只检查过滤后的taint，以尽可能提高效率。

因为此处只是过滤，所以只检查了pod是否可以tolerate NoSchedule和NoExecute的taint。
