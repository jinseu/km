## celery

版本：celery 4.1.0

celery本质上是一个分布式的任务队列。从Broker（中间人）处读取任务，并进行处理，然后将结果写入backend。相比于其他任务队列，celery具有简单，高可用，快速，灵活的特点。默认情况下，celery一般使用Rabbitmq作为Broker。

### AMQP

#### 基本概念

- Producer：发送消息方，一般用P表示。
- Queue:消息队列。相当于邮箱，本质上是一个无大小限制的缓冲区。消息队列可以接受多个生产者的消息，多个消费者也可以共享一个消息队列。
- Consumer:消费者。一个等待接收消息的程序。一般用C表示。
- Exchange：接收消息，转发消息到绑定的队列上，指定消息按什么规则，路由到哪个队列。
- Binding:绑定，它的作用就是把 Exchange 和 Queue 按照路由规则绑定起来。
- RoutingKey:路由关键字，Exchange 根据这个关键字进行消息投递。
- Channel:消息通道，在客户端的每个连接里可建立多个 Channel，每个 channel 代表一个会话。

exchange的类型：

1. Direct exchange 完全根据 key 进行投递，只有 key 与绑定时的 routing key 完全一致的消息才会收到消息
2. Fanount exchange 完全不关心 key，直接采取广播的方式进行消息投递，与该交换机绑定的所有队列都会收到消息
3. Topic exchange 会根据 key 进行模式匹配然后进行投递，与设置的 routing key 匹配上的队列才能收到消息
4. Header exchange 使用消息头代替 routing key 作为关键字进行路由，不过在实际应用过程中这种类型的 exchange 使用较少


在消息发送，并不是直接发送到queue，而是先发送到exchange，然后再由exchange发送到queue中。

RabbitMQ 支持消息的持久化，即将消息数据持久化到磁盘上，如果消息服务器中途断开，下次开启会将持久化的消息重新发送，消息队列持久化需要保证 exchange（指定 durable=1）、queue（指定 durable=1）和消息（delivery_mode=2）3 个部分都是持久化。

#### Topic

binding_key和routing key的形式一样。Topic exchange会将一个附带特定的routing key的消息将会被转发到与之匹配的binding key对应的队列中。需要注意的是：关于绑定键有两种特殊的情况:

`*`:可以匹配一个标识符。
`#`:可以匹配0个或多个标识符。


#### AMQP

#### 任务注册

TaskRegistry类

app.task

shared

lazy

filter

### celery特性说明


#### 调用任务

**调用方式**

任务的调用方式有以下几种：
1. apply_async
2. delay  发送任务消息的简写，不支持执行选项
3. 直接调用任务对象

**Linking**

可以为分别任务指定callbacks/errbacks

```
add.apply_async((2, 2), link=add.s(16), link_error=error_handler.s())
```

同时link和link_error 可以在一个列表中声明

```
add.apply_async((2, 2), link=[add.s(16), other_task.s()])
```

**状态变更**

Celery 通过设置 setting_on_message 回调支持捕获所有状态变更。

例如对于一个长时间运行的任务，可以这样做

```
@app.task(bind=True)
def hello(self, a, b):
    time.sleep(1)
    self.update_state(state="PROGRESS", meta={'progress': 50})
    time.sleep(1)
    self.update_state(state="PROGRESS", meta={'progress': 90})
    time.sleep(1)
    return 'hello world: %i' % (a+b)
def on_raw_message(body):
    print(body)
```

任务发送端

```
r = hello.apply_async()
print(r.get(on_message=on_raw_message, propagate=False))
Will generate output like this:
```

执行结果

```
{'task_id': '5660d3a3-92b8-40df-8ccc-33a5d1d680d7',
 'result': {'progress': 50},
 'children': [],
 'status': 'PROGRESS',
 'traceback': None}
{'task_id': '5660d3a3-92b8-40df-8ccc-33a5d1d680d7',
 'result': {'progress': 90},
 'children': [],
 'status': 'PROGRESS',
 'traceback': None}
{'task_id': '5660d3a3-92b8-40df-8ccc-33a5d1d680d7',
 'result': 'hello world: 10',
 'children': [],
 'status': 'SUCCESS',
 'traceback': None}
hello world: 10
```

**ETA Countdown**

ETA（估计到达时间）使你可以声明任务将被执行的最早时间。以后，countdown 则是一种简便的方式。用来设置ETA 距离目前的时间（单位为s）。

设置ETA 仅仅保证，执行时间在给定的时间之后，但并不是准确的时间。可能由于消息队列的阻塞和网络延迟导致一定的延迟。

eta 必须是一个 datetime 对象，用来声明一个精确的日期和时间（包含毫秒精度，以及时区信息
```
result = add.apply_async((2, 2), countdown=3)
result.get()   # this takes at least 3 seconds to return

from datetime import datetime, timedelta

tomorrow = datetime.utcnow() + timedelta(days=1)
add.apply_async((2, 2), eta=tomorrow)
```

**Expiration**

在调用任务的时候，可以指定expires选项，来指定任务失效的的时间。worker收到失效的任务后，会返回TaskRevokedError。

**Message Sending Retry**

当链接失败，celery 会重试发送任务消息，并且重试行为可以设置 - 比如重试的频率，或者最大重试次数 - 或者禁用所有。

禁用消息发送重试，你可以设置重试的执行选项为 False:

``` 
add.apply_async((2, 2), retry=False)
```

**Retry Policy**

具体的，重试策略可以设置

1. max_retries 最大重试次数，None 值意味着一直重试 
默认重试3次
2. interval_start 定义首次重试间隔的秒数（浮点数或者整数）。默认是0（首次重试会立即进行）
3. interval_step 每进行一次重试，这个值会加到重试延迟里（浮点数或者整数）。默认是0.2。
4. interval_max  重试之间间隔的最大秒数

**Connection Error Handling**

当你发送一个任务消息，而消息传输链接丢失了，或者链接不能被初始化了，一个 OperationError


**Serializers**

内建的序列化器有 JSON, pickle, YAML 以及 msgpack。可以用serializer参数指定。

**Compression**

Celery 使用 gzip或者 bzip2 压缩消息。你也可以创建自己的压缩模式，并注册到 Kombu 压缩模式注册表。使用compression参数指定。

**Connections**

celery默认支持连接池，同时可以用broker_pool_limit指定，最大链接数。

```
from celery import group

numbers = [(2, 2), (4, 4), (8, 8), (16, 16)]
res = group(add.s(i, j) for i, j in numbers).apply_async()

res.get() #[4, 8, 16, 32]
```

**Routing**

celery 可以发送任务的时候用queue参数指定队列。同时还可以使用exchange，routing_key，priority等参数。


> http://docs.celeryproject.org/en/latest/userguide/index.html

#### Concurrency

#### celery Routing

#### Monitoring and Management

常用的celery监控命令

celery status

### Celery 代码解析

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


### Python 2&3兼容性

vine 包 python_2_unicode_compatible

### kombu库

### 参考资料

> https://github.com/celery/billiard
> http://www.celeryproject.org/