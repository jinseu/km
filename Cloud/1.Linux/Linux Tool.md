cfdisk、dhcpcd、dump、e2fsck、fdisk、halt、ifconfig

## bash

x
## 文本处理









skip



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




## 文件



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