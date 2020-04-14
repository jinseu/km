## kubelet


### 

### 垃圾收集

kubelet 的垃圾收集包含两个部分

1. 未使用的image的清理，清理逻辑五分钟运行一次
2. 未使用的container的清理，清理逻辑1分钟运行一次

#### Image Collection

在Kubernetes 借助于cadvisor通过ImageManager来管理image的生命周期。

镜像垃圾回收策略只考虑两个因素：HighThresholdPercent 和 LowThresholdPercent。在磁盘使用率超过上限阈值（HighThresholdPercent）将触发垃圾回收。垃圾回收将删除最近最少使用的镜像，直到磁盘使用率满足下限阈值（LowThresholdPercent）

kubernetes-1.14.8/pkg/kubelet/images/image_gc_manager.go

#### Container Collection

> https://kubernetes.io/docs/concepts/cluster-administration/kubelet-garbage-collection/

### 启动流程

1. 解析参数配置
2. 创建kubeServer和kubeDeps 实例
3. 创建kubelet对象
4. 启动kubelet
 1. k.Run(podCfg.Updates())
 2. k.ListenAndServe
5. 根据设置启动健康检查端口


### FAQ

1. Dynamic kubelet configuration

#### 2. Kubelet Dependencies

kubelet Dependencies 简单来说所有依赖服务的客户端，工具的集合。官方解释如下

> Dependencies is a bin for things we might consider "injected dependencies" -- objects constructed at runtime that are necessary for running the Kubelet. This is a temporary solution for grouping these objects while we figure out a more comprehensive dependency injection story for the Kubelet.

Dependencies 中包含有

1. KubeClient 以及对应配置
2. EventClient 以及对应配置
3. HeartbeatClient 以及对应配置
4. CAdvisorInterface
5. ContainerManager


2. Docker Shim