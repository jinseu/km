### FAQ

#### 如何在Go中检查一个struct是否被复制过

首先无法做到让一个结构体检查自己是否被复制过，但是可以让一个复制出来的结构体无法使用。例如可以为目标结构体C添加一个copyChecker成员。然后每次调用C的方法前，先调用copyChecker.check()函数，检查调用结构体是否是复制出来的。

```Golang
// copyChecker holds back pointer to itself to detect object copying.
type copyChecker uintptr

func (c *copyChecker) check() {
        if uintptr(*c) != uintptr(unsafe.Pointer(c)) &&
                !atomic.CompareAndSwapUintptr((*uintptr)(c), 0, uintptr(unsafe.Pointer(c))) &&
                uintptr(*c) != uintptr(unsafe.Pointer(c)) {
                panic("is copied")
        }
}
```

#### 使用结构提的空指针是否可以调用结构体的方法

可以，例如如下代码会输出`struct point is nil`，这是因为对于Golang而言本质上没有类似类方法的结构体方法，只有函数，结构体方法在编译是会被编译为普通的函数，该函数的第一个参数是结构体或结构体的指针。

```Golang
package main

import (
    "fmt"
)

type Es struct {
}

func (e *Es) Print() {
        if e == nil {
                fmt.Printf("struct point is nil\n")
        }
}

func main() {
        var a *Es
        a.Print()
}
```
