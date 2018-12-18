# RocketMQ 测试

### 部署方式
broker ：   2m-2s-async，即：broker-a, borker-b，其中，async 表示 m 和 s 之间异步写入。也可以同步写入，性能稍差。
nameServer: 任意部署多个节点，且 nameServer 之间无交互。

### topic
创建 topic 的时候，需要把 BROKER_NAME 选项指定为多个 broker，如：broker-a, borker-b，即不少于 2 个 master 的情况下，才能达到高可用。

一个 topic 下可以包含若干个队列。

### 写消息
（1）在 producer 端指定 nameServer 后，nameServer 会返回 broker 列表。
producer 端会自动负载均衡的将消息写入多个 master broker，并通过同步或者异步的方式写入各自的 slave。

（2）可以并行的往 topic 的多个队列写消息。

### 读消息
可以并行的从 topic 的所有队列中读取数据。

### 高可用测试
A: 1master - 1slave 部署方式
（1）master 挂掉不影响消息的读取，但是会影响消息的写入。并且 master 挂掉，slave 无法提升为 master，也即是 master 和 slave 之间不会发生角色的切换。

B: 2master - 2slave 部署方式
（1）某一个 master 挂掉后，不会影响读、写，且，master 恢复后，producer 继续以负载均衡的方式写入 2 个 master.

（2）一个或者所有 slave 挂掉不会影响消息的读、写。

（3）所有的 master 挂掉，无法写入，但是可以继续读。



### 部署脚本
1）依赖 Java 8+

2）
sh bin/mqbroker -n nameServer:port -c conf/2m-2s-async/broker-a.properties &

sh bin/mqbroker -n nameServer:port -c conf/2m-2s-async/broker-a-s.properties &

sh bin/mqbroker -n nameServer:port -c ./conf/2m-2s-async/broker-b.proper &

sh bin/mqbroker -n nameServer:port -c ./conf/2m-2s-async/broker-b-s.proper &