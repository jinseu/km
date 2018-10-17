## Spring

### Spring 原理

### spring-framework

> https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html
> https://www.ibm.com/developerworks/cn/java/j-lo-spring-principle/

### Spring-boot

本文中spring-boot的版本: 1.3.5.RELEASE

在spring boot里，很吸引人的一个特性是可以直接把应用打包成为一个jar/war，然后这个jar/war是可以直接启动的，不需要另外配置一个Web Server。


在使用maven打包之后，会生成两个jar文件，以tcp-proxy项目为例。

```
tcp-proxy-0.0.1-SNAPSHOT.jar
tcp-proxy-0.0.1-SNAPSHOT.jar.original
```

其中，tcp-proxy-0.0.1-SNAPSHOT.jar.original是默认的maven-jar-plugin生成的包。

tcp-proxy-0.0.1-SNAPSHOT.jar则是spring boot maven 插件生成的包，里面包含了应用的依赖，以及spring boot 相关的类。

解压tcp-proxy-0.0.1-SNAPSHOT.jar包之后，可以发现，目录结构如下所示：

```
├── META-INF
│   ├── MANIFEST.MF
├── application.properties
├── io
│   └── igx
│       └── proxy
              └── TcpProxyApplication.class
├── lib
│   ├── aopalliance-1.0.jar
│   ├── bootstrap-3.3.6.jar
│   ├── ...
└── org
    └── springframework
        └── boot
            └── loader
                ├── ExecutableArchiveLauncher.class
                ├── JarLauncher.class
                ├── JavaAgentDetector.class
                ├── LaunchedURLClassLoader.class
                ├── Launcher.class
                ├── MainMethodRunner.class
                ├── ...                
```

**MANIFEST.MF**

```
Manifest-Version: 1.0
Implementation-Title: tcp-proxy
Implementation-Version: 0.0.1-SNAPSHOT
Archiver-Version: Plexus Archiver
Built-By: jinlei01
Start-Class: io.igx.proxy.TcpProxyApplication
Implementation-Vendor-Id: io.igx.proxy
Spring-Boot-Version: 1.3.5.RELEASE
Created-By: Apache Maven 3.5.3
Build-Jdk: 1.8.0_121
Implementation-Vendor: Pivotal Software, Inc.
Main-Class: org.springframework.boot.loader.JarLauncher
```

其中指定了Main-Class为org.springframework.boot.loader.JarLauncher，即jar包启动时的Main函数。

Start-Class则是应用自己的Main函数 io.igx.proxy.TcpProxyApplication。

在实际启动时，java虚拟机会首先加载并运行在Main-Class

**lib目录**

这里存放的是应用的Maven依赖的jar包文件。 
比如spring-beans，spring-mvc等jar。


**org/springframework/boot/loader**

这下面存放的是Spring boot loader的.class文件。


> http://blog.csdn.net/hengyunabc/article/details/50120001
> https://docs.spring.io/spring-boot/docs/2.0.0.BUILD-SNAPSHOT/reference/htmlsingle/
> https://docs.spring.io/spring-boot/docs/2.0.0.BUILD-SNAPSHOT/api/

#### spring boot如何启动的？

#### spring boot embed tomcat是如何工作的？ 

静态文件，jsp，网页模板这些是如何加载到的？


#### Spring boot 的使用



#### Q&A

**ibatis`$`与`#`号的区别**

1. `#`是把传入的数据当作字符串，如`#field#`传入的是id,则sql语句生成是这样，`order by "id"`。 

2. `$`传入的数据直接生成在sql里，如`$field$`传入的是id,则sql语句生成是这样，`order by id`, 这就对了． 如：
 
3. `#`方式能够很大程度防止sql注入． 

4. `$`方式一般用于传入数据库对象．例如传入表名. 

5. 一般能用`#`的就不用`$`.

http://www.cnblogs.com/google4y/p/3556357.html