## GCC

GCC 是一系列编译器的集合，可以编译C，C++在内的多种语言

### 编译

#### 1. 预编译

使用 `-E` 参数可以执行预编译，预编译会执行代码中的预编译指令，具体操作包括

1. 展开宏定义
2. 处理预编译指令
3. 将使用`#inlcude`包含的文件插入到预编译指令的位置
4. 删除注释
5. 添加行号和文件名标识，便于调试时编译错误能够行号信息 
6. 保留`#pragma`编译器指令

```
gcc -E ./hello.c
```

hello.c 内容如下

```
#include<stdio.h>

int main(){
    printf("hello world");
    return 0;
}
```


可以在输出结果中看到，如下信息，即3，5两个操作的结果

```
# 913 "/usr/include/stdio.h" 3 4
extern void flockfile (FILE *__stream) __attribute__ ((__nothrow__ , __leaf__));
```

### 2. 编译

使用`-S`参数可以执行编译，生成汇编代码

```
gcc -S ./hello.c -o ./hello.s
```

hello.s 内容如下

```
        .file   "hello.c"
        .section        .rodata
.LC0:
        .string "hello world"
        .text
        .globl  main
        .type   main, @function
main:
.LFB0:
        .cfi_startproc
        pushq   %rbp
        .cfi_def_cfa_offset 16
        .cfi_offset 6, -16
        movq    %rsp, %rbp
        .cfi_def_cfa_register 6
        movl    $.LC0, %edi
        movl    $0, %eax
        call    printf
        movl    $0, %eax
        popq    %rbp
        .cfi_def_cfa 7, 8
        ret
        .cfi_endproc
.LFE0:
        .size   main, .-main
        .ident  "GCC: (GNU) 4.8.5 20150623 (Red Hat 4.8.5-5)"
        .section        .note.GNU-stack,"",@progbits
```

### 汇编

`-c`参数可以生成目标文件（object file)

```
gcc -c hello.c -o hello.o
```