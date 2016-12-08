## syslog

syslog是linux系统中比较重要的一项服务，syslog的使用，既涉及到开发工作，也涉及到运维工作。本节中将只讲解关于开发工作，即系统调用的部分。

syslog相关的系统调用包括四个，分别具体系统调用如下。

```
 #include <syslog.h>

void openlog(const char *ident, int option, int facility);
void syslog(int priority, const char *format, ...);
void closelog(void);

#include <stdarg.h>
void vsyslog(int priority, const char *format, va_list ap);

```