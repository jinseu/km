### subprocess

`subprocess`模块用来产生新的进程，同时可以使用管道来链接子进程的input/output/error，还可以获得子进程的返回值。subprocess模块可以用来替代以下模块或者函数。即意味以下模块或者函数是过时的。
```
os.system
os.spawn*
os.popen*
popen2.*
commands.*
```

#### call

subprocess模块中，最基本的函数包括`call`，`check_call`,`check_output`。其中
1. `call`函数运行args指定的命令，并等待运行完成后，返回命令的返回值。
2. `check_call`和`call`函数的不同之处在于，如果返回值为0则返回0，否则抛出 CalledProcessError异常。
3. `check_output`函数则是返回命令的输出。同时在进程返回值不为0的情况下，抛出CalledProcessError异常异常。

另外需要说明的是，shell=True是一个非常不推荐的选择，如果使用shell=True，可能导致shell注入，最终导致不可预估的后果。

最后，需要说明的是第一个参数args是参数列表或者元组。同时每一个参数都必须是字符串。否则会抛出child_exception。
```
>>> subprocess.call(["ls", "-l"])
0
```

#### Popen类

和三个call函数不同，Popen会在新的进程中执行子程序。在Popen命令调用后，子程序会立即执行，同时Popen会返回。

Popen中的常用方法包括：
1. `Popen.poll()`检查子程序是否终止，如果终止，返回returncode，否则返回None。
2. `Popen.wait()`阻塞父进程，等待子进程返回。返回值为子进程的returncode。
3. `Popen.communicate`
4. `Popen.send_signal(signal)`
5. `Popen.terminate()`,`Popen.kill()`
7. `Popen.pid`
8. `Popen.returncode`
9. `Popen.stdin`,`Popen.stdout`,`Popen.stderr`
在指定了Popen中如果指定了对应的描述符为管道，那么以上三者就是相应的文件描述符。




