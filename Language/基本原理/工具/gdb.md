

### GDB 常用命令

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

