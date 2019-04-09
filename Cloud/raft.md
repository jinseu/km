### RAFT 协议

RAFT 协议可以看做由两部分组成:

1. Leader Election
2. Log Replication

#### 复制状态机

应用于实际系统的一致性算法一般有以下特征:
1. 安全性，即从来不会返回错误的结果。
2. 高可用性，只要集群中的大部分机器能运行，可以互相通信并且可以和客户端通信，这个集群就可用。
3. 不依赖时序保证一致性，时钟错误和极端情况下的消息延迟在最坏的情况下才会引起可用性问题。
4. 通常情况下，一条命令能够尽可能快的在大多数节点做出响应时完成，一部分慢的机器不会影响系统的整体性能。

#### Leader Election
 
1. node 有三种状态，Follower，Candidate, Leader
2. 所有节点在初始化时，都在Follower状态
3. 在election timeout时间内，如果Follower状态没有感知到来自Leader的消息，那么进入Candidate状态，此处election timeout是随机值在 150ms ~ 300ms之间。
4. Candidate 会vote它自己，然后向其他节点请求votes（Request Vote Message)
5. 如果收到的节点此前没有vote 其他节点，就回复Candidate votes，同时重置election timeout
6. Candidate在接收到超过半数的节点reply votes后会变为Leader。如果没有重新进入follower状态 2.
7. Leader 会周期性的发送Append Entries，作为心跳
8. Followers 会回答Append Entries，并重置election timeout。
9. 7-8 两步会一直持续直到Follower停止接收心跳或者变为Candidate
10. 在leader宕机后，由于Follower在election timeout时间内收不到来自Leader的消息，

在leader election完成之后，对etcd 集群的更改都将通过leader来完成。

#### Log Replication

1. 每一个更改作为一个entry，加入leader 节点的log
2. log entry 并不马上提交，会将log entry(也是心跳)的副本发送到其他follower 节点
3. leader会等待直到大多数节点收到entry
4. leader节点会提交entry，改变待更改的值。
5. 返回请求给客户端，表示操作成功
5. 通知followers 提交entry


#### go-raft

