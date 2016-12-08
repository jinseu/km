## celery

celery本质上是一个分布式的任务队列。从Broker（中间人）处读取任务，并进行处理，然后将结果写入backend。

相比于其他任务队列，celery具有简单，高可用，快速，灵活的特点。

### celery起步

### broker

### backend

### 任务状态

res.state
res.failed()
res.ready()
res.successfule()
```
PENDING -> STARTED -> SUCCESS
```

### celery特性说明

#### Concurrency

#### celery Routing

#### Monitoring and Management

常用的celery监控命令

celery status

### celery源码解读