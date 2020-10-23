

### GDB 常用命令


#### step

`step [count]` 继续运行一行，遇到函数会进入其中。指定count相当于运行了count次step。

#### next

`next [count]` 在当前栈内继续运行一行，遇到函数不会进入其中。指定count相当于运行了count次next。

#### finish

`finish` 继续运行直到从当前函数返回。 

#### info

`info sources` 查看源码文件信息
`info functions` 查看源码函数信息

#### break/b


`break cstress.c:136` 在指定文件的指定行设置断点

### 使用技巧

#### 1. 传递被调试程序参数

```
gdb --args executablename arg1 arg2 arg3
```

