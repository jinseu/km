## 系统审计和维护

linux系统内部，有相当多命令可以看做是承担了系统审计和维护的责任。例如系统日志（syslog），定时任务等。本节就主要针对这一类命令，进行了说明。

### cron

#### crontab参数

-e 编辑crontab
-l 列出当前用户cron表中已有的条目
-u 指定用户（root权限）
-r 移除当前用户cron表

#### crontab编辑

cron 配置文件的路径是：

/var/spool/cron/crontabs
/etc/cron.allow

在cron的时间描述里
"-"表示在指定的范围内触发
"/"表示步进触发

另外在/crontab里还可以配置一些在特殊时刻触发的事件，例如：
```
string         meaning
------         -------
@reboot        Run once, at startup.
@yearly        Run once a year, "0 0 1 1 *".
@annually      (same as @yearly)
@monthly       Run once a month, "0 0 1 * *".
@weekly        Run once a week, "0 0 * * 0".
@daily         Run once a day, "0 0 * * *".
@midnight      (same as @daily)
@hourly        Run once an hour, "0 * * * *".
```

补充说明：

1. @reboot 只会在重启时运行，并不会在cron deamon重启时运行。@reboot 会根据REBOOT_FILE /var/run/crond.reboot来判断服务器是否重启。在开机的时候/var/run/目录下的文件都要被清除，于是cron就通过判断REBOOT_FILE是否存在来确定是否重启。

2. crontab如何实现每分钟跑两次：
```
    * * * * * /path/to/executable param1 param2
    * * * * * ( sleep 30 ; /path/to/executable param1 param2 )
```

### syslog

#### logrotate

#### syslog