## celery

版本：celery 4.1.0

celery本质上是一个分布式的任务队列。从Broker（中间人）处读取任务，并进行处理，然后将结果写入backend。

相比于其他任务队列，celery具有简单，高可用，快速，灵活的特点。

### 1.模块加载与配置


### 2.Celery

首先会初始化一个app.base.Celery对象

然后在调用app.task的时候，会根据以app.task中指定的函数，初始化一个celery.local.PromiseProxy对象

select_queues

调用了amqp.queues.select(queues)

work_main

调用了celery.bin.worker:worker 类的execute_from_commandline方法。

初始化的时候会_register_app，_register_app本质上就是将本次的Celery对象放入一个weakset中。

cached_property的用法

instantiate 的用法

### 3.发送任务

发送任务时会调用Celery的send_task方法，该方法会返回一个AsyncResult对象。

在send_taks方法中的流程如下：
1. 查找路由
2. 调用amqp.create_task_message来构造一个message。
3. 构造一个Producer， P
4. backend.on_task_call(P, task_id)
5. amqp.send_task_message



res.state
res.failed()
res.ready()
res.successfule()
```
PENDING -> STARTED -> SUCCESS
```

#### AMQP

#### 任务注册

TaskRegistry类

app.task

shared

lazy

filter

### celery特性说明

#### Concurrency

#### celery Routing

#### Monitoring and Management

常用的celery监控命令

celery status

### Python 2&3兼容性

vine 包 python_2_unicode_compatible

### kombu库

### 参考资料

> https://github.com/celery/billiard
> http://www.celeryproject.org/