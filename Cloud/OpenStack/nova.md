## Nova


nova 是Openstack的最核心的组件，主要提供计算资源的编排与管理服务。nova内部由三个服务组成，分别是

1. nova-api  提供HTTP API接口
2. nova-scheduler 提供资源的调度，例如，选择最合适的计算节点来创建虚拟机实例。
3. nova-conductor 数据库服务，所有数据库的写操作都通过conductor进行
4. nova-computer 通过与虚拟机管理器交互，来运行虚拟机，并管理虚拟机生命周期


四个组件之间通过AMQP消息队列进行通信。

### api

#### api的启动

nova api启动的时候会运行nova.cmd.api:main 函数，同时会在命令行参数中指定配置文件和日志文件。

main函数分为五步:

1. nova.config.parse_args
2. nova.objects.register_all()
3. 初始化一个launcher，nova.openstack.common.service:ProcessLauncher
4. 为CONF.enabled_apis中的每一个服务
 1. 初始化一个service(即代码中的Server), nova.service:WSGIService
 2. 调用launcher加载service。
5. launcher.wait()


其中最关键的4.1 和 4.2。

**4.1 初始化Server**

首先创建一个server，即`nova.service:WSGIService`对象。在`WSGIService`中会使用`nova.wsgi:Loader().load_app ` 加载name参数指定的app，然后用这个app创建一个`nova.wsgi:Server()`。在创建WSGI Server的时候，本质上是启动了一个`eventlet.wsgi.server`，使用eventlet.listen 监听指定的端口。然后在调用`nova.wsgi:Server().start`方法的时候，使用evenlet接收并处理请求。

在加载app时，使用paste deploy加载。流程是

1. 在nova.conf中找到`enabled_apis = ec2,osapi_compute,metadata`
2. 然后根据这个配置在api-paste中加载对应的app
3. 对不同url加载不同的composite去处理。

以osapi_compute为例，在auth使用keystone的情况下，加载app pipeline为

`compute_req_id faultwrap sizelimit authtoken keystonecontext ratelimit osapi_compute_app_v2`

其中除了最后一项真正的应用之外

```
[app:osapi_compute_app_v2]
paste.app_factory = nova.api.openstack.compute:APIRouter.factory
```

比较重要的是

```
[filter:keystonecontext]
paste.filter_factory = nova.api.auth:NovaKeystoneContext.factory

[filter:authtoken]
paste.filter_factory = keystonemiddleware.auth_token:filter_factory
```

需要说明的是，默认情况下，enabled_apis指定了三种类型，这三种类型的api会分别加载到不同的server，绑定不同的端口。

**4.2 launch_service**

在4.2中调用第三步中初始化好的launcher，加载4.1中创建的service。在launch_service时，首先会用ServiceWrapper对service和worker进行简单的封装，然后，调用launcher._start_child启动封装过的服务。

在`_start_child`中，会调用os.fork 创建子进程，然后

```
launcher = self._child_process(wrap.service)
while True:
    self._child_process_handle_signal()
    status, signo = self._child_wait_for_exit_or_signal(launcher) # launcher.wait()->service.wait()
    if not _is_sighup_and_daemon(signo):
        break
    launcher.restart()

    os._exit(status)
```

###



### conductor


### scheduler

### computer


### objects


### config



### Policy
