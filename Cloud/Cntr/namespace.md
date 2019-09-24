## Namespace

Linux Namespace是Linux提供的一种内核级别环境隔离的方法，实现了对UTS、IPC、Mount、PID、Network、User等的隔离机制。（chroot系统调用，可以将一个用户的根目录转移到某个指定目录下。使其不能访问指定目录外的内容，从而实现一定程度上的隔离）

Namespace则是在chroot的基础上更进一步，更为完整的隔离。目前Linux Namespace有如下种类。

| 分类 | 系统调用参数 | 隔离域 |
|------|------------|------------|
|Mount namespaces | CLONE_NEWNS	| Mount points |
|UTS namespaces	| CLONE_NEWUTS	| Hostname and NIS domain name |
|IPC namespaces	| CLONE_NEWIPC	| System V IPC, POSIX message queues |
|PID namespaces	| CLONE_NEWPID	| Process IDs |
|Network namespaces	| CLONE_NEWNET | Network devices, stacks, ports, etc.|
|User namespaces	| CLONE_NEWUSER	| User and group IDs |
|Cgroup | CLONE_NEWCGROUP | Cgroup root directory |

在以上几种Namespace中Mount namespaces, UTS namespaces, IPC namespaces, PID namespaces比较简单。User namespaces，Cgroup namespaces，则相对比较复杂。

在内核中，每个task都有一个指针指向一个namespace，存放在tsk->nsproxy中，同一个container的所有进程指向的namespace是相同的，不同container的进程所指向的namepsace是不同的。nsproxy 结构体的定义如下：

```
/*
 * A structure to contain pointers to all per-process
 * namespaces - fs (mount), uts, network, sysvipc, etc.
 *
 * The pid namespace is an exception -- it's accessed using
 * task_active_pid_ns.  The pid namespace here is the
 * namespace that children will use.
 *
 * 'count' is the number of tasks holding a reference.
 * The count for each namespace, then, will be the number
 * of nsproxies pointing to it, not the number of tasks.
 *
 * The nsproxy is shared by tasks which share all namespaces.
 * As soon as a single namespace is cloned or unshared, the
 * nsproxy is copied.
 */
struct nsproxy {
        atomic_t count;
        struct uts_namespace *uts_ns;
        struct ipc_namespace *ipc_ns;
        struct mnt_namespace *mnt_ns;
        struct pid_namespace *pid_ns_for_children;
        struct net           *net_ns;
        struct cgroup_namespace *cgroup_ns;
};
```

> http://hustcat.github.io/tags/#namespace
> https://lwn.net/Articles/531114/

#### PID Namespace

#### Pid namespaces

```
#define _GNU_SOURCE
#include<stdio.h>
#include <unistd.h>
#include <signal.h>
#include <sched.h>
#include <sys/wait.h>


#define STACK_SIZE (1024 * 1024)
static char cntr_stack[STACK_SIZE];
char* const cntr_args[] = {
    "/bin/bash",
    NULL
};

int cntr_main(void* arg)
{
    printf("Container [%5d] - inside the container!\n", getpid());  // Container [    1] - inside the container!
    sethostname("container",10);
    execv(cntr_args[0], cntr_args);
    printf("Something's wrong!\n");
    return 1;
}

int main()
{
    printf("Parent [%5d] - start a container!\n", getpid());
    int cntr_pid = clone(cntr_main, cntr_stack+STACK_SIZE, CLONE_NEWPID | SIGCHLD, NULL);
    waitpid(cntr_pid, NULL, 0);
    printf("Parent - container stopped!\n");
    return 0;
}
```

#### Mount namespaces

Mount namespaces中，父进程会把自己的文件结构复制给子进程中。而子进程中新的namespace中的所有mount操作都只影响自身的文件系统，而不对外界产生任何影响。这样可以做到比较严格地隔离。于是我们就可以在子进程中挂载新的文件系统。

#### User namespaces

User Namespace使用了CLONE_NEWUSER的参数。使用了这个参数后，内部看到的UID和GID已经与外部不同了，默认显示为65534。那是因为容器找不到其真正的UID所以，设置上了最大的UID（其设置定义在/proc/sys/kernel/overflowuid）。

要把容器中的uid和真实系统的uid给映射在一起，需要修改 /proc/<pid>/uid_map 和 /proc/<pid>/gid_map 这两个文件。这两个文件的格式为：

```
ID-inside-ns ID-outside-ns length
```

其中：


第一个字段ID-inside-ns表示在容器显示的UID或GID，
第二个字段ID-outside-ns表示容器外映射的真实的UID或GID。
第三个字段表示映射的范围，一般填1，表示一一对应。
比如，把真实的uid=1000映射成容器内的uid=0
```
$ cat /proc/2465/uid_map
         0       1000          1
```
再比如下面的示例：表示把namespace内部从0开始的uid映射到外部从0开始的uid，其最大范围是无符号32位整形

```
$ cat /proc/$$/uid_map
         0          0          4294967295
```

另外，需要注意的是：

写这两个文件的进程需要这个namespace中的CAP_SETUID (CAP_SETGID)权限（可参看Capabilities）
写入的进程必须是此user namespace的父或子的user namespace进程。另外需要满如下条件之一：

1. 父进程将effective uid/gid映射到子进程的user namespace中
2. 父进程如果有CAP_SETUID/CAP_SETGID权限，那么它将可以映射到父进程中的任一uid/gid。

### Network Namspace


### Namespace 系统调用

具体隔离的种类，可以在以下三个系统调用中指定。
* clone()
* unshare() – 使某进程脱离某个namespace
* setns() – 把某进程加入到某个namespace

其中clone是最主要的系统调用，考虑到linux中线程以及进程的创建是通过clone调用来完成的。所以从这里也可以说明Namespace(docker)和进程之间的关系，是先有进程，再有Namespace(docker)。

setns可以将一个进程加入到一个已经存在的namespace中。加入的namespace可以通过/proc/[pid]/ns目录下的某个的文件描述符来指定。`int setns(int fd, int nstype);`

unshare 则可以使调用进程脱离当前的namespace。`int unshare(int flags);`其中flags指定了具体要脱离哪一个Namespace。可以指定脱离CLONE_NEWNET等namespace分类。不过需要说明的是这里的参数和clone的参数又有一些不同，具体使用时，需要注意。

/proc/[pid]/ns目录下的内容如下所示：

```
ljin@ubuntu:~$ ls -l /proc/1315/ns
总用量 0
lrwxrwxrwx 1 ljin ljin 0 12月  7 22:47 ipc -> ipc:[4026531839]
lrwxrwxrwx 1 ljin ljin 0 12月  7 22:47 mnt -> mnt:[4026531840]
lrwxrwxrwx 1 ljin ljin 0 12月  7 22:47 net -> net:[4026531957]
lrwxrwxrwx 1 ljin ljin 0 12月  7 22:47 pid -> pid:[4026531836]
lrwxrwxrwx 1 ljin ljin 0 12月  7 22:47 user -> user:[4026531837]
lrwxrwxrwx 1 ljin ljin 0 12月  7 22:47 uts -> uts:[4026531838]

```
ns目录下内容以软连接的形式指向了硬盘上的inode。如果两个进程具有相同的namespace，那么这两个进程下的对应的namespace（例如ipc）指向同一个inode。

一般而言，是看不到docker 的网络namespace的，需要使用`nsenter`


