# Bash shell

## 内置操作符

### 数组与关联数组


关联数组

关联数组需要事先申明

    #ass_array被申明为一个关联数组
    declare -A ass_array
    ass_array[index1]=val1
    # ${!array_var[*]} 列出关联数组（普通数组）的索引
    # ${!array_var[@]} 同上
    echo ${!array_var[*]}
    # ${array_var[*]} 列出关联数组（普通数组）的值
    # ${array_var[@]} 同上
    echo ${array_var[*]}

### let

可以执行基本的算术操作。例如自增，自减等。
```
let no++
let no--
let no+=6
```

## 特殊参数
```
 Special Parameters
       The shell treats several parameters specially.  These parameters may only be referenced; assignment to them
       is not allowed.
      *      Expands  to  the  positional parameters, starting from one.  When the expansion is not within double
              quotes, each positional parameter expands to a separate word.  In contexts where  it  is  performed,
              those words are subject to further word splitting and pathname expansion.  When the expansion occurs
              within double quotes, it expands to a single word with the value of each parameter separated by  the
              first character of the IFS special variable.  That is, "$*" is equivalent to "$1c$2c...", where c is
              the first character of the value of the IFS variable.  If IFS is unset, the parameters are separated
              by spaces.  If IFS is null, the parameters are joined without intervening separators.
       @      Expands  to  the  positional parameters, starting from one.  When the expansion occurs within double
              quotes, each parameter expands to a separate word.  That is, "$@" is equivalent to "$1" "$2" ...  If
              the  double-quoted  expansion  occurs  within a word, the expansion of the first parameter is joined
              with the beginning part of the original word, and the expansion of the last parameter is joined with
              the  last part of the original word.  When there are no positional parameters, "$@" and $@ expand to
              nothing (i.e., they are removed).
       #      Expands to the number of positional parameters in decimal.
       ?      Expands to the exit status of the most recently executed foreground pipeline.
       -      Expands to the current option flags as specified upon invocation, by the  set  builtin  command,  or
              those set by the shell itself (such as the -i option).
       $      Expands  to the process ID of the shell.  In a () subshell, it expands to the process ID of the cur‐
              rent shell, not the subshell.
       !      Expands to the process ID of the job most recently placed into the background, whether  executed  as
              an asynchronous command or using the bg builtin (see JOB CONTROL below).
       0      Expands  to the name of the shell or shell script.  This is set at shell initialization.  If bash is
              invoked with a file of commands, $0 is set to the name of that file.  If bash is started with the -c
              option,  then  $0  is  set to the first argument after the string to be executed, if one is present.
              Otherwise, it is set to the filename used to invoke bash, as given by argument zero.
       _      At shell startup, set to the absolute pathname used to invoke the shell or shell script  being  exe‐
              cuted  as passed in the environment or argument list.  Subsequently, expands to the last argument to
              the previous command, after expansion.  Also set to the full pathname used to  invoke  each  command
              executed and placed in the environment exported to that command.  When checking mail, this parameter
              holds the name of the mail file currently being checked.
```


### 函数

函数的定义方式如下：

```
fname()
{
statements
}
```

在函数内部参数的访问通过如下形式：
$1 第一个参数
$2 第二个参数
$@ 全部参数 "$1" "$2"
$* 全部参数 "$1c$2" c是IFS第一个字符（IFS内部字段分隔符）

### 内建命令

#### cd

#### read

read命令可以从输入中读取字符。并且限定读取字符的数量以及超时时间。

-n：指定读取的字符数量
-p：显示提示信息
-t：设置超时时间
-d：指定分隔符

### 语法

#### 循环

for循环

while循环
```
while condition #条件为真则循环
do
   statement
done
```
util循环
```
util condition #会一直循环直到条件为真
do
   statement
done
```
### 补充内容

可以将命令放入（）中，从而生成独立的子shell
