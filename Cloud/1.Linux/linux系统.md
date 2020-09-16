
### 2. 文件系统

linux根目录下的文件包括
bin，dev，home，lib，media，opt，root，sbin，sys，usr
boot  etc lib64  mnt    proc  run   srv   tmp  var
等

/bin是系统的一些指令。bin为binary的简写主要放置一些系统的必备执行档例如:cat、cp、chmod df、dmesg、gzip、kill、ls、mkdir等。在挂载/usr 目录前就可以使用。

/sbin一般是指超级用户指令。主要放置一些系统管理的必备程序例如:cfdisk、dhcpcd、dump、e2fsck、fdisk、halt、ifconfig等。

/usr/bin　是你在后期安装的一些软件的运行脚本。主要放置一些应用软件工具的必备执行档例如c++、g++、gcc、chdrv、diff、dig、du等
/usr/sbin   放置一些用户安装的系统管理的必备程式例如:tcpdump等

/var contains data that is changed when the system is running normally.

/tmp is cleared out at boot or at shutdown by the local system.

/boot 目录中存放了grub，kernel启动镜像

/usr/include里都是头文件，而/usr/lib里则是编译过的对象文件，以及一些动态链接库

/proc The  proc  filesystem  is a pseudo-filesystem which provides an interface to kernel data structures.  It is commonly mounted at /proc.  Most of it is read-only, but some files allow kernel variables to be changed.

/proc目录中比较常用的子目录有：

/proc/cpuinfo

/proc/meminfo

/proc/uptime

/proc/stat

/proc/[pid] There is a numerical subdirectory for each running process; the subdirectory is named by the process ID.  




## 日期与时间

### 4 NTP与UTC

不幸的是，ntpdate调整时间的方式就是我们所说的”跃变“：在获得一个时间之后，ntpdate使用settimeofday(2)设置系统时间，这有几个非常明显的问题：

第一，这样做不安全。ntpdate的设置依赖于ntp服务器的安全性，攻击者可以利用一些软件设计上的缺陷，拿下ntp服务器并令与其同步的服务器执行某些消耗性的任务。由于ntpdate采用的方式是跳变，跟随它的服务器无法知道是否发生了异常（时间不一样的时候，唯一的办法是以服务器为准）。

第二，这样做不精确。一旦ntp服务器宕机，跟随它的服务器也就会无法同步时间。与此不同，ntpd不仅能够校准计算机的时间，而且能够校准计算机的时钟。

第三，这样做不够优雅。由于是跳变，而不是使时间变快或变慢，依赖时序的程序会出错（例如，如果ntpdate发现你的时间快了，则可能会经历两个相同的时刻，对某些应用而言，这是致命的）。

因而，唯一一个可以令时间发生跳变的点，是计算机刚刚启动，但还没有启动很多服务的那个时候。其余的时候，理想的做法是使用ntpd来校准时钟，而不是调整计算机时钟上的时间。

NTPD 在和时间服务器的同步过程中，会把 BIOS 计时器的振荡频率偏差——或者说 Local Clock 的自然漂移(drift)——记录下来。这样即使网络有问题，本机仍然能维持一个相当精确的走时。

可以使用hwclock -w命令，将当前时间写入BIOS。

UTC是指世界标准时间

在debian中可以使用tzselect来修改时区，或者可以使用dpkg-reconfigure tzdata

### date

date可以打印或者设置系统的日期和时间。

常用参数：
-s: 设置系统时间

实例：
```

星期日
```

### 5 硬链接与软连接

Liunux 硬链接允许一个文件拥有多个有效的路径名，这样用户就可以建立硬链接指向同一个文件。即多个文件指向同一个inode，删除一个链接并不会影响索引节点本身和其他链接，只有当所有与之相关的连接被删除后，该文件才会被删除。

硬链接有两个限制：
- 不允许给目录创建硬链接（目录环）
- 只有在同一文件系统中的文件之间才能建立硬链接（inode 号仅在各文件系统下是唯一的，当 Linux 挂载多个文件系统后将出现 inode 号重复的现象，因此硬链接创建时不可跨文件系统）
- 不能对不存在的文件创建硬链接

软连接（符号链接）

- 软链接有自己的文件属性及权限等；
- 可对不存在的文件或目录创建软链接；
- 软链接可交叉文件系统；
- 软链接可对文件或目录创建；
- 创建软链接时，链接计数 i_nlink 不会增加；
- 删除软链接并不影响被指向的文件，但若被指向的原文件被删除，则相关软连接被称为死链接（即 dangling link，若被指向路径文件被重新创建，死链接可恢复为正常的软链接）。

参考资料：

https://www.ibm.com/developerworks/cn/linux/l-cn-hardandsymb-links/  (VFS文件系统部分待补充)

### 6 文件的权限

linux下文件还有一些隐藏属性，可以用lsattr来查看和更改

SUID：其他用户在执行该文件时，以其拥有者的身份来执行该文件。

- SUID权限仅对二进制可执行文件有效
- 执行者对于该文件具有x的权限
- 本权限仅在执行该文件的过程中有效
- 执行者将具有该文件拥有者的权限

     -rwsr-xr-x 1 root root 54192 11月 18  2015 /usr/bin/passwd

SGID和SUID的定义类似，只不过将权限限定在了该文件所属的用户组的权限。

Sticky权限

- 只能设置在目录上
- 任何用户都有权创建和修改文件，但是只有文件的owner和root用户才有权限可以删除文件。
- 如果同时设置了x权限，则显示T，否则显示t。
- 可以使用 chmod +t /usr/local/tmp或chmod 1777 /usr/local/tmp

    drwxrwxrwt  4 root root  133 8月   4 14:38 tmp

权限对目录的意义

1. r权限：拥有此权限表示可以读取目录结构列表，也就是说可以查看目录下的文件名和子目录名，注意：仅仅指的是名字。
2. w权限：拥有此权限表示具有更改该目录结构列表的权限，总之，目录的w权限与该目录下的文件名或子目录名的变动有关，注意：指的是名字。具体如下：
 - 在该目录下新建新的文件或子目录。
 - 删除该目录下已经存在的文件或子目录（不论该文件或子目录的权限如何），注意：这点很重要，用户能否删除一个文件或目录，看的是该用户是否具有该文件或目录所在的目录的w权限。
 - 将该目录下已经存在的文件或子目录进行重命名。
 - 转移该目录内的文件或子目录的位置。
3. x权限：拥有目录的x权限表示用户可以进入该目录成为工作目录，能不能进入一个目录，只与该目录的x权限有关，如果用户对于某个目录不具有x权限，则无法切换到该目录下，也就无法执行该目录下的任何命令，即使具有该目录的r权限。且如果用户对于某目录不具有x权限，则该用户不能查询该目录下的文件的内容，注意：指的是内容，如果有r 权限是可以查看该目录下的文件名列表或子目录列表的。所以要开放目录给任何人浏览时，应该至少要给与r及x权限。

样例

    #更改权限的实例
    chmod u=rwx g=rw o=r filename
    #递归更改
    chmod 777 . -R

###7 linux 系统启动

系统启动顺序
1. 启动BIOS，读取MBR（主引导记录），从MBR中获取GRUB地址，运行GRUB。
2. GRUB会加载kernel，并运行第一个程序/sbin/inittab来进行初始化工作。设定系统运行级别。根据系统运行的级别，运行对应的脚本/etc/init.d/rc 在rc脚本里会设置系统变量，网络，设定/proc,加载用户模块模块。
3. 根据runlevel启动对应的服务
4. 运行/etc/rc.local

服务启动顺序

确定服务启动的顺序是根据rc.d目录下的脚本确定的，该目录下都是符号链接，连接的是上层init.d目录下的服务脚本。在系统启动时会先运行K（kill）开头的脚本，然后运行S（stop）开头的脚本。在运行同类的脚本时，先运行数字小的，然后运行数字大的。

运行级

linux中有7个运行级。包括0：关机，1：单用户模式，2：多用户，无网络，3：完全多用户，4：保留未使用，5：窗口模式，多用户，支持网络，6：重启。在/etc/inittab中定义了默认的runlevel，可以用runlevel查看当前的runlevel，也可以用telinit 3 来改变当前runlevel，telinit会通过合适的信号来告诉init程序需要改变到什么模式下运行。

单用户模式

在单用户模式下，系统会运行/sbin/sulogin，一般用于系统故障时的维护，典型的场景有忘记root密码后，进入该模式修改root密码。

###8 管道与重定向

重定向
重定向中的0，1，2分别指标准输入，标准输出，错误输出。在使用重定向时需要注意：

- 左边的命令应该有标准输出 | 右边的命令应该接受标准输入
- 左边的命令应该有标准输出 > 右边只能是文件
- 左边的命令应该需要标准输入 < 右边只能是文件
- `>>` 表示追加文件尾
- `>` 会清空内容

可以使用mkfifo来创建一个有名管道

A FIFO (short for First In First Out) has a name within the  filesystem  (created  using  mkfifo(3)),  and is opened using open(2).  Any process may open a FIFO, assuming the file permissions allow it.  The read end  is  opened  using the  O_RDONLY  flag;  the  write  end  is opened using the O_WRONLY flag.


###9 用户管理

####增加用户
创建目录中的内容是从/etc/skel目录中复制的，同时可以在配置文件adduser.conf更改这个设置

####删除用户
会根据/etc/deluser.conf进行相关的操作，删除passwd和shadow中的记录，然后会根据deluser中的配置来删除对应的文件。

####禁止用户登录
设置shell 为/usr/sbin/nologin
密码

####踢用户下线
杀掉tty对应的进程
iptables加防火墙规则


####怎样升级一个软件

apt-get update
apt-get upgrade 

注：
aptitude 命令，它为 APT 包管理功能提供了一个基于文本的全屏界面（使用 ncurses）

###11 安全习惯

SSH AGENT
https://wiki.archlinux.org/index.php/SSH_keys_(%E7%AE%80%E4%BD%93%E4%B8%AD%E6%96%87)

###12 分区与挂载





参考资料：

https://www.ibm.com/developerworks/cn/linux/l-lpic1-v3-102-1/

####d. 如何从另一块分区借一些空间

https://www.ibm.com/developerworks/cn/linux/l-linux-filesystem/

http://www.ibm.com/developerworks/cn/linux/l-lpic1-v3-102-1/

### 13. 信号机制


#### b. nohup

nohup命令会使提交的命令忽略hangup信号。同时标准输入会被重定向到/dev/null ，标准输出会被重定向到onhup.out（或$HOME/onhup.out）, 错误输出会被定向到标准输出。
https://www.ibm.com/developerworks/cn/linux/l-cn-nohup/

#### c. 如何在bash脚本中处理用户的Ctrl-C？

 trap可以使你在脚本中捕捉信号。该命令的一般形式为：
 trap name signal(s)
 其中，name是捕捉到信号以后所采取的一系列操作。实际生活中， name一般是一个专门
 用来处理所捕捉信号的函数。Name需要用双引号（“ ”）引起来。Signal就是待捕捉的信号。
 脚本在捕捉到一个信号以后，通常会采取某些行动。最常见的行动包括：
 1) 清除临时文件。
 2) 忽略该信号。
 3) 询问用户是否终止该脚本的运行。
 下表列出了一些最常见的trap命令用法：
 trap "" 2 3 忽略信号2和信号3，用户不能终止该脚本
 trap"commands" 2 3 如果捕捉到信号2或3，就执行相应的commands命令
 trap 2 3  复位信号2和3，用户可以终止该脚本
 也可以使用单引号（‘’）来代替双引号（“”）；其结果是一样的。

### 14. SUID机制与sudo

####a. SUID机制
设置了SUID的程序在运行时可以给使用者以所有者的EUID。
####b. sudo 配置与更新

visudo /etc/sudoers

####c. shell脚本中的sudo

echo "password" | sudo -S visudo
或者在sudoers中配置NOPASSWD选项

####d. 高权限程序一部分功能开放给sudo

john            ALPHA = /usr/bin/su [!-]*, !/usr/bin/su *root*

###15. 文件传输

#### a. 默认参数下，资源的消耗
SSH的资源消耗更高，rsync的资源消耗主要集中于客户端
#### b. 

默认情况下scp 是加密的，scp也可以配置通过ssh传输来加密

#### scp 和rsync的适用场景

## 操作系统命令



### ssh

### 原理

#### ssh agent forward

#### 私钥权限

私钥为660
公钥为644

#### 端口转发

    ssh -R <local port>:<remote host>:<remote port> <SSH hostname>

还可以新建socks代理

https://www.ibm.com/developerworks/cn/linux/l-cn-sshforward/

#### passphrase是什么？

密码短语，实现对私钥的加密


### screen


直接运行screen会创建一个新的screen

参数说明：


-r 重新连接
-ls 查看screen列表
-c file	使用配置文件file，而不使用默认的$HOME/.screenrc
-d|-D [pid.tty.host]	不开启新的screen会话，而是断开其他正在运行的screen会话
-h num	指定历史回滚缓冲区大小为num行
-list|-ls	列出现有screen会话，格式为pid.tty.host
-d -m	启动一个开始就处于断开模式的会话
-r sessionowner/ [pid.tty.host]	重新连接一个断开的会话。多用户模式下连接到其他用户screen会话需要指定sessionowner，需要setuid-root权限
-S sessionname	创建screen会话时为会话指定一个名字
-v	显示screen版本信息
-wipe [match]	同-list，但删掉那些无法连接的会话

screen命令

C-a ?	显示所有键绑定信息
C-a w	显示所有窗口列表
C-a C-a	切换到之前显示的窗口
C-a c	创建一个新的运行shell的窗口并切换到该窗口
C-a n	切换到下一个窗口
C-a p	切换到前一个窗口(与C-a n相对)
C-a 0..9	切换到窗口0..9
C-a a	发送 C-a到当前窗口
C-a d	暂时断开screen会话
C-a k	杀掉当前窗口
C-a [	进入拷贝/回滚模式
http://blog.csdn.net/ritterliu/article/details/50664108

## 文件系统相关

### stty


## 其他

###man文件的使用
man文件一共分为9种，分别是
1   Executable programs or shell commands
2   System calls (functions provided by the kernel)
3   Library calls (functions within program libraries)
4   Special files (usually found in /dev)
5   File formats and conventions eg /etc/passwd
6   Games
7   Miscellaneous  (including  macro  packages  and conventions)
8   System administration commands (usually only for root)
9   Kernel routines [Non standard]

可以用-f 参数查找待查询的内容位于哪些man文件
n：向下查询
N: 向上查询
q: 退出

另外还可以使用info命令，来查找部分命令，工作的说明文档。
