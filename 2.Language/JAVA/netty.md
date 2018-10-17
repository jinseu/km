## netty

### 基本流程

#### 服务端创建

一段典型的Netty服务端启动的代码如下:

```
import io.netty.channel.socket.nio.NioServerSocketChannel;

EventLoopGroup bossGroup = new NioEventLoopGroup(2);   # 即ServerBootstrap.parentGroup
EventLoopGroup workerGroup = new NioEventLoopGroup(8); # 即ServerBootstrap.childGroup
ServerBootstrap bootstrap = new ServerBootstrap();

ChannelFuture cf = bootstrap.group(bossGroup,workerGroup)
                .option(ChannelOption.SO_REUSEADDR,false)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ProxyInitializer(definition,trafficHandler))
                .childOption(ChannelOption.AUTO_READ,true)
                .bind(8999);
```

下面具体分析如下


**1. 创建ServerBootStrap 实例**

ServerBootStrap是Netty服务端的启动辅助类，提供了一系列的方法用于设置服务端相关的启动参数。

具体特点如下：
1. 关联的类或者组件比较多，但是提供了一个无参构造函数，值得在系统设计时参考，化负责为简单
2. 内部有一个HashMap options（LinkedHashMap），Channel 选项（TCP选项）

**2. 设置并绑定Reactor线程池，即EventLoopGroup**

处理网络I/O事件，用户自定义的Task和定时任务Task
 * 一般会初始化两组，也可以创建一个共享
 * 是MultithreadEventExecutorGroup 的子类

**3. 设置并绑定服务端Channel**，

即NioServerSocketChannel，通过工厂类，在服务启动时调用，因此反射对性能的影响并不大。

**4. 创建并初始化Channel Pipeline**

ChannelPipeline 并不是NIO必须的


**5. 添加并设置ChannelHandler**

如下的代码添加了两个Handler，添加的Handler一般都是ChannelInboundHandlerAdapter类的子类，根据需要override如下方法:

1. channelActive
2. channelRead
3. channelInactive

```
import io.netty.handler.traffic.AbstractTrafficShapingHandler;
@Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(trafficShapingHandler,
                new ProxyFrontendHandler(proxyDefinition));

    }
```

**6.绑定并监听端口**

**7.Selecor轮询**

**8.轮询到准备就绪的Channel之后，就由Reactor线程执行对应的ChannelPipeline 方法**

**9. 执行Netty系统ChannelHandler 和用户添加指定的ChannelHandler**

#### 客户端创建



### 基本原理