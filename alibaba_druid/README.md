# alibaba druid 数据库连接池

> https://github.com/alibaba/druid

# 保持最小连接数 minIdle 方法

现象：长时间空闲的连接，Druid 无法保证最小连接数为 minIdle

排查和解决方法：

1.通过如下方法，检查 MySQL 的 `wait_timeout` 参数确定其大小。
```sql
# 全局
show global variables like 'wait_timeout';

# 会话
show variables like 'wait_timeout';
```
会话的 wait_timeout 优先于全局的 wait_timeout。


2.配置一个连接在池中最小、最大生存的时间: `minEvictableIdleTimeMillis`、`maxEvictableIdleTimeMillis`。

其中，`minEvictableIdleTimeMillis` 的值不能大于 mysql 的 wait_timeout，否则，MySQL 将先于 Druid 而把连接关闭。（这个检查是至关重要的）


3.配置 `testWhileIdle` 参数为 true


4.配置 `keepAlive` 参数为 true

这个参数存在多种配置方式，见：https://github.com/alibaba/druid/wiki/KeepAlive_cn


5.配置 `initialSize`、`minIdle`、`maxActive` 三个参数。这 3 个参数最好都显式的配置，因为 `maxActive` 的默认值是 8，有可能你配置的 `minIdle` 却是 20。


*其它配置说明见：*

*DruidDataSource通用配置: https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE*

*DruidDataSource配置属性列表: https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE%E5%B1%9E%E6%80%A7%E5%88%97%E8%A1%A8*


# 参数最佳实践
`maxWait` 和 `timeBetweenEvictionRunsMillis` 两个参数的值最好是一致的。
尤其是 `timeBetweenEvictionRunsMillis` 不要过小于 `maxWait`。