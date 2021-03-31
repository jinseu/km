### tls

网络是一个分层结构，https协议本质上是在tcp和http协议之间加了一层，由这额外的一层来完成对上次信息的加密与保护，这额外的一层就是tls/ssl(tls: Transport Layer Security, ssl: Secure Sockets Layer)。一般而言，tls可以看做是ssl的继任者，所以这两个协议，通常放在一起。

tls 协议的核心加密算法，有三类，分别是哈希算法，非对称加密算法，对称加密算法。TLS/SSL有很多个版本，最新的tls1.3为例，对TLS协议进行说明。

**PSK**

**0-RTT**

#### TLS Record Protocol

```
The Record Protocol takes messages to be transmitted, fragments the
data into manageable blocks, optionally compresses the data, applies
a MAC, encrypts, and transmits the result.
```

#### Handshake Protocol

握手阶段可以分为三步：

1. client发送ClientHello message
2. server 处理ClientHello，并返回ServerHello message。
3. client 和 server 交换Authentication

基本过程如下图所示

```
Client                                           Server

Key  ^ ClientHello
Exch | + key_share*
     | + signature_algorithms*
     | + psk_key_exchange_modes*
     v + pre_shared_key*       -------->
                                                  ServerHello  ^ Key
                                                 + key_share*  | Exch
                                            + pre_shared_key*  v
                                        {EncryptedExtensions}  ^  Server
                                        {CertificateRequest*}  v  Params
                                               {Certificate*}  ^
                                         {CertificateVerify*}  | Auth
                                                   {Finished}  v
                               <--------  [Application Data*]
     ^ {Certificate*}
Auth | {CertificateVerify*}
     v {Finished}              -------->
       [Application Data]      <------->  [Application Data]

```



#### 反响

密码学是一门科学，和软件工程有很大的区别，需要慎之又慎。



