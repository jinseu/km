cfdisk、dhcpcd、dump、e2fsck、fdisk、halt、ifconfig

## bash

### bash的运行模式

Bash有几种不同的运行模式，`login shell`与`non-login shell`，`interactive shell`与`non-interactive shell`（比如执行shell脚本）。这两种分类方法是交叉的，也就是说一个`login shell`可能是一个`interactive shell`，也可能是个`non-interactive shell`

在下列情况下，可以获得一个login shell：

1. 登录系统时获得的顶层shell，无论是通过本地终端登录，还是通过网络ssh登录。这种情况下获得的login shell是一个交互式shell。
2. 在终端下使用`--login`选项调用bash，可以获得一个交互式login shell。
3. 在脚本中使用`--login`选项调用bash（比如在shell脚本第一行做如下指定：`#!/bin/bash --login`），此时得到一个非交互式的login shell。
4. 使用`su -`切换到指定用户时，获得此用户的`login shell`。如果不使用`-`，则获得`non-login shell`。

`login shell`与`non-login shell`的主要区别在于它们启动时会读取不同的配置文件，从而导致环境不一样。

`login shell`启动时首先读取`/etc/profile`全局配置，然后依次查找`~/.bash_profile`、`~/.bash_login`、`~/.profile`三个配置文件，并且读取第一个找到的并且可读的文件。login shell退出时读取并执行`~/.bash_logout`中的命令。

交互式的`non-login shell`启动时读取`~/.bashrc`资源文件。非交互式的non-login shell不读取上述所有配置文件，而是查找环境变量BASH_ENV，读取并执行BASH_ENV指向的文件中的命令。

如果使用命令"sh"调用bash，bash会尽可能保持向后兼容。作为login shell启动时，bash依次读取/etc/profile和~/.profile配置文件。作为non-login shell启动时，bash读取环境变量ENV指向的文件。

另外通过网络登录(ssh)时，bash会尝试读取~/.bashrc 以及 ~/.bashrc中的配置

## 文本处理

### awk

#### awk 程序的组成

awk 程序是由 一系列的`pattern {action}` 组成的。

pattern 可以是以下几种：

1. BEGIN （用于匹配运行前）
2. END   （用于匹配运行后）
3. expression
4. expression , expression 匹配多个条件，所有的行分别匹配每个expression

同时pattern 可以指定 `&&`, `||`, `!` 分别表示与、或、非关系

如果不指定pattern，默认匹配全部，如果不指定action，默认print

```
cat /etc/passwd | awk -F ":" '{print $1}
```

#### awk 内建变量

| 变量 | 说明|
|------|----|
|$0	| 当前记录（这个变量中存放着整个行的内容）|
|$1~$n	|当前记录的第n个字段，字段间由FS分隔 |
|FS	| 输入字段分隔符 默认是空格或Tab |
|NF	| 当前记录中的字段个数，就是有多少列 |
|NR	| 已经读出的记录数，就是行号，从1开始，如果有多个文件话，这个值也是不断累加中。|
|FNR | 当前记录数，与NR不同的是，这个值会是各个文件自己的行号 |
|RS	| 输入的记录分隔符， 默认为换行符 |
|OFS | 输出字段分隔符， 默认也是空格 |
|ORS | 输出的记录分隔符，默认为换行符 |
|FILENAME | 当前输入文件的名字 |

注意 `$NF` 表示最后一个字段

#### 控制语句

形式与C++类似

1. if-else
2. while
3. for


#### 输入输出

1. 输出重定向
```
{ print($1, $3) > ($3 > 100 ? "bigpop" : "smallpop") }
```
2. 输出到管道
```
print | command
```

#### awk 内建函数

1. length 获取指定变量的长度
2. close 关闭管道或者文件
3. getline


#### 自定义函数

在awk中，可以使用function关键字，自定义函数。

```
}   statementspatameter-list) {
```

#### 例子

1. 打印最长的行的长度
```
awk '{if(length($0) > kk) kk = length($0)} END {print kk}' /etc/passwd
# 功能上类似于
wc -L /etc/passwd
```

### convmv

转换文件目录编码

```
convmv -f 源编码 -t 新编码 [选项] 文件名
```

常用参数：

```
-r 递归处理子文件夹
--notest 真正进行操作，请注意在默认情况下是不对文件进行真实操作的，而只是试验。
--list 显示所有支持的编码
--unescap 可以做一下转义，比如把%20变成空格
```

样例

```
convmv -f UTF-8 -t GBK -r --notest ./var
```

### cut

从文件中的每行中删除指定部分

#### 参数说明

* `-f` 指定列（从1开始）
* `-d` '分隔符'
* `-c --characters=LIST` select only these characters
* `-b  --bytes=LIST` select only these bytes
* `--complement` 补集，打印除指定的列外的其他列
* `--output-delimiter=STRING` 当使用-c -b参数指定多个字段时，需要显示定界符，即显示{1}{STRING}{2}

#### 样例

```
cut -f1-6  显示列范围,
cut -c10-15 显示每行的第10到第15个字符
cut -f1 --complement /etc/passwd -d ':'
```

### df/du

du的工作原理

du命令会对待统计文件逐个调用fstat这个系统调用，获取文件大小。它的数据是基于文件获取的，所以有很大的灵活性，不一定非要针对一个分区，可以跨越多个分区操作。如果针对的目录中文件很多，du速度就会很慢了。

df的工作原理

df命令使用的事statfs这个系统调用，直接读取分区的超级块信息获取分区使用情况。它的数据是基于分区元数据的，所以只能针对整个分区。由于df直接读取超级块，所以运行速度不受文件多少影响。

du和df不一致情况模拟

常见的df和du不一致情况就是文件删除的问题。当一个文件被删除后，在文件系统 目录中已经不可见了，所以du就不会再统计它了。然而如果此时还有运行的进程持有这个已经被删除了的文件的句柄，那么这个文件就不会真正在磁>盘中被删除， 分区超级块中的信息也就不会更改。这样df仍旧会统计这个被删除了的文件。

#### 参数说明

df -i 显示inode占用率
df -T 可以显示文件系统的类型
du -s 参数可以仅仅显示该目录下的总大小

skip

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
- -b (忽略空白字符并将行中的第一个非空白字符当做是排序键的开始。还有，如果您使用该选项，那么将从第一个非空白字符开始计算偏移量（当字段分隔符不是空白字符，且字段可能包含以空白字符开头的字符串时，这非常有用
））。
- -d （只将字母、数字和空白用作排序键）
- -f （关闭大小写区分，认为小写和大写字符是一样的）
- -i （忽略非打印的 ASCII 字符）
- -M （使用三个字母的月份名称缩写：JAN、FEB、MAR … 来对行进行排序）
- -n （只用数字、- 和逗号或另外一个千位分隔符对行进行排序）。
- -g  --general-numeric-sort 支持科学技术法
- -C  =--check=quiet 检查一个文件是否排过序

参考资料：
http://www.ibm.com/developerworks/cn/linux/l-tip-prompt/l-tiptex4/

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

```
#打印匹配项的数量
echo -e "1 2 3 4"|grep -E -o '[0-9]' | wc -l
```

### wc

打印文件的行数，字节数等

```
wc [OPTION]... [FILE]...
wc [OPTION]... --files0-from=F
```

1. -c, --bytes 字节数
2. -m, --chars 字符数
3. -l, --lines 行数
4. --files0-from=F 从F中读取用NULL分隔的文件名，F为-表示从命令行读取
5. -L, --max-line-length 最长的行的行数
6. -w, --words 单词数

例如，计算某个目录以及子目录下，所有文件的行数
```
find ./running/ -type f -print0 | wc -l --files0-from=-
```

### sed

参考资料

> https://coolshell.cn/articles/9070.html%## 其他


### tail/head ###

head
-c [-]k 打印前K字节，有'-'参数，打印除了最后k字节的内容。默认为10
-n [-]K 打印前K行，有'-'参数，打印除了最后k行的内容

tail
-c [+]K 字符 打印后K字节，有'+'参数，打印除了最前k-1字节的内容。默认为10
-n [+]K 行   打印前K行，有'+'参数，打印除了最前k-1行的内容
-f --follow 会显示文件中最新的几行，然后在指定的文件中添加新行时显示它们。
--pid=PID 与-f参数一起使用，可以在PID进程退出后，停止显示，即结束tail.

```
#如何用tail显示25行以后的内容。
tail -n +26 filename
#以16进制，显示/dev/sda 前512字节的内容
dd if=/dev/vda bs=510 count=1 2>/dev/null|tail -c 64
```


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

## 文件

### chown

### chmod

lsattr

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
3. x权限：拥有目录的x权限表示用户可以进入该目录成为工作目录，能不能进入一个目录，只与该目录的x权限有关，如果用户对于某个目录不具有x权限，则无法切换到该目录下，也就无法执行该目录下的任何命令，即使具有该目>录的r权限。且如果用户对于某目录不具有x权限，则该用户不能查询该目录下的文件的内容，注意：指的是内容，如果有r 权限是可以查看该目录下的文件名列表或子目录列表的。所以要开放目录给任何人浏览时，应该至少要给与r>及x权限。

样例

    #更改权限的实例
    chmod u=rwx g=rw o=r filename
    #递归更改
    chmod 777 . -R



### find

命令格式

```
find [-H] [-L] [-P] [-D debugopts] [-Olevel] [path...] [expression]
```

#### 参数列表

1. `-name` 根据文件名进行查找
2. `-iname` 根据文件名进行查找，并忽略大小写
3. `-o`  表示条件之间是or关系
4. `-path` 匹配文件路径
5. `-regex` 根据正则表达式来匹配路径
6. `!`否定参数，例如`! -name ".txt"` 找到所有不以txt结尾的文件名
7. `-maxdepth` 最大递归深度
8. `-mindepth` 最小递归深度
9. `-atime {n} -mtime {n} -ctime {n}` 按照文件被读取或者执行的时间、文件内容修改时间、文件的i结点被修改的时间查找，n整数单位为天。-表示小于，+表示大于，无符号则表示恰好在该天修改的。
10. `-amin {n} -mmin {n} -cmin {n}` 按照修改时间查找，整数，单位为分钟
11. `-anewer -cnewer -newer` 按照访问时间找到比某个文件更新的文件
12. `-size` 按照大小查找 -表示小于，+表示大于，无符号则表示恰好这么大。可以使用的单位包括b,c,w,k,M,G
13. `-user`   按照文件所有权进行查找
14. `-exec`  可以对查询结果，执行其他命令。

#### 1. find能根据哪些条件来查找文件

文件名，文件权限，用户名，修改时间，访问时间（访问时间是文件最后一次被读取的时间），创建时间（文件元数据最后一次被更改的时间），类型，大小，目录的深度，inode

常见的type有 b（块文件） d（目录） c（字符文件） p（管道文件） l（链接） f（普通文件） s（socket文件）

#### 2. find能否根据文件内容来检索

不可以，本身只是根据文件的属性进行查找的

#### 3. find删除找到的文件的

```
#删除查找到的文件
find . -name "*.md" -delete
find . -name "*.dump" | xargs rm -f
find . -type f -name "*.dump" -exec rm -f '{}' \;
#查找后缀为txt或pdf的文件
find . \( -name "*.txt" -o -name "*.pdf"\)
#找到比file.txt更新的文件
find . -type f -newer file.txt
#跳过指定的目录
find . \( -name ".git" -prune \) -o \( -type f \)
```
    
#### 4. find找到目录的最大深度

```
find /home/kiva/cephfs/var/ -type d -printf '%d %p\n' | sort -rn | head -1
```

### ln

Liunux 硬链接允许一个文件拥有多个有效的路径名，这样用户就可以建立硬链接指向同一个文件。即多个文件指向同一个inode，删除一个链接并不会影响索引节点本身和其他链接，只有当所有与之相关的连接被删除后，该文件才会
被删除。

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

https://www.ibm.com/developerworks/cn/linux/l-cn-hardandsymb-links/

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

### touch

可以用touch -m 来修改文件的修改时间,-a 修改access时间
也可以修改目录
-c 参数可以避免创建文件


### dd

dd是一个转换和复制文件的命令

用法示例：
       dd  [--help]  [--version]  [if=file] [of=file] [ibs=bytes] [obs=bytes] [bs=bytes] [cbs=bytes] [skip=blocks]
       [seek=blocks] [count=blocks] [conv={ascii, ebcdic,  ibm,  block,  unblock,  lcase,  ucase,  swab,  noerror,
       notrunc, sync}]


当进行非强制的转换的时候，使用指定的输入和输出块大小拷贝文件 （默认是从标准输入到标准输出。）

 它每次从输入读取指定大小的一个块（默认是512字节）。  如果使用  bs=bytes  选项，并且没有转换，除了指定 sync, noerror,    或    notrunc    之外，那么dd将把全部读到的数据（可以比请求读的少）    写到独立的输
出块去。这个输出块的长度和读到的数据                       完全一样，除非指定使用转换，那样的话，数据结尾处将追加NUL字符（或空格，见下）。

 其他情况下，输入的时候每次读一个块，然后处理，并将       输出结果收集起来，最后写到指定大小的数据块中去。最终的输出块可能会比指定的大小短一些。

数字值选项（以字节或块为单位）后面可以跟一个乘数：       k=1024，b=512，w=2，c=1（w和c是GNU扩展语法。最好别使用w，因为在system V中，它表示2，在4.2  BSD中，它  表示4）。两个或更多的数值表达式可以通过“x”乘>起来。  GEU fileutils  4.0并且允许在数据块大小的叙述中使用 下列乘法后缀（用bs=，cbs=，obs=）：M=1048576，G=1073741824，同理可得T，P，E，Z，Y。D后缀表示数值是以     十进制表示的：kD=1000      MD=1000000      GD=1000000000等等。
（注意，在ls、df、du命令中，M等标记的大小是由环境 变量确定的，而在DD中，它的值是固定的。）




## IO与网络

### iostat 命令时间间隔设定为 10 秒，总共执行３次。

iostat -d 10 3

http://www.ibm.com/developerworks/cn/aix/library/0910_jiangpeng_unixdisk/

tps

Indicate the number of transfers per second that were issued to the device. A transfer is  an I/O  request  to  the  device.  Multiple  logical  requests can be combined into a single I/O request to the device. A transfer is of indeterminate size.

在linux系统中，还有一些杂项命令，用来辅助用户使用这些命令相对比较简单，但是却非常有用。

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
              

### which

输出作为参数的命令所在的位置，即命令所对应的待执行文件的位置
```
$ which ls
/bin/ls
```

### whereis

whereis与which类似，但是不仅返回命令的路径，还能够打印出其对应的man手册以及源代码的路径（如果有的话）。

```
$ whereis ls
ls: /bin/ls /usr/share/man/man1/ls.1.gz
```

### file

file 用来确定文件的类型，file命令在具体使用时，参数相对较多。
```
$ file /bin/ls
/bin/ls: ELF 64-bit LSB executable, x86-64, version 1 (SYSV), dynamically linked, interpreter /lib64/ld-linux-x86-64.so.2, for GNU/Linux 2.6.32, BuildID[sha1]=a0823e17cafbe5b2296346216445566840fdfd88, stripped
```

### ldd

ldd 命令用来打印可执行文件依赖的共享库

```
$ldd /usr/bin/ls

```
### whatis

whatis 命令会输出作为参数的命令的简短描述信息。这些描述系信息的来源则是man手册。

```
$ whatis file
file (1)             - determine file type
```
### wall

可以用来给当前登录的所有用户发送广播消息。

可以使用mesg命令来屏蔽或者打开接收消息，选项，但如果是root用户，无论mesg启动与否，消息都会显示出来。

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

### echo

-e 启用转义字符
-n 不打印结尾的换行符

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

### 用户与组

Linux用户保存在/etc/passwd，密码则是保存在/etc/shadow中。
新增用户：useradd
修改密码：passwd
修改用户：usermod  可以用usermod –L 冻结用户，-U解锁
删除用户：userdel
新增组：groupadd
删除组：groupdel


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