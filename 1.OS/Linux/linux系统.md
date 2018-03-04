# linux shell

* [1 bash](#1)
* [1.1 bash](#1.1)
* [1.2 bash](#1.2)

<h2 id = "1"> 1. bash</h2>

<h3 id = "1.1"> 1.1 bash的运行模式 </h3>

Bash有几种不同的运行模式，login shell与non-login shell，interactive shell与non-interactive shell（比如执行shell脚本）。这两种分类方法是交叉的，也就是说一个login shell可能是一个interactive shell，也可能是个non-interactive shell。

login shell和interactive shell的定义如下：
       
A login shell is one whose first character of argument zero is a -, or one started with the --login option.

An  interactive  shell is one started without non-option arguments and without the -c option whose standard input and error are both connected to terminals (as determined by isatty(3)), or one started  with  the  -i option.   PS1 is set and $- includes i if bash is interactive, allowing a shell script or a startup file to test this state.


在下列情况下，我们可以获得一个login shell：

1. 登录系统时获得的顶层shell，无论是通过本地终端登录，还是通过网络ssh登录。这种情况下获得的login shell是一个交互式shell。
2. 在终端下使用--login选项调用bash，可以获得一个交互式login shell。
3. 在脚本中使用--login选项调用bash（比如在shell脚本第一行做如下指定：#!/bin/bash --login），此时得到一个非交互式的login shell。
4. 使用"su -"切换到指定用户时，获得此用户的login shell。如果不使用"-"，则获得non-login shell。

<h3 id = "1.2"> 1.2 bash 的配置文件 </h3>

login shell与non-login shell的主要区别在于它们启动时会读取不同的配置文件，从而导致环境不一样。

login shell启动时首先读取/etc/profile全局配置，然后依次查找~/.bash_profile、~/.bash_login、~/.profile三个配置文件，并且读取第一个找到的并且可读的文件。login shell退出时读取并执行~/.bash_logout中的命令。

when bash is invoked as an interactive login shell, or as a non-interactive shell with the --login  option, it  first  reads and executes commands from the file /etc/profile, if that file exists.  After reading that file, it looks for ~/.bash_profile, ~/.bash_login, and ~/.profile, in that order, and  reads  and  executes commands from the first one that exists and is readable.  The --noprofile option may be used when the shell is started to inhibit this behavior.When a login shell exits, bash reads and executes commands from the file ~/.bash_logout, if it exists.

交互式的non-login shell启动时读取~/.bashrc资源文件。非交互式的non-login shell不读取上述所有配置文件，而是查找环境变量BASH_ENV，读取并执行BASH_ENV指向的文件中的命令。

When an interactive shell that is not a login shell is started,  bash  reads  and  executes  commands  from /etc/bash.bashrc  and  ~/.bashrc,  if these files exist.  This may be inhibited by using the --norc option.The --rcfile file option will force bash to read and execute commands from file instead of /etc/bash.bashrc and ~/.bashrc.

When  bash  is  started  non-interactively,  to  run a shell script, for example, it looks for the variable BASH_ENV in the environment, expands its value if it appears there, and uses the expanded value as the name of a file to read and execute.  Bash behaves as if the following command were executed:
     
    if [ -n "$BASH_ENV" ]; then . "$BASH_ENV"; fi

but the value of the PATH variable is not used to search for the filename.

如果使用命令"sh"调用bash，bash会尽可能保持向后兼容。作为login shell启动时，bash依次读取/etc/profile和~/.profile配置文件。作为non-login shell启动时，bash读取环境变量ENV指向的文件。

另外通过网络登录(ssh)时，bash会尝试读取~/.bashrc 以及 ~/.bashrc中的配置。

Bash  attempts to determine when it is being run with its standard input connected to a network connection,as when executed by the remote shell daemon, usually rshd, or the secure shell daemon sshd.  If bash deter‐mines  it  is  being  run  in this fashion, it reads and executes commands from ~/.bashrc and ~/.bashrc, if these files exist and are readable.  It will not do this if invoked as sh.  The --norc option may  be  used to inhibit this behavior, and the --rcfile option may be used to force another file to be read, but neither rshd nor sshd generally invoke the shell with those options or allow them to be specified.


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
$date
2016年 08月 24日 星期三 16:42:50 HKT
#可以用+%的形式，指定打印结果，例如，+%s显示unix时间戳
$date +%s
1472028210
#--date参数可以指定日期作为输入，例如以下例子，将一个指定的日期，显示星期几
$date --date "Feb 16 1992" +%A
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

### 10 apt-get和debian包

####apt-get update，以及apt-get install mtr工作原理
执行apt-get update

程序分析/etc/apt/sources.list，获取包列表。

ubuntu的软件源类型有4种分别为：
main  Canonical-support open source softs  即canoniacl公司(ubuntu幕后的开发团队)支持的开源软件
multiverse 各开源社区支持的开源软件
restricted 有专利限制的设备驱动软件(主要是显卡驱动)
universe   有版权限制的软件(但个人在一定条件下可以自由使用)

ubuntu用于更新的软件源类型有4种分别为：
security  重要的安全更新
updates   建议的更新
proposed  pre-release updates
backports unsupported updates

向source.list文件添加源的格式为：
deb|deb-src  http://path/to/ubuntu/               ubuntu发行版名称 | 发行版名称-更新的类型      软件源类型 ...

自动连网寻找list中对应的Packages/Sources/Release列表文件，如果有更新则下载之，存入/var/lib/apt/lists/目录

在下载会先到对应目录下载Package.gz文件，获取包信息，包括包名，优先级，类型，维护者，架构，源文件（source），版本号，依赖包，冲突性信息，包大小，文件的下载路径，MD5sum，SHA1等。

然后 apt-get install 相应的包 ，下载并安装。



####怎样升级一个软件

apt-get update
apt-get upgrade 

注：
aptitude 命令，它为 APT 包管理功能提供了一个基于文本的全屏界面（使用 ncurses）

###11 安全习惯

SSH AGENT
https://wiki.archlinux.org/index.php/SSH_keys_(%E7%AE%80%E4%BD%93%E4%B8%AD%E6%96%87)

###12 分区与挂载

### 分区类型
IDE 驱动器上有三种类型的分区：主分区、逻辑分区 和扩展分区。分区表 位于磁盘主引导记录 (MBR) 之中。分区表 位于磁盘主引导记录 (MBR) 中。MBR 是磁盘上的第一个扇区，因而分区表在其中所占空间不大。这限制了一个磁盘上的主分区数量，最多为 4 个。如果需要 4 个以上的主分区（往往需要 4 个以上的主分区），其中一个主分区就必须以扩展分区的形式出现。扩展分区是一个或多个逻辑分区的容器。这样，在一个使用 MBR 布局的驱动器上就可以有4 个以上的分区。

####a. 将所有目录置于同一分区的优势和劣势

####b. LVM

LVM利用Linux内核的device-mapper来实现存储系统的虚拟化（系统分区独立于底层硬件）。 通过LVM，你可以实现存储空间的抽象化并在上面建立虚拟分区（virtual partitions），可以更简便地扩大和缩小分区，可以增删分区时无需担心某个硬盘上没有足够的连续空间。LVM由以下四部分构成：
物理卷Physical volume (PV)：可以在上面建立卷组的媒介，可以是硬盘分区，也可以是硬盘本身或者回环文件（loopback file）。物理卷包括一个特殊的header，其余部分被切割为一块块物理区域（physical extents）。
卷组Volume group (VG)：将一组物理卷收集为一个管理单元。
逻辑卷Logical volume (LV)：虚拟分区，由物理区域（physical extents）组成。
物理区域Physical extent (PE)：硬盘可供指派给逻辑卷的最小单位（通常为4MB）。

诸如 mkfs 和 mount 文件系统命令使用 /dev/<vg-name>/<lv-name> 这样的名称访问 LV。
vgcreate，lvcreate可以分别创建vg和lv



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




### grep 

grep 命令可以对多个文件进行搜索

-i 不区分大小写
-c 统计包含匹配的行数
-n 输出行号
-v 反向匹配（显示不匹配的行）
-A --after-context=NUM 打印出匹配结果之后的NUM行
-B --before-context=NUM 打印出匹配结果之前的NUM行
-C --context=NUM  打印出匹配结果之前以及之后的NUM行
-E 以正则表达式解释PATTERN（egrep）
-F 以严格的字符串解释PATTERN
-R 递归读取目录
-q 找到就退出，不输出任何信息（成功返回0）
-o 只打印匹一行中匹配的内容

--color=auto 在输出行中重点标注出匹配到的单词

样例：
    
    #打印匹配项的数量
    echo -e "1 2 3 4"|grep -E -o '[0-9]' | wc -l

### ls 

-i 打印出inode
-R 打印出子目录

默认根据name排序，可以根据--sort=WORD来改变，包括 size (-S), time (-t), version (-v), extension (-X)
ls 命令中的第二行指连接数，

ls -lc filename         列出文件的 ctime
ls -lu filename         列出文件的 atime
ls -l filename          列出文件的 mtime 

关于inode

https://www.ibm.com/developerworks/cn/aix/library/au-speakingunix14/

### df/du

du的工作原理

du命令会对待统计文件逐个调用fstat这个系统调用，获取文件大小。它的数据是基于文件获取的，所以有很大的灵活性，不一定非要针对一个分区，可以跨越多个分区操作。如果针对的目录中文件很多，du速度就会很慢了。

df的工作原理

df命令使用的事statfs这个系统调用，直接读取分区的超级块信息获取分区使用情况。它的数据是基于分区元数据的，所以只能针对整个分区。由于df直接读取超级块，所以运行速度不受文件多少影响。

du和df不一致情况模拟

常见的df和du不一致情况就是文件删除的问题。当一个文件被删除后，在文件系统 目录中已经不可见了，所以du就不会再统计它了。然而如果此时还有运行的进程持有这个已经被删除了的文件的句柄，那么这个文件就不会真正在磁盘中被删除， 分区超级块中的信息也就不会更改。这样df仍旧会统计这个被删除了的文件。

df -i 显示inode占用率
df -T 可以显示文件系统的类型
du -s 参数可以仅仅显示该目录下的总大小

skip

### touch

可以用touch -m 来修改文件的修改时间,-a 修改access时间
也可以修改目录
-c 参数可以避免创建文件




### cut

用法如下 cut 

-f 指定列（从1开始） 
-d '分隔符'
-c --characters=LIST select only these characters
-b  --bytes=LIST select only these bytes
--complement 补集，打印除指定的列外的其他列
--output-delimiter=STRING 当使用-c -b参数指定多个字段时，需要显示定界符，即显示{1}{STRING}{2}
样例：

    cut -f1-6  显示列范围,
    cut -c10-15 显示每行的第10到第15个字符
    cut -f1 --complement /etc/passwd -d ':'

### sort

-n 采取数字排序
-t 制定分隔符，不能指定多个字符
-k 指定第几列
-r 反向排序
-u unique模式，相同的只显示一次

-T 参数主要用于排序大文件，并且可以通过-S 指定最多占用的内存

例如：
    
    #用sort实现对IP地址的排序
    >sort -t '.' -k 1,1n -k 2,2n -k3,3n -k4,4n ./ip.txt
    10.2.2.2
    10.2.2.11
    10.3.2.11
    #用来处理多个文件，将多个文件排序
    >cat file1 file2 file3 | sort > outfile
    #merge 多个文件为一个大文件
    >sort -m file1 file2 file3 > outfile

另外，需要说明的是
- 可以使用键和偏移量。偏移量是用点与键相分隔的，比如在 -k 1.3,5.7 中，表示排序键应当从第 1 个字段的第 3 个字符开始，到第 5 个字段的第 7 个字符结束（偏移量也是从 1 开始编号的）。例如，可以用偏移量来对 Apache 日志进行排序；键和偏移量表示法让我跳过了日期字段。
- -b (忽略空白字符并将行中的第一个非空白字符当做是排序键的开始。还有，如果您使用该选项，那么将从第一个非空白字符开始计算偏移量（当字段分隔符不是空白字符，且字段可能包含以空白字符开头的字符串时，这非常有用））。
- -d （只将字母、数字和空白用作排序键）
- -f （关闭大小写区分，认为小写和大写字符是一样的）
- -i （忽略非打印的 ASCII 字符）
- -M （使用三个字母的月份名称缩写：JAN、FEB、MAR … 来对行进行排序）
- -n （只用数字、- 和逗号或另外一个千位分隔符对行进行排序）。
- -g  --general-numeric-sort 支持科学技术法
- -C  =--check=quiet 检查一个文件是否排过序

参考资料：
http://www.ibm.com/developerworks/cn/linux/l-tip-prompt/l-tiptex4/



### tail/head ###

head
-c [-]k 打印前K字节，有'-'参数，打印除了最后k字节的内容。默认为10
-n [-]K 打印前K行，有'-'参数，打印除了最后k行的内容

tail
-c [+]K 字符 打印后K字节，有'+'参数，打印除了最前k-1字节的内容。默认为10
-n [+]K 行   打印前K行，有'+'参数，打印除了最前k-1行的内容
-f --follow 会显示文件中最新的几行，然后在指定的文件中添加新行时显示它们。
--pid=PID 与-f参数一起使用，可以在PID进程退出后，停止显示，即结束tail.
     
     #如何用tail显示25行以后的内容。
     tail -n +26 filename
     #以16进制，显示/dev/sda 前512字节的内容
     dd if=/dev/vda bs=510 count=1 2>/dev/null|tail -c 64

### iostat 命令时间间隔设定为 10 秒，总共执行３次。

iostat -d 10 3 

http://www.ibm.com/developerworks/cn/aix/library/0910_jiangpeng_unixdisk/

tps

Indicate the number of transfers per second that were issued to the device. A transfer is  an I/O  request  to  the  device.  Multiple  logical  requests can be combined into a single I/O request to the device. A transfer is of indeterminate size.


### netstat

--numeric-ports 可以避免端口被翻译
-p --program 参数可以显示对应的进程PID

netstat中的所有状态如下所示
       
       FREE   The socket is not allocated

       LISTENING
              The socket is listening for a connection request.  Such sockets are only included in the  output  if
              you specify the --listening (-l) or --all (-a) option.

       CONNECTING
              The socket is about to establish a connection.

       CONNECTED
              The socket is connected.

       DISCONNECTING
              The socket is disconnecting.

       (empty)
              The socket is not connected to another one.

       UNKNOWN
              This state should never happen.


route 命令就可以查看路由表

### wget

可以使用如下参数发送POST请求 
--post-data=string
--post-file=file

可以使用
-c 断点续传

-O file
--output-document=file
 The documents will not be written to the appropriate files, but all will be concatenated together and written to file.  If - is used as file, documents will be printed to standard output, disabling link conversion.  (Use ./- to print to a file literally named -.)

-o logfile
--output-file=logfile
Log all messages to logfile.  The messages are normally reported to standard error.

### ifconfig

/etc/network/interfaces

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

### dd
dd是一个转换和复制文件的命令

用法示例：
       dd  [--help]  [--version]  [if=file] [of=file] [ibs=bytes] [obs=bytes] [bs=bytes] [cbs=bytes] [skip=blocks]
       [seek=blocks] [count=blocks] [conv={ascii, ebcdic,  ibm,  block,  unblock,  lcase,  ucase,  swab,  noerror,
       notrunc, sync}]


当进行非强制的转换的时候，使用指定的输入和输出块大小拷贝文件 （默认是从标准输入到标准输出。）

 它每次从输入读取指定大小的一个块（默认是512字节）。  如果使用  bs=bytes  选项，并且没有转换，除了指定 sync, noerror,    或    notrunc    之外，那么dd将把全部读到的数据（可以比请求读的少）    写到独立的输出块去。这个输出块的长度和读到的数据                       完全一样，除非指定使用转换，那样的话，数据结尾处将追加NUL字符（或空格，见下）。

 其他情况下，输入的时候每次读一个块，然后处理，并将       输出结果收集起来，最后写到指定大小的数据块中去。最终的输出块可能会比指定的大小短一些。

数字值选项（以字节或块为单位）后面可以跟一个乘数：       k=1024，b=512，w=2，c=1（w和c是GNU扩展语法。最好别使用w，因为在system V中，它表示2，在4.2  BSD中，它  表示4）。两个或更多的数值表达式可以通过“x”乘起来。  GEU fileutils  4.0并且允许在数据块大小的叙述中使用 下列乘法后缀（用bs=，cbs=，obs=）：M=1048576，G=1073741824，同理可得T，P，E，Z，Y。D后缀表示数值是以     十进制表示的：kD=1000      MD=1000000      GD=1000000000等等。
（注意，在ls、df、du命令中，M等标记的大小是由环境 变量确定的，而在DD中，它的值是固定的。）


### uniq

uniq命令消除重复的内容，前提是文件必须是排过序的。

参数：
-c 显示出现的次数
-d 只显示重复的行
-u 只显示唯一的行

### comm

comm文件用于两个文件之间的比较，可以找到交集以及差集等。

comm a.txt b.txt 完整情况下，其结果由三列组成。第一列为只出现在a中的行，第二列为只出现在B中的行，第三列为即出现在A也出现在B中的行。

参数

-1 从输出中删除第一列
-2 从输出中删除第二列
-3 从输出中删除第三列



### losetup

### echo 

-e 启用转义字符
-n 不打印结尾的换行符

### 自定义文件描述符

## 终端相关命令

### tput

该命令可以设置或者查询控制台相关的信息。常见的使用方式如下：

    #获取终端的列数
    tput cols
    #获取终端的行数
    tput lines
    #设置前景色（0~7）
    tput setf 0
    #设置背景色（0~7）
    tput setb 0 
    #设置为粗体
    tput bold
    #设置下划线的起止
    tput smul
    tput rmul

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