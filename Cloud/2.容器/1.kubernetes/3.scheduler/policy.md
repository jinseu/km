### Taint和Toleration

taint 和 toleration 相互配合，可以用来避免 pod 被分配到不合适的节点上。每个节点上都可以应用一个或多个 taint ，这表示对于那些不能容忍这些 taint 的 pod，是不会被该节点接受的。如果将 toleration 应用于 pod 上，则表示这些 pod 可以（但不要求）被调度到具有匹配 taint 的节点上。

一个taint 由以下三部分组成

key=value:effect



CheckNodeUnschedulablePredicate

https://blog.frognew.com/2018/05/taint-and-toleration.html
https://kubernetes.io/zh/docs/concepts/configuration/taint-and-toleration/
