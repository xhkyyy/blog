# JVM 调优或排查问题常用命令

```shell

top -p $pid -H   top 进程中的线程

jstack -m -l  $pid  打印栈信息和本地方法的信息

jstack -m   $pid    只打印本地方法的情况

jstat -gcutil pid $pid 3000 每隔 3 秒钟，输出下 gc 统计信息

jstat -gccapacity $pid 3000 每个 3 秒钟，输出下 jvm 堆各个堆中新、老年代的大小

jmap -histo $pid 显示堆中的统计信息

jmap -dump:live,format=b,file=/tmp/$pid.bin $pid   转储堆中存活的对象

如果 java 进程运行在其它用户下，或者环境中有多个不同的 java 版本可用，那么
1）ps 进程，找出运行 java 进程的用户名和 java 路径
2）通过
/sbin/runuser - $linux_user_name -s /bin/bash -c "jstack -m $pid"
使用特定的 linux 用户名 运行 java 命令，或者，同时指定运行 java 命令所在的目录（与同进程相同的 java 版本目录）
/sbin/runuser - $linux_user_name -s /bin/bash -c "/xxx/yyy/xxx/…./bin/jstack -m $pid"

```