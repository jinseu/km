### cgroup

#### 1. 简介

1. cgroup 即 Control Groups，是linux 内核的一个特性，可以通过cgroup来控制资源在不同任务（进程）间的分配。
2. cgroup 是一个分层结构，类似于一个树，可以通过`/sys/fs/cgroup`这个特殊文件系统来操作cgroup
3. cgroup 会被划分为类似`/sys/fs/cgroup/<restype>/<userclass>`结构，restype是资源类型，userclass


#### 2. 实现原理