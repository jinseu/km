## 类型与对象

### 布尔值
None 值确实是被当成False，但是还有其他的对象(比如长度为0 的字符串、列表、元组、字典等) 都会被当做False。

## 函数

### python是否支持重载

python在形式上并不支持重载，但是支持多参数默认值。同时还支持元组型非关键字参数（`*args`）和字典型关键字参数（`**args`）。同时需要说明的是，一个 `*`参数只能出现在函数定义中最后一个位置参数后面，而`**`参数只能出现在最后一个参数。

#### 如何实现函数的某些参数强制使用关键字参数传递

将强制关键字参数放到某个\* 参数或者单个* 后面就能达到这种效果。
```
def recv(maxsize, *, block):
'Receives a message'
pass
recv(1024, True) # TypeError
recv(1024, block=True) # Ok
```

#### python 函数是否支持返回多个值

形式上可以，但本质上还是返回了一个元组。使用的是逗号来生成的一个元组。

#### lamda表达式

##### 运行时绑定
```
>>> x = 10
>>> a = lambda y: x + y
>>> x = 20
>>> b = lambda y: x + y
```
a(10) 和b(10) 返回的结果是什么？

## I/O

## 4 库函数

#### 4.1.1 sys.exit与os._exit函数有何区别
os._exit()会直接将python程序终止，之后的所有代码都不会继续执行。

sys.exit()会引发一个异常：SystemExit，如果这个异常没有被捕获，那么python解释器将会退出。如果有捕获此异常的代码，那么这些代码还是会执行。

sys.exit()的退出比较优雅，调用后会引发SystemExit异常，可以捕获此异常做清理工作。os._exit()直接将python解释器退出，余下的语句不会执行。

一般情况下使用sys.exit()即可，一般在fork出来的子进程中使用os._exit()

> https://docs.python.org/2/library/exceptions.html#exceptions.SystemExit

 