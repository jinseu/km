## 以太网交换机地址学习过程


## VLAN的概念与原理

VLAN是一个2层的广播域，它能将广播控制在一个VLAN内部。而不同VLAN之间或VLAN与LAN/WAN的数据通信必须通过第3层（网络层）完成。否则，即便是同一交换机上的连接端口，假如它们不处于同一个VLAN，正常情况下也无法进行数据通信。

vlan标签包括两部分，第一部分是以太帧标识0x8100，第二部分则是标识控制信息。其中标识控制信息，包括Priority域，占3bits，表示报文的优先级，取值0到7，7为最高优先级，0为最低优先级。该域被802.1p采用。 
规范格式指示符（CFI)域，占1bit，0表示规范格式，应用于以太网；1表示非规范格式，应用于Token Ring。VLAN ID域，占12bit，用于标示VLAN的归属。两部分加起来总计四个字节，会插入到以太帧的源地址后方。

交换机端口有三种工作模式，分别是Access，Hybrid，Trunk。
1. Access类型的端口只能属于1个VLAN，一般用于连接计算机的端口；
2. Trunk类型的端口可以允许多个VLAN通过，可以接收和发送多个VLAN的报文，一般用于交换机之间连接的端口；
3. Hybrid类型的端口可以允许多个VLAN通过，可以接收和发送多个VLAN的报文，可以用于交换机之间连接，也可以用于连接用户的计算机。 


交换机端口在收到报文时的处理方法：
- access端口收报文：判断是否有VLAN信息，如果没有则打上端口的PVID，并进行交换转发,如果有则直接丢弃（缺省） 
- trunk端口收报文：判断是否有VLAN信息，如果没有则打上端口的PVID，并进行交换转发，如果有判断该trunk端口是否允许该 VLAN的数据进入：如果允许则报文携带原有VLAN标记进行转发，否则丢弃该报文。 
- hybrid端口收报文：判断是否有VLAN信息，如果没有则打上端口的PVID，并进行交换转发，如果有则判断该hybrid端口是否允许该VLAN的数据进入：如果可以则转发，否则丢弃。

交换机端口在发送报文时的处理办法：
- access端口发报文：将报文的VLAN信息剥离，直接发送出去 
- trunk端口发报文：比较端口的PVID和将要发送报文的VLAN信息，如果两者相等则剥离VLAN信息，再发送，否则报文将携带原有的VLAN标记进行转发。
- hybrid端口发报文：1、判断该VLAN在本端口的属性 2、如果是untag则剥离VLAN信息，再发送，如果是tag则比较端口的PVID和将要发送报文的VLAN信息，如果两者相等则剥离VLAN信息，再发送，否则报文将携带原有的VLAN标记进行转发。

## 以太网帧格式
|MAC destination|MAC source|Ethertype or length |Payload	| CRC32 |
|--|
| 6 octets | 6 octets|2 octets|42–1500 octets|4 octets|

以太网帧最小长度为64字节，保证在收到冲突信号时，帧还没有传完。

在实现时一个帧还有前导序列，以及发送间隔。

一般而言，前导码和帧开始符无法在包嗅探程序中显示。这些信息会在OSI第1层被网卡处理掉，而不会传入嗅探程序采集数据的OSI第2层。也存在OSI物理层的嗅探工具以显示这些前导码和帧开始符，但这些设备大多昂贵，多用于检测硬件相关的故障。

帧类型

|编号|协议|
|--|--|
|0x800|IPv4|
|0x806|ARP|
|0x866|IPv6|

## IP报文格式以及TTL字段的作用

报文头格式

      0                   1                   2                   3
      0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     |Version|  IHL  |Type of Service|          Total Length         |
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     |         Identification        |Flags|      Fragment Offset    |
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     |  Time to Live |    Protocol   |         Header Checksum       |
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     |                       Source Address                          |
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     |                    Destination Address                        |
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     |                    Options                    |    Padding    |
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+


- IHL： Internet Header Length is the length of the internet header in 32bit words, and thus points to the beginning of the data.  Note that the minimum value for a correct header is 5.
- TOS（Type of Service）： The Type of Service provides an indication of the abstract parameters of the quality of service desired.  These parameters are to be used to guide the selection of the actual service parameters when transmitting a datagram through a particular network.  Several networks offer service precedence, which somehow treats high precedence traffic as more important than other traffic (generally by accepting only traffic above a certain precedence at time of high load).  The major choice is a three way tradeoff between low-delay,high-reliability, and high-throughput.

      Bits 0-2:  Precedence.
      Bit    3:  0 = Normal Delay,      1 = Low Delay.
      Bits   4:  0 = Normal Throughput, 1 = High Throughput.
      Bits   5:  0 = Normal Relibility, 1 = High Relibility.
      Bit  6-7:  Reserved for Future Use.

         0     1     2     3     4     5     6     7
      +-----+-----+-----+-----+-----+-----+-----+-----+
      |                 |     |     |     |     |     |
      |   PRECEDENCE    |  D  |  T  |  R  |  0  |  0  |
      |                 |     |     |     |     |     |
      +-----+-----+-----+-----+-----+-----+-----+-----+

        Precedence

          111 - Network Control
          110 - Internetwork Control
          101 - CRITIC/ECP
          100 - Flash Override
          011 - Flash
          010 - Immediate
          001 - Priority
          000 - Routine
- Total Length:  Total Length is the length of the datagram, measured in octets,including internet header and data.  This field allows the length of a datagram to be up to 65,535 octets. Such long datagrams are impractical for most hosts and networks.  All hosts must be prepared to accept datagrams of up to 576 octets (whether they arrive whole or in fragments).  It is recommended that hosts only send datagrams larger than 576 octets if they have assurance that the destination is prepared to accept the larger datagrams.
- Identification：An identifying value assigned by the sender to aid in assembling the fragments of a datagram.
- Flags：分片标识。
      
      Bit 0: reserved, must be zero
      Bit 1: (DF) 0 = May Fragment,  1 = Don't Fragment.
      Bit 2: (MF) 0 = Last Fragment, 1 = More Fragments.

          0   1   2
        +---+---+---+
        |   | D | M |
        | 0 | F | F |
        +---+---+---+
- Fragment Offset：片内偏移
- Header Checksum： 头校验和，CRC16

TTL字段可以避免报文在网络中无限制的传递下去。

## IPV4，私有地址

###地址分类

- A类地址: 0  
- B类地址  10
- C类地址  110
- 组播地址 1110
- 保留地址 11110

此外
0.0.0.0表示本机
127.0.0.1表示本端口，整个127表示回路测试。

10.0.0.0 – 10.255.255.255 A类
172.16.0.0 – 172.31.255.255 B类
192.168.0.0 – 192.168.255.255 C类

169.254.0.0到169.254.255.255是保留地址。如果你的IP地址是自动获取IP地址，而你在网络上又没有找到可用的DHCP服务器，这时你将会从169.254.0.1到169.254.255.254中临时获得一个IP地址。

CIDR 变长掩码

## ARP协议的原理以及基本流程

ARP协议是数据链路层协议。
报文格式如下：长度字段均为字节


| 字段 | 长度 | 说明 |
| ---- | --- | --- |
|目的地址|6| 广播地址 |
|源地址|6| |
|帧类型|2| 0x0806|
|硬件类型|2|硬件地址类型，为1表示MAC地址|
|协议类型|2|表示要映射的协议地址，即0x0800表示IP地址|
|硬件地址长度|1|即MAC地址的长度，一般为6|
|协议地址长度|1|即在协议地址的长度，ipv4 为4|
|op|2|操作类型，包括ARP请求，ARP应答，RARP请求，RARP应答，分别对应1，2，3，4|
|发送端MAC地址|6|(无)|
|发送端IP地址|4|(无)|
|目的MAC地址|6|(无)|
|目的IP地址|4|(无)|

**ARP欺骗**

**代理ARP**

**免费ARP**

RARP
    
主要用于无盘工作站启动时获取IP地址。

    rarp who is xx:xx:xx:xx:xx:xx tell xx:xx:xx:xx:xx:xx
    rarp reply xx:xx:xx:xx:xx:xx at yy:yy:yy:yy

参考资料：
https://www.ibm.com/developerworks/cn/linux/l-arp/

## DHCP协议原理和DHCP Client获取地址的基本过程

DHCP协议使用UDP协议，同时使用了67，68两个端口，67端口DHCP服务器的监听端口，68端口则是客户发出请求的端口。

DHCP协议的流程可以分为四部。

1. 客户发出的DHCP discovery报文
     
     DHCP客户机初始化TCP/IP，通过UDP端口67向网络中发送一个DHCPDISCOVER广播包，请求租用IP地址。该 广播包中的源IP地址为0.0.0.0，目标IP地址为255.255.255.255；包中还包含客户机的MAC地址和计算机名。
    
    
2. DHCP服务器发出DHCP offer报文

    任何接收到DHCPDISCOVER广播包并且能够提供IP地址的DHCP服务器，都会通过UDP端口68给客户机回应一个DHCPOFFER广播包，提供一个IP地址。该广播包的源IP地址为DCHP服务器IP，目标IP地址为255.255.255.255；包中还包含提供的IP地址、子网掩码及租期等信息。

3. 客户发出DHCP request报文

    客户机从不止一台DHCP服务器接收到提供之后，会选择第一个收到的DHCPOFFER包，并向网络中广播一个 DHCPREQUEST消息包，表明自己已经接受了一个DHCP服务器提供的IP地址。该广播包中包含所接受的IP地址和服务器的IP地址。 所有其他的DHCP服务器撤消它们的提供以便将IP地址提供给下一次IP租用请求。

4. 服务器发出DHCP acknowledgement报文

    被客户机选择的DHCP服务器在收到DHCPREQUEST广播后，会广播返回给客户机一个DHCPACK消息包，表明已经接受客户机的选择，并将这一IP地址的合法租用以及其他的配置信息都放入该广播包发给客户机。

由于DHCP协议本身没有提供鉴别机制，所以DHCP协议存在比较大的安全漏洞，包括

- Unauthorized DHCP servers providing false information to clients.
- Unauthorized clients gaining access to resources.
- Resource exhaustion attacks from malicious DHCP clients.

客户IP租用更新报文
http://www.cnblogs.com/wangquan/articles/1657174.html

## 路由表的组成

路由表，简称RIB。一般而言，路由表由以下几部分组成：
The routing table consists of at least three information fields:

1. the network id: i.e. the destination subnet
2. cost/metric: i.e. the cost or metric of the path through which the packet is to be sent
3. next hop: The next hop, or gateway, is the address of the next station to which the packet is to be sent on the way to its final destination

此外还可能包括一些附加内容：

4. quality of service associated with the route. For example, the U flag indicates that an IP route is up.
5. links to filtering criteria/access lists associated with the route
6. interface: such as eth0 for the first Ethernet card, eth1 for the second Ethernet card, etc.

和转发表的区别

Routing tables are generally not used directly for packet forwarding in modern router architectures; instead, they are used to generate the information for a smaller forwarding table. A forwarding table contains only the routes which are chosen by the routing algorithm as preferred routes for packet forwarding. It is often in a compressed or pre-compiled format that is optimized for hardware storage and lookup.

This router architecture separates the Control Plane function of the routing table from the Forwarding Plane function of the forwarding table.[3] This separation of control and forwarding provides uninterrupted（不中断，连续的） performance.

转发表一般用TCAM存储，以便于快速查询。

在linux中可以使用route， netstat -r， 以及ip route命令来查看路由表

参考资料
https://en.wikipedia.org/wiki/Forwarding_information_base

## OSPF和BGP的基本原理

### OSPF
OSPF 与 RIP

随着当今网络的快速成长和扩展，RIP的局限性显得尤为突出。RIP的以下一些限制在大型网络中可能会导致出现问题：
1. RIP 的跃点限制为 15 个。跨幅超过 15 个跃点（15 个路由器）的 RIP 网络是无法实现的。
2. RIP 不能处理可变长子网掩码 (VLSM)。考虑到 IP 地址短缺和 VLSM在有效分配IP地址方面的灵活性，这被认为是一个重大缺陷。

3. 完整路由表的定期广播消耗大量带宽。这是大型网络中的一个主要问题，特别是在低速链路和广域网网云中。
4. RIP的收敛速度慢于OSPF。在大型网络中，收敛大约需要几分钟的时间。RIP路由器会经历一段抑制和垃圾回收时期，并缓慢地使最近未接收的信息超时。这在大型环境中并不合适，并且可能导致路由不一致。

参考资料
http://www.cisco.com/cisco/web/support/CN/108/1083/1083003_1.html

OSPF（Open Shortest Path First）
直接使用IP，协议号0x59=89
OSPF是一个链路状态协议，路由器并不与其邻居交换距离信息。而是，主动地测试与邻居的链路状态，然后将信息发给其他邻居，从而将这些信息在自治系统中传播出去。每个路由器中都有完整的链路信息。

OSPF协议是分区的，分为backbone，stub，non-stub，totally-stubby四种区域，所有区域通过（必须存在的)主干网互联，形成逻辑拓扑。也可以通过虚拟连接相连。

OSPF报文包括Hello，exchange，flooding等三个过程。
1. Hello规程。发现新链路并检查现有链路是否还在正常工作，选举指定路由器DR和备份指定路由器BDR 虚拟节点。
2. 交换规程。两个路由器在建立了相邻关系之后要交换彼此的链路状态数据库（即拓扑数据库），以尽快达到对当前网络状态认识的同步，这个数据库的交换过程使用交换规程。OSPF的交换规程是非对称的，在交换之前双方要商定谁为主（master），谁为从（slave），然后再进行内容的交换。检查并更新数据库－任何一方在收到对方的链路状态描述之后，要根据状态类型、广播路由器和链路标识符在自己的链路状态数据库中进行查找。若找不到，或新记录描述的LS顺序号大于原有记录的顺序号（意味着收到的路由信息更新），则要向对方具体询问这个新路由。路由器将待询问的记录描述放在一个待查表中，当交换结束之后，它要根据待查表的内容向对方发送链路状态请求报文。
3. 广播规程。当链路状态发生变化，负责该链路的路由器要将新的状态广播给其它路由器，同样当收到链路状态请求报文时，对应的路由器也要将这个链路的状态记录传送给询问者。链路状态记录通过广播路由器标识符、链路状态标识符和链路状态类型来标识，并通过广播规程来传送。


BGP

距离向量协议 使用TCP，端口179
BGP数据库中包括以下三部分内容：
1. 邻居表。BGP对等体即BGP邻居，交换路径信息，与rip类似；可以直连可以不直连；邻居关系建立在TCP连接之上。
2. BGP转发表。列出了到达邻居的所有路径，能包含多条路径到目标网络，database包含每条路径的属性
3. 路由表。列出了到目标网络的最佳路径。


BGP的报文类型有四种：Open，update，notification，keepalive

流程：
1. BGP在支撑通信的 TCP建立之后，双方均要发送 open报文，以协商连接的参数。
2. 如果两个 BGP路由器同时发起连接建立的要求，则会发生连接建立冲突，这时使用 BGP连接标识符值大的一方所发起建立的连接；另一方发起的连接建立若已完成，则将其拆除，否则将其丢弃。
3. 如果同意建立连接，则用 keepalive报文进行应答；若不同意，则用 notification报文进行应答，并给出拒绝的原因。
4. 当BGP连接建立之后，BGP路由器就开始使用UPDATE报文进行路由更新信息的交换（给出每个通路向量对应的路由）。Update报文中包括不可达路由信息，路径归属信息，NLRI（网络层可达信息，以地址加前缀的形式提供）
5. 接收方将每一个收到的通路向量与自己路由表中的原有记录进行比较，若通路缩短了，则用其更新自己的路由表，并向自己的其它邻接点转告；否则丢弃这个新收到通路向量。
6. 接收方在实际接受这个新路由之前，还要进行两项检查：首先看是否有循环存在，即自己的自治系统号是否出现在通路向量中；另外需要将这个路由保持一段时间（即先不使用），看其是否稳定，即是否很快又收到不可达的通知。
7. 动态的路由注入分为纯动态：所有的IGP路由都被再分配（redistribute）到BGP；和半动态：只有某些IGP路由被注入（network命令）到BGP。纯动态方式可能将错误的路由扩散出去；且内部路由的变化会引发BGP的操作，从而造成全局路由的不稳定。半动态方式需要人工干预。
8. 边界路由器向外部输出本自治系统内的路由时， Origin置为IGP，而通路向量中只包含本自治系统号。当外部路由穿越本自治系统，并继续向其它自治系统传播时，边界路由器要将本自治系统号加入通路向量。
9. BGP路由器使用通路向量的长度来判定路由的优劣，长度较短的被认为距离较近，应优先使用。因此，BGP路由器也可通过人为控制通路向量长度方法来调整对路由的使用。例如，如果通路向量A1长度短，但对应路由的带宽是T1，通路向量A2长度长，但对应路由的带宽是T3；于是BGP路由器可以在通路向量中重复加入自己的AS号，使得原来较短的通路向量A1变得比A2长，这样路由器将使用A2给出的路由。

## TCP和UDP的主要区别

TCP：可靠传输，拥塞控制，面向连接，启动速度慢
UDP：不可靠传输，没有拥塞控制，面向数据分组，启动速度快

## linux如何配置IP地址，网关和DNS

在/etc/network/interfaces目录中可以配置IP地址，网关
DNS可以在/etc/resolv.conf，默认超时时间5秒，最多可以配置三个。
还可以通过ifconfig命令更改IP地址，网关
ifconfig eth0 add 10.10.33.145 netmask 10.10.255.255
route add default gw x.x.x.x
ifconfig eth0 arp 开启网卡eth0 的arp协议；
ifconfig eth0 -arp 关闭网卡eth0 的arp协议；

## linux 如何添加静态路由，策略路由

静态路由可以用route命令来操作。
route add

策略路由的添加则需要iproute2包中的ip命令来操作，基本流程包括三步，1、定义一个路由表，2、定义路由规则，3、应用路由规则。

1. 先建立一个自定义的路由表，ID 是 10000 ，名字叫做mygod（注意，ID从0-32766之间随你便起，3万多个可选）：

    echo 10000 mygod >> /etc/iproute2/rt_tables  

2. 建立路由规则

    ip rule add from <source address> lookup <table name>  

假设我们机器上连了两条线路，两块网卡，分配了2个ip，第一个线路是eth0，ip是192.168.1.1，第二个线路是eth1，ip是192.168.2.1。我们添加一下策略路由规则，从192.168.2.1过来的走mygod规则。

    ip rule add from 192.168.2.1 lookup mygod  

3. 应用路由规则

    ip route add default via 192.168.2.1 dev eth1 table mygod  

注意，这里是应用了缺省路由，当然你也可以弄其他的。比如，一台机器一个网卡，N个IP，每个ip对应不同的专线。你就可以添加不同的非缺省路由。另外，也是支持vlan的。

    ip route add default via 192.168.2.1 dev eth0.30 table vlan30  

如果想把策略路由固定下来，不用每次都执行shell脚本，生成下面两个文件：

    # vi /etc/sysconfig/network-scripts/route-eth1
    192.168.2.0/24 dev eth1 table mygod  
    default via 192.168.2.1 dev eth1 table mygod

    # vi /etc/sysconfig/network-scripts/rule-eth1
    from 192.168.2.0/24 lookup mygod  

## QoS的基本概念

QoS的基本概念

The ability to provide different priority to different applications, users, or data flows, or to guarantee a certain level of performance to a data flow

服务级别约定（SLA）

为什么需要QoS？
丢失数据包 - 当数据包到达一个缓冲器已满的路由器时，则代表此次的发送失败，路由器会依网络的状况决定要丢弃一部分、不丢弃或者是丢弃所有的数据包，而且这不可能在预先就知道，接收端的应用程序在这时必须请求重新发送，而这同时可能造成总体传输严重的延迟。
延迟 - 或许需要很长时间才能将数据包传送到终点，因为它会被漫长的队列迟滞，或需要运用间接路由以避免阻塞；也许能找到快速、直接的路由。总之，延迟非常难以预料。
传输顺序出错 - 当一群相关的数据包被路由经过因特网时，不同的数据包可能选择不同的路由器，这会导致每个数据包有不同的延迟时间。最后数据包到达目的地的顺序会和数据包从发送端发提交去的顺序不一致，这个问题必须要有特殊额外的协议负责刷新失序的数据包。

QoS的实现形式
(1)int—serv集成业务
int—serv主要引入了一个重要的网络控制协议RSVP(资源预留协议)。RSVP的引入使得IP网络为应用提供所要求的端到端的QoS保证成为可能。Int—serv尽管提供QoS保证，但其扩展性差。因为其工作方式是基于每个流的，这就需要保存大量的与分组队列数成正比的状态信息。此外，RSVP的有效实施必须依赖于分组所经过路径上的每个路由器。在骨干网上，业务流的数目可能很大，因此要求路由器的转发速率很高，这使得int—serv难于在骨干网上得到实施。
(2)Diff—serv区分业务
IETF在RFC2475中提出diff—serv体系结构，旨在定义一种能实施QoS且更易扩展的方式，以解决int—serv扩展性差的缺点。diff—serv简化了信令，对业务流的分类颗粒度更粗。Diff—serv通过汇聚(aggregate)和PHB(per hop behavior)的方式提供QoS。汇聚是指路由器把QoS需求相近的业务流看成一个大类，以减少调度算法所处理的队列数。PHB是指逐跳的转发方式，每个PHB对应一种转发方式或QoS要求。由于diff—serv采用对数据流分类聚集后提供差别服务的方法实现对数据流的可预测性传输，所以对QoS的支持粒度取决于传输服务的分级层次，各网络节点中存储的状态信息数量仅正比于服务级别的数量而不是数据流的数量，由此diff-serv获得了良好的扩展性。
(3)MPLS多协议标签交换
多协议标签交换(MPLS)将灵活的3层IP选路和高速的2层交换技术完美地结合起来，从而弥补了传统IP网络的许多缺陷。它引入了“显式路由”机制，对QoS提供了更为可靠的保证。

## Data center 网络的拓扑以及特性

接入层
汇聚层
核心层

## CDN

## SDN NFV Openflow

## NAT的基本原理

基本NAT
NAPT

## linux支持的NAT类型

SNAT源地址转换
DNAT目标地址转换
端口转换

## 对TCP，UDP，SSL，HTTP等的影响

TCP：2MSL
要先出一个
参考资料
https://tools.ietf.org/html/rfc3489

## linux如何配置NAT

    #首先打开ip_forward
    >/proc/sys/net/ipv4/ip_forward 设置为1
    
    iptables -t nat -A POSTROUTING -s 172.16.93.0/24  -j SNAT --to-source 10.0.0.1

    #此条规则将请求IP为10.0.0.1并且端口为80的数据包转发到后端的172.16.93.1主机上，通过定义不同的端口，就可以实现PNAT，将同一个IP不同的端口请求转发到后端不同的主机
    iptables -t nat -A PREROUTING -d 10.0.0.1 -p tcp –-dport 80 -j DNAT –-to-destination 172.16.93.1
    

## TCP握手过程和结束过程

## TCP报文头





## 滑动窗口协议



## TCP TIME_WAIT



## 了解TCP不适用的场景以及原因