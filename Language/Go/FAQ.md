### FAQ

#### 如何在Go中检查一个struct是否被复制过

首先无法做到让一个结构体检查自己是否被复制过，但是可以让一个复制出来的结构体无法使用。例如可以为目标结构体C添加一个copyChecker成员。然后每次调用C的方法前，先调用copyChecker.check()函数，检查调用结构体是否是复制出来的。

```
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
