# MyBatis

### 其它笔记

+ 关于 Interceptor
    + 给每个 Mapper 的方法生成一个唯一签名：
        + 我们知道 mapper 的方法（方法名称）是不能出现重载的，因此，可以利用 Class Name + Method Name 的签名作为一个 Mapper 方法的唯一签名
    + 在 Interceptor 中，我们的直接操作对象是每一个 Mapper，而不是 Service