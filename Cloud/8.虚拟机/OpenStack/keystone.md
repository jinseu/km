## Openstack Security

针对云计算而言，需要考虑的安全因素有：

1. 数据的安全。主要包括用户数据的安全，包括静态的文件，数据库等。
2. 身份和访问管理的安全。
3. 虚拟化安全。比如需要有效隔离各个虚拟机，将不同敏感度和安全要求的虚拟机隔离。
4. 基础设施的安全。包服务器，存储，网络等核心IT基础设施的安全。具体措施有，安全事件日志，入侵检测，入侵防御等。


### Keystone

#### 体系结构

基本概念

1. User 用户。通过Keystone访问Openstack服务的个人，系统或者某个服务，Keystone会通过认证信息（密码等）验证用户请求的合法性，并分配令牌。
2. Tenant 租户。可以理解为一个组织或者一个项目。
3. Role 角色。 角色不同以为着被赋予的权限不同，只有知道用户所被授予的角色才能知道是否有权限访问某个资源。
4. Service。 Nova，Swift等，根据User，Tenant和role来确认是否有权限访问。
5. Endpoint 端点，指一个可被访问的具体的URL，可以看做是服务的访问点。URL具有Public，Internal 和 Admin三种权限。Pulic提供全局服务，Internal提供内部服务，Admin只提供给管理员使用。
6. Token 令牌。用户通过Credentials获取在某个租户下的令牌。
7. Credentials 凭证。用户的用户名和密码。

keystone 提供的服务

1. Identity 对用户身份的验证。
2. Token Identity确认身份后，会发给用户一个令牌。Token服务用语验证并管理用于身份验证的令牌。Token可以和Tenant绑定，也可以无关。
3. Catalog。对外提供的服务查询目录。
4. Policy，基于规则的身份验证引擎。


### auth



### Policy


### keystone 源码解析

keystone在启动时，可以通过keystone-all 这个脚本启动，同时可以使用keystone-manage这个命令进行管理。

在keystone-all 中会从 `keystone.server import eventlet`，然后运行`eventlet_server.run(possible_topdir)`。