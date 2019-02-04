# Interceptor 的 intercept 方法中，invocation.getArgs() 参数是怎样封装的

```java
// org.apache.ibatis.binding.MapperMethod.java
// args 中是否会产生 ParamMap 的逻辑代码如下：

public Object convertArgsToSqlCommandParam(Object[] args) {
      final int paramCount = params.size();
      if (args == null || paramCount == 0) {
        return null;

        // hasNamedParameters 判断 mapper 方法的所有参数中是否有使用了 @Param 注解
        // 只要 mapper 的方法参数只有一个，且没有使用 @param 注解，那么，将直接返回该参数，否则，通过 ParamMap 封装参数
      } else if (!hasNamedParameters && paramCount == 1) {
        return args[params.keySet().iterator().next()];
      } else {
        final Map<String, Object> param = new ParamMap<Object>();
        int i = 0;
        for (Map.Entry<Integer, String> entry : params.entrySet()) {
          param.put(entry.getValue(), args[entry.getKey()]);
          // issue #71, add param names as param1, param2...but ensure backward compatibility
          final String genericParamName = "param" + String.valueOf(i + 1);
          if (!param.containsKey(genericParamName)) {
            param.put(genericParamName, args[entry.getKey()]);
          }
          i++;
        }
        return param;
      }
    }
```


```java
// com.sun.proxy.$Proxy29
// 假设我们正在执行的是一个 update 类型的方法
public final int update(MappedStatement var1, Object var2) throws SQLException {
        try {
            // var2 的产生间接依赖于通过上述的 convertArgsToSqlCommandParam 方法生成
            return (Integer)super.h.invoke(this, m6, new Object[]{var1, var2});
        } catch (RuntimeException | SQLException | Error var4) {
            throw var4;
        } catch (Throwable var5) {
            throw new UndeclaredThrowableException(var5);
        }
    }
```