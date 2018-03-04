## ayncio（trollius）

### Event Loop
event loop是asyncio的核心，提供了一下四点特性：
1. 注册，执行，撤销调用
2. 创建不同种类通信的客户端和服务端transport
3. 启动子进程，以及关联对应Transport。
4. 委托其他线程执行函数（Delegating costly function calls to a pool of threads）

默认的event loop有两种，分别是BaseEventLoop以及AbstractEventLoop。其中BaseEventLoop是AbstractEventLoop的子类。同时BaseEventLoop不应该是其他第三方的EventLoop的父类。第三方实现应该继承自AbstractEventLoop。

#### Event Loop的基本方法

##### run_forever
##### run_until_complete

Run until the Future is done.

If the argument is a coroutine object, it is wrapped by ensure_future().

Return the Future’s result, or raise its exception.

##### is_running
##### stop

Stop running the event loop.This causes run_forever() to exit at the next suitable opportunity (see there for more details).
##### is_closed
##### close

Close the event loop. The loop must not be running. Pending callbacks will be lost.
#### calls

### Future

### Tasks and coroutines

