### 注解

1. 注解可以用@interface的形式来定义。
2. 使用时，则是用@后面加上注解的类型名来表示。
3. 注解元素的值是由括号内的由逗号分隔的name=value语句列表提供。
4. 注解可以没有元素，如果注解没有元素，或者元素都有默认值，那么可以省略初始化列表，即大部分默认的注解的写法`@Deprecated`
5. 可以用注解来修饰的元素就是那些可以用修饰符来修饰的元素，包括类型声明，字段，方法，本地变量，以及参数。
6. 可以用@Rentention注解来限定注解的保存策略
 * SOURCE 仅保存在源文件中
 * CLASS 保存在class文件中，默认保存策略
 * RUNTIME 保存在类的二进制表示中，可以用反射机制来获取，例如@Depercated
7. 可以用@Target注解来限制注解，可以注解的元素。


```
//定义
@interface Sample {
    String name();
    int revision() default 1;    // 此处不能有任何参数，不能有throws语句，以及泛型
}

//使用

@Sample(
    name= "java",
    revision = 3
}
public class Foo {

}
```


### 反射

与反射相关的包都在`java.lang.reflect`包中。

1. 通过反射机制，可以从类名，获取对应的class对象，然后使用这个class对象创建新的实例。
2. 每种类型都有一个Class对象，包括每个类，枚举，接口，注解，数组和基本类型。
3. Class类是一个被声明为Class<T>的泛型类，每一个引用类型的class对象都有一个与所表示的类相对应的参数化类型。
4. Class类提供了一系列方法来获取这个类的属性，方法，字段。
 * 可以用Class类newInstance方法来创建一个对象
 * 使用getMethods方法获取Method数组，然后对单个Method对象后，可以用invoke方法，来让指定的obj调用该方法，
 * 使用getFields方法获取Fields数组，然后对单个Field对象，则可以使用set方法，给指定的obj对象设置这个Field对象代表的字段的值。注意此处调用方法，设置字段都需要指定一个对应类型的obj。
 * 使用getConstructor方法获得构造器，然后调用Constructor对象的，newInstance方法创建对象
 * 可以使用isInstance方法检查obj是否与该类兼容
 * 使用cast方法将obj转换为class所表示的类型。
 * Class，Method，Field都有getAnnotations方法来获取对应元素上的注解。

### JAVA 代理

#### 基本原理

通过使用Java 动态代理机制，可以使得我们不用手工编写代理类，只要简单地指定一组接口及委托类对象，便能动态地获得代理类。代理类会负责将所有的方法调用分派到委托对象上反射执行，在分派执行的过程中，还可以按需调整委托类对象及其功能。

Java 动态代理。具体使用 包括如下四步骤：
1.	通过实现 InvocationHandler 接口创建自己的调用处理器；
2.	通过为 Proxy 类指定 ClassLoader 对象和一组 interface 来创建动态代理类；
3.	通过反射机制获得动态代理类的构造函数，其唯一参数类型是调用处理器接口类型；
通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数被传入。

```
// 方法 1: 该方法用于获取指定代理对象所关联的调用处理器
static InvocationHandler getInvocationHandler(Object proxy)

// 方法 2：该方法用于获取关联于指定类装载器和一组接口的动态代理类的类对象
static Class getProxyClass(ClassLoader loader, Class[] interfaces)

// 方法 3：该方法用于判断指定类对象是否是一个动态代理类
static boolean isProxyClass(Class cl)

// 方法 4：该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
static Object newProxyInstance(ClassLoader loader, Class[] interfaces,
    InvocationHandler h)
```


#### CGLib

### 类加载器

#### 基本概念

类加载器（class loader）用来加载 Java 类到 Java 虚拟机中。一般来说，Java 虚拟机使用 Java 类的方式如下：Java 源程序（.java 文件）在经过 Java 编译器编译之后就被转换成 Java 字节代码（.class 文件）。

所有的类加载器都是java.lang.ClassLoader的子类。同时每一个类对象内都保存有一个加载该类的类加载器的引用，可以参见下面的代码示例。

类加载器加载类的基本过程有以下几步：

1. 调用ClassLoader.loadClass加载类，返回一个class对象
 * 调用findLoadedClass查看该类是否被加载过
 * 如果没有加载过的话，会调用父类加载器的 loadClass()方法来尝试加载该类；
 * 如果父类加载器无法加载该类的话，就调用 findClass()方法来查找该类。
2. 调用class对象的newInstance方法，创建该类的一个对象。

Java 中的类加载器大致可以分成两类，一类是系统提供的，另外一类则是由开发人员编写的。

系统提供的类加载器主要有下面三个：

* 引导类加载器（bootstrap class loader）：它用来加载 Java 的核心库，是用原生代码来实现的，并不继承自 java.lang.ClassLoader，没有办法用`class.getClassLoader`方法来获取，例如`HashMap.class.getClassLoader()`返回值为null。Bootstrap ClassLoader不继承自ClassLoader，因为它不是一个普通的Java类，底层由C++编写，已嵌入到了JVM内核当中，当JVM启动后，Bootstrap ClassLoader也随着启动，负责加载完核心类库后，并构造Extension ClassLoader和App ClassLoader类加载器。
* 扩展类加载器（extensions class loader）：它用来加载 Java 的扩展库。Java 虚拟机的实现会提供一个扩展库目录。该类加载器在此目录里面查找并加载 Java 类。
* 系统类加载器（system class loader）：它根据 Java 应用的类路径（CLASSPATH）来加载 Java 类。一般来说，Java 应用的类都是由它来完成加载的。可以通过 ClassLoader.getSystemClassLoader()来获取它。

除了引导类加载器之外，所有的类加载器都有一个父类加载器。通过 classloader的 getParent()方法可以得到。

* 对于系统提供的类加载器来说，系统类加载器的父类加载器是扩展类加载器，而扩展类加载器的父类加载器是引导类加载器；
* 对于开发人员编写的类加载器来说，其父类加载器是加载此类加载器 Java 类的类加载器。因为类加载器 Java 类如同其它的 Java 类一样，也是要由类加载器来加载的。一般来说，开发人员编写的类加载器的父类加载器是系统类加载器。类加载器通过这种方式组织起来，形成树状结构。树的根节点就是引导类加载器。

例如下面的代码通过一个SampleClass，获取了加载这个类的系统类加载器，和扩展类加载器，但是并没有获得引导类加载器，因为ExtClassLoader的类加器是Bootstrap ClassLoader，但是Bootstrap ClassLoader不是一个普通的Java类，所以ExtClassLoader的parent=null。


```

ClassLoader loader = SampleClass.class.getClassLoader();
while (loader != null) {
    System.out.println(loader.toString());
    loader = loader.getParent();
}

-------

> sun.misc.Launcher$AppClassLoader@3af49f1c
> sun.misc.Launcher$ExtClassLoader@1fb3ebeb

```


类加载器在尝试自己去查找某个类的字节代码并定义它时，会先代理给其父类加载器，由父类加载器先去尝试加载这个类，依次类推。

之所以会先交给父classloader去加载，是因为:

JAVA在判断两个类是否相同时，Java 虚拟机不仅要看类的全名是否相同，还要看加载此类的类加载器是否一样。只有两者都相同的情况，才认为两个类是相同的。即便是同样的字节代码，被不同的类加载器加载之后所得到的类，也是不同的。

代理模式是为了保证 Java 核心库的类型安全。所有 Java 应用都至少需要引用 java.lang.Object类，也就是说在运行的时候，java.lang.Object这个类需要被加载到 Java 虚拟机中。如果这个加载过程由 Java 应用自己的类加载器来完成的话，很可能就存在多个版本的 java.lang.Object类，而且这些类之间是不兼容的。通过代理模式，对于 Java 核心库的类的加载工作由引导类加载器来统一完成，保证了 Java 应用所使用的都是同一个版本的 Java 核心库的类，是互相兼容的。

Class.forName是一个静态方法，同样可以用来加载类。该方法有两种形式：

* Class.forName(String name, boolean initialize, ClassLoader loader)
* Class.forName(String className)。

第一种形式的参数 name表示的是类的全名；initialize表示是否初始化类；loader表示加载时使用的类加载器。第二种形式则相当于设置了参数 initialize的值为 true，loader的值为当前类的类加载器。

需要注意的是，用户自定义的类并不一定要严格按照代理模式，例如，以Tomcat 为例，每个 Web 应用都有一个对应的类加载器实例。该类加载器也使用代理模式，所不同的是它是首先尝试去加载某个类，如果找不到再代理给父类加载器。这与一般类加载器的顺序是相反的。这是 Java Servlet 规范中的推荐做法，其目的是使得 Web 应用自己的类的优先级高于 Web 容器提供的类。




### Exception

#### 基本语法

#### Exception类层次

所有异常类的基类是`java.lang.Throwable`,Throwable 有两个直接子类`Error`和`Excption`。

An Error is a subclass of Throwable that indicates serious problems that a reasonable application should not try to catch. Most such errors are abnormal conditions. The ThreadDeath error, though a "normal" condition, is also a subclass of Error because most applications should not try to catch it.

A method is not required to declare in its throws clause any subclasses of Error that might be thrown during the execution of the method but not caught, since these errors are abnormal conditions that should never occur.


`Error`

#### 何时需要声明throws，何时不需要


#### 参考资料

> http://www.liquid-reality.de/display/liquid/2011/02/15/Karaf+Tutorial+Part+1+-+Installation+and+First+application
> https://www.ibm.com/developerworks/cn/java/j-lo-proxy1/
> https://www.ibm.com/developerworks/cn/java/j-lo-proxy2/