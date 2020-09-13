### iostat 


iostat -d 10 3

http://www.ibm.com/developerworks/cn/aix/library/0910_jiangpeng_unixdisk/

tps

Indicate the number of transfers per second that were issued to the device. A transfer is  an I/O  request  to  the  device.  Multiple  logical  requests can be combined into a single I/O request to the device. A transfer is of indeterminate size.

在linux系统中，还有一些杂项命令，用来辅助用户使用这些命令相对比较简单，但是却非常有用。

### netstat

--numeric-ports 可以避免端口被翻译
-p --program 参数可以显示对应的进程PID

netstat中的所有状态如下所示

       FREE   The socket is not allocated

       LISTENING
              The socket is listening for a connection request.  Such sockets are only included in the  output  if
              you specify the --listening (-l) or --all (-a) option.

       CONNECTING
              The socket is about to establish a connection.

       CONNECTED
              The socket is connected.

       DISCONNECTING
              The socket is disconnecting.

       (empty)
              The socket is not connected to another one.

       UNKNOWN
              This state should never happen.