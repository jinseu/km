## Java 虚拟机



![虚拟机](./img/hotspot-jvm.png)

### Java 虚拟机参数

**节本内存相关常见参数列表**

1. `-Xms4g` 初始可用内存4g，可以设置与-Xmx相同，以避免每次垃圾回收完成后JVM重新分配内存。
2. `-Xmx4g` 最大可用内存4g
3. `-Xss128k` 每个线程的堆栈大小128K
4. `-Xmn2g` 设置年轻代大小为2G。整个JVM内存大小=年轻代大小 + 年老代大小 + 持久代大小
5. `-XX:+HeapDumpOnOutOfMemoryError` OOM是dump heap 内存
6. `-XX:HeapDumpPath=data` headp dump 的目录

**CMS 内存收集**

CMS 是针对老年代的垃圾收集算法，目标是尽量减少应用的暂停时间，减少full gc发生的几率，利用和应用程序线程并发的垃圾回收线程来标记清除年老代。

1. `-XX:+UseConcMarkSweepGC` 启用CMS
2. `-XX:CMSInitiatingOccupancyFraction=75`
3. `-XX:+UseCMSInitiatingOccupancyOnly`
4. `-XX:+UseG1GC`
5. `-XX:MaxGCPauseMillis=20`
6. `-XX:GCTimeRatio=n` 不超过应用运行时间的`1/(1+n)`用在垃圾回收上
7. `-XX:InitiatingHeapOccupancyPercent=35` 
8. `-XX:+DisableExplicitGC`

**异常处理相关以及日志相关**

1. `-XX:-OmitStackTraceInFastThrow` 关闭JDK优化，抛出原始异常栈
2. `-XX:+PrintGCTimeStamps` 打印GCTimeStamps

### GC

HotSpot有这么多的垃圾回收器，那么如果有人问，Serial GC、Parallel GC、Concurrent Mark Sweep GC这三个GC有什么不同呢？请记住以下口令：

* 如果你想要最小化地使用内存和并行开销，请选Serial GC；
* 如果你想要最大化应用程序的吞吐量，请选Parallel GC；
* 如果你想要最小化GC的中断或停顿时间，请选CMS GC。

#### Serial/Serial Old

#### Parallel


#### CMS

#### G1

The Garbage-First (G1) collector is a server-style garbage collector, targeted for multi-processor machines with large memories.

G1 收集器的目标是:

* Can operate concurrently with applications threads like the CMS collector.
* Compact free space without lengthy GC induced(引起) pause times.
* Need more predictable GC pause durations.
* Do not want to sacrifice a lot of throughput performance.
* Do not require a much larger Java heap.


> https://tech.meituan.com/g1.html
> http://www.oracle.com/technetwork/tutorials/tutorials-1876574.html

**G1收集器适用的场景**

heap很大（>6GB）同时需要限制 GC 时延

2. Full GC durations are too long or too frequent.
3. The rate of object allocation rate or promotion varies significantly.
4. Undesired long garbage collection or compaction pauses (longer than 0.5 to 1 second)

### 内存

#### Run-Time Data Areas

java 运行时的内存布局包括如下内容

1. PC Register
 * 每一个线程都有一个 PC Register，如果在执行native method，name PC Register的值为null，否则指向正在执行的JVM instruction。
2. Java Virtual Machine Stacks
 * 每一个线程都有一个 JVM Stacks，和线程一起同时创建
 * 只有push and pop frames, frames may be heap allocated.
3. Heap
 * 虚拟机启动时创建，由垃圾收集器主要管理
 * 存放对象实例，几乎所有的对象实例以及数组都在堆上分配（例外之处在于，栈上分配，标量替换等编译优化）
4. Method Area
 * 在虚拟机启动时创建，各个线程共享
 * 存储已经被虚拟机加载的类信息，常量，静态变量等
 * 在HostSpot虚拟机中方法区是永久代的一部分
5. Run-Time Constant Pool
 * Java 方法区的一部分 Each run-time constant pool is allocated from the Java Virtual Machine's methodarea
 * 保存Class文件的在编译器生成的各种字面量和符号引用
 * 允许在运行綦江将新的常量放入池中，比如String类的intern 方法
 * 由于运行时常量池分配中方法区内，可以通过`-XX:PermSize` 和`-XX:MaxPermSize` 来限制方法区的大小，从而间接限制其中常量池的容量
6. Native Method Stacks
 * 每个线程一个，线程运行时创建
 * 根据虚拟机的实现有不同，可以与JVM Stacks 合二为一

#### Java Frame




#### String.intern

> https://tech.meituan.com/in_depth_understanding_string_intern.html


#### 参考资料

> http://www.cs.umd.edu/~pugh/java/memoryModel/

### 线程与并发
