## 简介

### 基本介绍

ZooKeeper: A Distributed Coordination Service for Distributed Applications

ZooKeeper集群的基本结构如下图所示，集群由多个节点组成，多个节点根据Paxos协议选举中Leader节点，然后其他节点自动变为Follower节点，Leader和Follower之间通过Paxos协议保持同步，Client可以连接集群中的任一节点。

![zkservice](./img/zkservice.jpg)

### 数据模型

ZooKeeper 的数据模型是一个文件系统的树状结构，每一个节点都用一个路径来标识。这一点和`Etcd V3`是不一样的，`Etcd V3`中纯粹是一个KV结构，不同Node之间是没有任何关系的。当然由于`Etcd V3`提供了按照Key前缀获取节点的方式，所以可以在客户端模拟类似ZK `get children`的操作。

![zknode](./img/zknamespace.jpg)

#### 持久化节点和临时（ephemeral）节点

### API接口

ZooKeeper的API接口相对比较简单，基本操作包括

- create: 在树结构中创建节点
- delete: 在树结构中删除节点 
- exists: 检查制定路径的节点是否存在
- get: 获取节点对应的数据
- set: 设置节点对应的数据
- get children: 获取某个节点的子节点列表
- sync: 等待数据同步

这里主要要解释以下sync，sync的目的是强制client当前连接着的ZooKeeper服务器，和ZooKeeper的Leader节点同步（sync）一下数据。sync请求会从client传递给client链接的ZooKeeper服务器，然后由该服务器传递给ZooKeeper集群的Leader节点。当Leader收到一个sync请求时，如果leader当前没有待commit的决议，那么leader会立即发送一个Leader.SYNC消息给follower。否则，leader会等到当前最后一个待commit的决议完成后，再发送Leader.SYNC消息给Follower。由于leader和follower之间的消息通信，是严格按顺序来发送的（TCP保证），因此，当follower接收到Leader.SYNC消息时，说明follower也一定接收到了leader之前（在leader接收到sync请求之前）发送的所有提案或者commit消息。这样，就可以确保follower和leader是同步的了。

此外，ZooKeeper还支持Watch操作，客户端可以Watch一个节点（包括子节点）的变化，客户端会在节点变化时收到通知，但是在收到通知之后Watch会被删除，客户端如果要继续Watch，需要重新发起请求。