
RocketMQ 旧集群升级到 Dledger集群
------

参考文章：

+ https://github.com/apache/rocketmq/blob/master/docs/cn/dledger/deploy_guide.md

+ https://mp.weixin.qq.com/s/Jjch9Pl97xFjggljSq_8xQ

要点：

+ 注意 dLegerSelfId 命名规范 （以字母开头，数字结尾，如 n0、n1）

+ 具备核心的容灾能力，需要保证部署 3+ 节点

最佳的升级方法（假如旧集群采用 Master-Slave 模式）：

+ 将机器中某个 Master-Slave Group 设置成 read only，不再允许写消息，只允许消费消息

+ 新增 Dledger Group，并加入集群

+ 逐步下线 read only 的 Master-Slave Group，被新的 Dledger Group 所替代