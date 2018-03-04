### ps

ps命令原型如下：

ps [options] 

```
-e     选择所有进程，类似于-A，-ax
-a     选择所有进程，但是需要除去同会话的leader进程和没有关联终端的进程。
-o     指定输出的列，-o paramerer1,paramerer2 ..
ww     可以不限制宽度输出 -w参数设定屏幕宽
-f     显示full-format 列表，一般而言会额外显示PPID
--sort 将输出根据特定的列排序，同时还可以在参数前加上+（升序）或-（降序）来指定排序方式。默认按照PID排序，
线程相关参数：

H      Show threads as if they were processes.
-L     显示线程，LWP（线程ID）NLWP（线程数）
m      Show threads after processes.
-m     Show threads after processes.
-T     Show threads, possibly with SPID column.
```
关于输出列的说明
1. ps 命令中START列或STIME列指进程启动的时间。TIME列则指占用的CPU时间。
2. 在参数后加上`=`就可以移除每一列的头部
3. 添加 -axjf --forest参数可以查看进程树
4. 对于cmd选项，添加e参数，可以列出环境变量，例如`ps -eo cmd e`可以显示进程启动时的环境变量。

### top

#### 1. 如何将top的输出通过管道交给另一个进程

    -b Batch mode,在该模式下，top可以将输出通过管道导入其他程序或者文件
    -n 行数，指定Top显示的行数
    -d 设置刷新时间


### 2. 如何让top显示每一个CPU的使用情况
数字 1
### 3. 如何在top里杀进程
k pid
### 4. top默认刷新时间？如何修改
默认是刷新时间是3.0，在交互模式下，可以按d修改
### 5. top里的load average是如何计算的
/proc/loadaverge uptime
后面两个呢，一个的分子是正在运行的进程数，分母是进程总数；另一个是最近运行的进程ID号。
load average是根据队列中等待运行的进程数计算的
### 6. 假设top显示ffmpeg进程的CPU使用率为143.7%，请具体解释这个数值是如何算出来的

In  a  true  SMP  environment, if a process is multi-threaded and top is not operating in Threads mode,amounts greater than 100% may be reported.  You toggle Threads mode with the 'H' interactive command.

### 7. 第四列的NI是什么意思

进程优先级，负值表示高优先级

### kill

kill命令虽然常用于终结进程，但其本质上仅仅给进程发送一个信号而已。使用较为简单。默认情况下送出的信号为SIGTERM。

参数列表
```
-l 列出可用的信号
-<signal>           指定具体的信号类型
-s <signal>
--signal <signal>
```

示例：

```
kill -9 111 #给111进程发出SIGKILL信号
kill -9 -1 Kill all processes you can kill.
```
说明事项：
1. 内核进程，以及处于uninterruptible sleep状态以及zombie状态的进程都不能被kill命令终止。
2. trap 命令用来在脚本中为信号分配信号处理程序