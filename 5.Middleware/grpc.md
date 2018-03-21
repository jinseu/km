### gRPC

grpc是一个使用protocol buffer 作为Interface Definition Language (IDL)语言的远程调用框架。

支持四种服务类型
1. single->single
2. single->stream
3. stream->single
4. stream->stream


Starting from a service definition in a .proto file, gRPC provides protocol buffer compiler plugins that generate client- and server-side code.

服务端流程:

1. 实现在service中声明的方法
2. 启动gRPC server
3. gRPC 框架解码输入的请求，执行service 中的方法
4. 编码响应内容

客户端流程：

1. 创建一个local stub，这个stub实现了和service中同样的方法。
2. 调用stub中的方法
3. 编码/序列化 请求参数，发送到server
4. 接收响应，解码/反序列化

#### 基本使用

#### 路由

#### HelloWorld 的流程图

#### 基本架构与功能说明

#### 关键源码解析