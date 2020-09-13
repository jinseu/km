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