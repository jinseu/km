## setuptools

setuptools是Python distutils增强版的集合，它可以帮助我们更简单的创建和分发Python包，尤其是拥有依赖关系的。setuptools具备以下特性：
1. 可以自动查找、下载、安装、升级依赖包
2. 创建Python Eggs
3. 包含包目录内的数据文件
4. 自动包含包目录内的所有的包，而不用在setup.py中列举
5. 自动包含包内和发布有关的所有相关文件，而不用创建一个MANIFEST.in文件
6. 自动生成经过包装的脚本或Windows执行文件
7. 支持Pyrex，即在可以setup.py中列出.pyx文件，而最终用户无须安装Pyrex
8. 可以部署开发模式，使项目在sys.path中
9. 用新命令或setup()参数扩展distutils，为多个项目发布/重用扩展
在项目setup()中简单声明entry points，创建可以自动发现扩展的应用和框架

### setup

setup函数是整个setuptool是的接口，所有的工作都需要过setup函数来完成。下面是setuptool相对比较完整的一个例子。
```
from setuptools import setup, find_packages
setup(
    name = "HelloWorld",
    version = "0.1",
    packages = find_packages(),
    scripts = ['say_hello.py'],

    # Project uses reStructuredText, so ensure that the docutils get
    # installed or upgraded on the target machine
    install_requires = ['docutils>=0.3'],

    package_data = {
        # If any package contains *.txt or *.rst files, include them:
        '': ['*.txt', '*.rst'],
        # And include any *.msg files found in the 'hello' package, too:
        'hello': ['*.msg'],
    },

    # metadata for upload to PyPI
    author = "Me",
    author_email = "me@example.com",
    description = "This is an Example Package",
    license = "PSF",
    keywords = "hello world example examples",
    url = "http://example.com/HelloWorld/",   # project home page, if any

    # could also include long_description, download_url, classifiers, etc.
)

```
#### version

版本号是一个由点，‘-’，数字，以及字母组成的字符串。

版本号的之间的新旧关系是一个比较复杂的问题。可以使用pkg_resources中的parse_version函数来比较版本的新旧。

首先，最基本的，对于由‘.’分隔的版本号的比较是比较简单的，通过将每个部分视为数字，然后来比较，前面的数字可以看做是高位。于是，有以下结果
```
>>> parse_version('1.9') < parse_version('2.0')
True
```

同时，需要说明的是，2.0.0 和 2.0是同一个版本。2.01 和2.1是同一个版本。

版本号的复杂之处在于含有字母的版本号。含有字母的版本号分为两类，分别是pre-release tag和post-release tag。pre-release tag是指按照字母表顺序，位于‘final’的字符。相反的post-release是指位于‘final’之后，或者有一个‘-’符号。

常用pre-release tag包括alpha, beta, a, c, dev等。需要说明的是2.4c1 ，2.4.c1 ，2.4-c1指的都是同一个版本。同时有三个特殊的tag：pre, preview, rc.都被看做是c。于是 2.4rc1, 2.4pre1 , 2.4preview1 都被看做是和 2.4c1相同的版本。


事实上，可以使用pkg_resources中的parse_version函数来比较版本的新旧。
```
>>> from pkg_resources import parse_version
>>> parse_version('1.9.a.dev') == parse_version('1.9a0dev')
True
>>> parse_version('2.1-rc2') < parse_version('2.1')
True
>>> parse_version('0.6a9dev-r41475') < parse_version('0.6a9')
True
```
其中越小的版本就意味着越旧。

**持续集成下的版本号**

#### packages

#### package_data

#### entry_points

entry_points 格式如下
```
entry_points={
    'console_scripts': [
        'cursive = cursive.tools.cmd:cursive_command',
    ],
},
```

`entry_points`可以将一个函数暴露出来，然后使之可以在命令行中运行。例如，在以上配置中运行`cursive`命令就相当于运行`cursive.tools.cmd:cursive_command`这个函数。

#### install_requires

A string or list of strings specifying what other distributions need to be installed when this one is. See the section below on Declaring Dependencies for details and examples of the format of this argument.

#### python_requires

A string corresponding to a version specifier (as defined in PEP 440) for the Python version, used to specify the Requires-Python defined in PEP 345.

#### setup_requires

A string or list of strings specifying what other distributions need to be present in order for the setup script to run. setuptools will attempt to obtain these (even going so far as to download them using EasyInstall) before processing the rest of the setup script or commands. This argument is needed if you are using distutils extensions as part of your build process; for example, extensions that process setup() arguments and turn them into EGG-INFO metadata files.

#### find_packages()

对于简单工程来说，手动增加packages参数很容易，但是如果工程比较大，那么手动添加package参数就会变得比较繁琐。可以使用`find_packages`参数来解决这个问题。find_packages默认在和setup.py同一目录下搜索各个含有__init__.py的包。

同时在`find_packages`函数中还可以使用exclude关键字来排除一些包。



#### Automatic Script Creation


### disutil和setuptools的区别

https://docs.python.org/2/distutils/introduction.html
https://setuptools.readthedocs.io/en/latest/

### python 模块卸载

setuptools中没有提供卸载参数，在卸载时可以按照如下流程操作
```
    $python setup.py install --record files.txt
    $cat files.txt | xargs rm -rf
```

## 问题

### entry_point 与scripts的区别

### egg包的格式以及加载的原理


## 参考资料

http://www.ibm.com/developerworks/cn/linux/l-cppeak3.html
https://setuptools.readthedocs.io/en/latest/setuptools.html