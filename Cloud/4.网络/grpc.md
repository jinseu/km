### gRPC

grpc是一个使用protocol buffer 作为Interface Definition Language (IDL)语言的远程调用框架。

支持四种服务类型

1. single->single
2. single->stream
3. stream->single
4. stream->stream


Starting from a service definition in a .proto file, gRPC provides protocol buffer compiler plugins that generate client- and server-side code.

服务端流程:

1. 实现在service中声明的方法
2. 启动gRPC server
3. gRPC 框架解码输入的请求，执行service 中的方法
4. 编码响应内容

客户端流程：

1. 创建一个local stub，这个stub实现了和service中同样的方法。
2. 调用stub中的方法
3. 编码/序列化 请求参数，发送到server
4. 接收响应，解码/反序列化



#### 路由

#### HelloWorld 的流程图

#### 客户端基本说明

首先需要创建grpc.Channel，channel 分为两种insecure_channel和secure_channel。下面以insecure_channel的创建来说明。一个channel是一个grpc._channel.Channel 类的实例。channel内部包含一个`_channel`成员，所有channel 在初始化时，有以下关键操作

```
grpc_init()  # grpc c++ 核心函数，初始grpc 核心线程，状态插件等
self._state = _ChannelState()
self._state.c_channel = grpc_insecure_channel_create(<char *>target, c_arguments, NULL)
self._state.c_call_completion_queue = (grpc_completion_queue_create_for_next(NULL))
self._state.c_connectivity_completion_queue = (grpc_completion_queue_create_for_next(NULL))
```

**`grpc_insecure_channel_create`**


**`grpc_completion_queue`**

调用具体的方法时，需要创建stub，stub的代码一般都是根据proto文件自动生成的。stub中的每一个方法都是一个Callable对象，在初始化时生成。

```
  class KVStub(object):

    def __init__(self, channel):
    
      self.Range = channel.unary_unary(
          '/etcdserverpb.KV/Range',
          request_serializer=RangeRequest.SerializeToString,
          response_deserializer=RangeResponse.FromString,
          )
```

**subscribe**

**unary_unary**



**服务端**

服务端的关键在于grpc.server

1. grpc.server 是一个函数，参数有5个，其中必须参数thread_pool（futures.ThreadPoolExecutor）。在server函数内部会调用`grpc._server.create_server()`，create_server函数内部会创建一个`grpc._server._Server` 实例并返回。
2. 在_Server初始化时，会创建cygrpc.Server实例，cygrpc.CompletionQueue，并将CompletionQueue注册到server上，最后创建一个`self._state = _ServerState`
3. 在server初始化完毕后，会向server绑定服务
 1. dfas
4. 添加监听端口
 1. `Server.add_insecure_port`
 2. `server.add_http2_port` Cython grpc.Server 方法
 3. `grpc_server_add_insecure_http2_port` C函数声明在`extern from "grpc/grpc.h"`，定义在`/core/ext/transport/chttp2/server/insecure/server_chttp2.cc`
 4. `grpc_chttp2_server_add_port` `/core/ext/transport/chttp2/server/chttp2_server.cc`
 5. `grpc_tcp_server_create `以及`grpc_tcp_server_add_port`, 定义在`./src/core/lib/iomgr/tcp_server_posix.cc`
  4.2. 

#### 关键源码解析


**内存分配**

grpc使用gpr_zalloc来分配内存

gRpc 虽然支持比较多的语言，但是事实上只有三种实现，分别是C，JAVA，以及Go。下面以Python为例说每个grpc Python-C语言实现，然后再说嘛JAVA实现以及GO实现。

### python


> https://blog.bugsnag.com/grpc-and-microservices-architecture/
> https://grpc.io/blog/principles
> https://platformlab.stanford.edu/Seminar%20Talks/gRPC.pdf
