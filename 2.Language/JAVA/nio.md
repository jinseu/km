### nio&netty

#### NIO

Channel和Buffer 是 NIO 中的核心对象，几乎在每一个 I/O 操作中都要使用它们。Channel是对原 I/O 包中的流的模拟。到任何目的地(或来自任何地方)的所有数据都必须通过一个 Channel 对象。一个 Buffer 实质上是一个容器对象。发送给一个通道的所有对象都必须首先放到缓冲区中；同样地，从通道中读取的任何数据都要读到缓冲区中。


#### Buffer

Buffer 是一个对象， 它包含一些要写入或者刚读出的数据。 在 NIO 中加入 Buffer 对象，体现了新库与原 I/O 的一个重要区别。在面向流的 I/O 中，将数据直接写入或者将数据直接读到 Stream 对象中。
在 NIO 库中，所有数据都是用缓冲区处理的。在读取数据时，它是直接读到缓冲区中的。在写入数据时，它是写入到缓冲区中的。任何时候访问 NIO 中的数据，您都是将它放到缓冲区中。
缓冲区实质上是一个数组。通常它是一个字节数组，但是也可以使用其他种类的数组。但是一个缓冲区不 仅仅 是一个数组。缓冲区提供了对数据的结构化访问，而且还可以跟踪系统的读/写进程。
缓冲区类型
最常用的缓冲区类型是 ByteBuffer。一个 ByteBuffer 可以在其底层字节数组上进行 get/set 操作(即字节的获取和设置)。
ByteBuffer 不是 NIO 中唯一的缓冲区类型。事实上，对于每一种基本 Java 类型都有一种缓冲区类型：

- ByteBuffer
- CharBuffer
- ShortBuffer
- IntBuffer
- LongBuffer
- FloatBuffer
- DoubleBuffer

每一个 Buffer 类都是 Buffer 接口的一个实例。 除了 ByteBuffer，每一个 Buffer 类都有完全一样的操作，只是它们所处理的数据类型不一样。因为大多数标准 I/O 操作都使用 ByteBuffer，所以它具有所有共享的缓冲区操作以及一些特有的操作。

**内部实现**

在从通道读取数据时，数据被放入到缓冲区。在有些情况下，可以将这个缓冲区直接写入另一个通道，但是在一般情况下，您还需要查看数据。这是使用 访问方法 get() 来完成的。同样，如果要将原始数据放入缓冲区中，就要使用访问方法 put()。

可以用三个值指定Buffer在任意时刻的状态：

- position 
- limit
- capacity
- mark

position 变量跟踪已经写了多少数据。更准确地说，它指定了下一个字节将放到数组的哪一个元素中。因此，如果从通道中读三个字节到缓冲区中，那么缓冲区的position 将会设置为3，指向数组中第四个元素。同样，在写入通道时，是从缓冲区中获取数据。 position 值跟踪从缓冲区中获取了多少数据。更准确地说，它指定下一个字节来自数组的哪一个元素。因此如果从缓冲区写了5个字节到通道中，那么缓冲区的 position 将被设置为5，指向数组的第六个元素。

limit 变量表明还有多少数据需要取出(在从缓冲区写入通道时)，或者还有多少空间可以放入数据(在从通道读入缓冲区时)。position 总是小于或者等于 limit。

capacity 表明可以储存在缓冲区中的最大数据容量。实际上，指定了底层数组的大小，或者是准许使用的底层数组的容量。limit 不能大于 capacity。

mark 对position进行了标记，可以显示的调用mark()方法来标记当前的position。在调用reset方法后，position将被重置为mark index。如果mark没有定义的情况

这四个变量的关系如下:

0 <= mark <= position <= limit <= capacity

新创建的buffer的position为0，mark为undefined，limit可能为0，也可能不为0，需要根据具体的构造函数分析。除了mark的值，其他的三个值，都可以通过对应的方法获取。

此外，还有三个方法可以与以上四个变量相关:

1. clear() makes a buffer ready for a new sequence of channel-read or relative put operations: It sets the limit to the capacity and the position to zero.
2. flip() makes a buffer ready for a new sequence of channel-write or relative get operations: It sets the limit to the current position and then sets the position to zero.
3. rewind() makes a buffer ready for re-reading the data that it already contains: It leaves the limit unchanged and sets the position to zero.
4. remaining() Returns the number of elements between the current position and the limit.


**Direct or Non-direct**

Direct Buffer

1. Java 虚拟机将尽最大努力直接对它执行本机 I/O 操作。也就是说，它会在每一次调用底层操作系统的本机 I/O 操作之前(或之后)，尝试避免将缓冲区的内容拷贝到一个中间缓冲区中(或者从一个中间缓冲区中拷贝数据)。
2. Java 虚拟机提供更好的I/O性能，直接在Buffer上面做native I/O操作，避免从java虚拟机到底层系统I/O操作之间的内存拷贝。
2. 更高的创建和删除开销，不在普通GC管理之下
3. 一般用于大量的，长期存在的数据

Non-direct Buffer

1. 在Java虚拟机堆中
2. 分配和收回的代价较小
3. 可以用mmap和JNI的方式实现

##### Channel

Channel 本身是一个接口，链接了I/O操作，可以通过它读取和写入数据。具体的实现类包括ServerSocketChannel, SocketChannel 等。通道就像是流。
正如前面提到的，所有数据都通过 Buffer 对象来处理。永远不会将字节直接写入通道中，相反，您是将数据写入包含一个或者多个字节的缓冲区。同样，不会直接从通道中读取字节，而是将数据从通道读入缓冲区，再从缓冲区获取这个字节。

通道与流的不同之处在于通道是双向的。而流只是在一个方向上移动(一个流必须是 InputStream 或者 OutputStream 的子类)， 而 通道 可以用于读、写或者同时用于读写。
因为通道是双向的，所以通道可以比流更好地反映底层操作系统的真实情况。特别是在 UNIX 模型中，底层操作系统pipe是双向的。

Channel的实现类中常用的方法如下，其中read，write的参数都是一个Buffer，会从buffer中读取数据，或者将buffer中的数据写入。

```
read(Buffer)
write(Buffer)
close()
isOpen()
```

**SelectableChannel**

是一个接口，用来说明一个Channel是否能够被多路复用，即register到一个Selector上将channel和Selector 绑定。但是解绑并不能直接调用channel方法，而是要调用SelectionKey的cancel方法，或者channel的close方法。


##### Selector

Selector是一个多路复用器，与SelectableChannel一起实现了多路复用。在具体使用时，基本方法如下：

**1. channel.register(selector, SelectionKey.OP_ACCEPT)**

**2. selector.select(1000)**

**3. Set<SelectionKey> selectedKeys = selector.selectedKeys()**

**4. 获取SelectionKey**

```
Iterator<SelectionKey> it = selectedKeys.iterator();
SelectionKey key = null;
while(it.hasNext())
{
    key = it.next();
    it.remove();
    handleInput(key);
}
```

**5. 从key获取对应的channel，然后读写数据**

```
SocketChannel sc = (SocketChannel) key.channel();
ByteBuffer readBuffer = ByteBuffer.allocate(1024);
int readByters = sc.read(readBuffer);
readBuffer.flip();
byte[] bytes = new byte[readBuffer.remaining()];
readBuffer.get(bytes)
```

**SelectionKey**

SelectionKey 一共有四种类型，分别OP_CONNECT(仅限客户端）,OP_ACCEPT（仅限服务端）,OP_READ，OP_WRITE。其中，OP_CONNECT在判断key.isConnectable() 之后，还需要调用socketchannel.finishConnect()才能完成链接的创建。然后在socketchannel上register OP_READ 类型的操作。


#### AIO

java AIO机制的基础是CompletionHandler接口，该接口用来消费异步I/O的结果，即异步I/O的回调类都应该实现这个接口。

该接口有两个方法

1. completed(V result, A attachment)
2. failed(Throwable exc, A attachment)

The completed method is invoked when the I/O operation completes successfully. The failed method is invoked if the I/O operations fails.

java AIO机制中另外一个重要组成部分是AsynchronousByteChannel 接口（非线程安全），这个接口提供了四个方法，分别是

```
1. read(ByteBuffer dst)
2. read(ByteBuffer dst, A attachment, CompletionHandler<Integer,? super A> handler)
3. write(ByteBuffer src)
4. write(ByteBuffer src, A attachment, CompletionHandler<Integer,? super A> handler)
```

方法1、3会分别进行读写操作，然后将结果放入一个Future<Integer>对象中。
方法2、4则是继续进行异步读写，然后在读写完成后回调CompletionHandler类

例如以Socket server为例，解释AIO的场景

1. 创建AsynchronousServerSocketChannel对象，并bind到一个IP地址和端口
2. 调用AsynchronousServerSocketChannel.accept 方法，并注册accept后的回调CompletionHandler
3. 注册在accept方法中的CompletionHandler在completed的时候，会收到一个AsynchronousSocketChannel对象。此时需要继续调用AsynchronousServerSocketChannel.accept方法来接收下一个链接请求。
4. 然后此时AsynchronousSocketChannel的read方法（即方法2，同时注册回调函数）,然后在回调函数中处理读取的数据。

一个AIO的例子

```

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;


public class AioTimeServer {
    public static void main(String[] args){
        int port = 9999;
        AioTimeServerHandler server = new AioTimeServerHandler(port);
        new Thread(server, "AIO-Server").start();
    }
}



public class AioTimeServerHandler implements Runnable{

    int port;
    CountDownLatch latch;
    AsynchronousServerSocketChannel asyncChannel;

    public AioTimeServerHandler(int port) {
        this.port = port;
        try {
            asyncChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void doAccept(){
        asyncChannel.accept(this, new AioAcceptCompletionHandler());
    }

    public void run() {
        this.latch = new CountDownLatch(1);
        this.doAccept();

        try{
            this.latch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


public class AioAcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AioTimeServerHandler> {

    @Override
    public void completed(AsynchronousSocketChannel result, AioTimeServerHandler attachment) {
        attachment.asyncChannel.accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer, buffer, new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AioTimeServerHandler attachment)
    {
        exc.printStackTrace();
        //attachment.latch.countDown();
    }
}


public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer>{

    private AsynchronousSocketChannel channel;

    public ReadCompletionHandler (AsynchronousSocketChannel channel){
        if (this.channel == null){
            this.channel = channel;
        }
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        System.out.println(result);
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        try {
            String req = new String(body, "UTF-8");
            System.out.println("The time server receive order:" + req);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
            doWrite(currentTime);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void doWrite(String currentTime)
    {
        byte[] bytes = currentTime.getBytes();
        ByteBuffer writeBuffer =  ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>(){
            @Override
            public void completed(Integer result, ByteBuffer buffer) {
                if (buffer.hasRemaining())
                    channel.write(buffer, buffer, this);
            }
            public void failed(Throwable exc, ByteBuffer attachment){
                try{
                    channel.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.channel.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}


```





#### 参考资料

