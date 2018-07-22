## Java 虚拟机

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

**异常处理相关**

1. `-XX:-OmitStackTraceInFastThrow` 关闭JDK优化，抛出原始异常栈


