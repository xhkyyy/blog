# 模拟 Elasticsearch 网络故障

### 3 个节点

+ node-78 （机房 A）
+ node-99 （机房 B）
+ node-242（机房 C）

## 测试-1

测试条件：
+ node-78   (Master-eligible: true) （Master）
+ node-99   (Master-eligible: true)
+ node-242  (Master-eligible: true)
+ `node-242` 与 `node-99` 之间网络不通

现象：

+ 创建新的索引后，`node-242` 和 `node-99` 上的部分 Index Shard 始终处于 `INITIALIZING` 状态，错误原因是两个节点无法与对方相互通信（failed to execute on node ...）
+ `node-242` 与 `node-99` 节点不稳定，比如出现访问异常等，`node-78` 节点不受影响
+ 从 `node-242` 、 `node-99` 查询集群节点列表，返回结果中都不会包含对方
+ 如果一个 Shard A 在 `node-99` 上，其副本在 `node-242` 上，此时通过 `node-78` 向 index 中插入数据，那么
    + Shard A（`node-99`）能够正常到写入数据
    + `node-242` 上的副本写入失败（副本是通过 Shard 同步的），副本写入失败后，将经历由未分配状态 -----> 该副本被重新 relocation 分配到了主节点（`node-78`）上。改过程中 `node-78` 会不断到尝试将副本重新调度到 `node-242` 上。当网络恢复正常后，Master (`node-78`) 重新平衡了各个节点到分片。

网络恢复后，集群状态`自动`恢复正常。

## 测试-2

测试条件：
+ node-78   (Master-eligible: true) （Master）
+ node-99   (Master-eligible: true)
+ node-242  (Master-eligible: true)
+ `node-242` 与 `node-99`、`node-78` 网络不通

现象：

+ `node-99`、`node-78` 组成一个新的集群
+ `node-242` 处于离线状态
+ 如果同一个 Shard 的主副本都在 `node-242` 上，那么数据将丢失

网络恢复后，集群状态`自动`恢复正常。


## 测试-3

测试条件：
+ node-78   (Master-eligible: true) （Master）
+ node-99   (Master-eligible: true)
+ node-242  (Master-eligible: true)
+ `node-242` 与 `node-78` 之间网络不通

现象：

+ `node-242` 被踢出集群，`node-99`、`node-78` 组成一个新的集群

网络恢复后，集群状态`自动`恢复正常。


## 测试-4

测试条件：
+ node-78   (Master-eligible: true) （Master）
+ node-99   (Master-eligible: true)
+ node-242  (Master-eligible: true)
+ `node-78` 与 `node-99` 、`node-242` 之间网络不通

现象：

+ 集群处于较长时间的不可用状态
+ `node-99（新Master） ` 、`node-242` 重新组成新的集群，
+ `node-78` 处于离线状态
+ 网络恢复后，集群状态`自动`恢复正常，`node-78` 作为普通节点加入集群


## 测试-5

测试条件：
+ node-78   (Master-eligible: true) （Master）
+ node-99   (Master-eligible: true)
+ node-242  (Master-eligible: false)

待补充。。。


## 总结

+ 发生网络分区后，整个集群出现短暂的不可用
+ 每次节点之间出现不互通，主节点会出现短暂的不可用