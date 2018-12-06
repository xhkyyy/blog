# Guava 使用技巧

+ 使用 LoadingCache 时：
    + 通过 getIfPresent 获取 value，此时，如果 key 不存在，那么将直接返回 null，不会通过 `load` 方法加载数据
    + 可以使用 `getUnchecked` 代替 `get` 方法，将异常捕获交给 getUnchecked 处理
+ 使用 `weakKeys` 、`weakValues` 构建缓存时，当我们从缓存中查询时，切记 `get`、`getIfPresent` 等方法将通过 `==` 来判断 key 是否相等，而且不是通过 equals 方法
    + 比如使用了 `weakKeys` 构建缓存，实例见 `代码-1`


```java
//代码-1

cache.put("abc", "hello world");

String abcKey = buildABCKey();// 动态创建一个字符串 abc，防止编译器使用同一个 abc 字面量实例

String value = cache.getIfPresent(abcKey);
// value 将为 Null

```