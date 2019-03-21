# 关于 poolPreparedStatements 参数


## 参数解释

是否缓存 preparedStatement，也就是 PSCache。PSCache 对支持游标的数据库性能提升巨大，比如说 oracle。

## 开启建议

在 `Oracle 下建议始终设置该参数为 true`。

在 MySQL 下，低于 5.5 （不包括 5.5）的版本建议关闭该参数（设置为 false），`MySQL 5.5 及其以上的版本建议开启（设置为 true）`。
