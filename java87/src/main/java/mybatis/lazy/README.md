# 延迟加载

## 配置方法
##### 1.在 mybatis 配置文件中新增：
> http://www.mybatis.org/mybatis-3/configuration.html
```xml
<configuration>
    ...
    <settings>
        <!-- true： 指定全局懒加载  -->
        <setting name="lazyLoadingEnabled" value="true"/>

        <!-- false： 每个属性按照需要懒加载 -->
        <setting name="aggressiveLazyLoading" value="false"/>
    </settings>
    ...
</configuration>
```


##### 2.在 Mapper 中配置 fetchType=lazy 属性
```xml
<resultMap id="BaseResultMap" type="com.x.User">
        <id column="id" property="id" jdbcType="INTEGER"/>
        <result column="user_id" property="userId" jdbcType="INTEGER"/>
        ...
        <association property="userInfo" column="user_id" fetchType="lazy" select="com.x.dao.UserInfoMapper.getUserInfo"/>
    </resultMap>
```

##### 3.在代码中，只有 user.getUserInfo() 的时候才会去执行相关 sql，加载数据。（可以通过观察 mybatis sql 的输出来判断懒加载机制是否生效）


## 实现机制
##### 1.Mapper 执行的时候会获取 getProxyFactory()，它的默认实现如下：
```java
//org.apache.ibatis.session.Configuration.java
public ProxyFactory getProxyFactory() {
    if (proxyFactory == null) {
      proxyFactory = new CglibProxyFactory();
    }
    return proxyFactory;
  }
```
##### 2.cglib 会创建实体对象的增强类，会向实体的所有方法（比如 getter、setter），插入相关的增强逻辑。
```java
public class User$$EnhancerByCGLIB$$a213efd5 extends User implements WriteReplaceInterface, Factory {

...
//增强的 getter 和 settter 方法
public final int getId()
  {
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_0;
    if (tmp17_14 != null)
    {
      Object tmp36_31 = tmp17_14.intercept(this, CGLIB$getId$0$Method, CGLIB$emptyArgs, CGLIB$getId$0$Proxy);
      tmp36_31;
      return tmp36_31 == null ? 0 : ((Number)tmp36_31).intValue();
    }
    return super.getId();
  }

...

public final void setUserId(int paramInt)
  {
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    if (this.CGLIB$CALLBACK_0 != null) {
      return;
    }
    super.setUserId(paramInt);
  }
  
...

public final String getUserInfo()
{
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_0;
    if (tmp17_14 != null) {
      return (String)tmp17_14.intercept(this, CGLIB$getUsername$1$Method, CGLIB$emptyArgs, CGLIB$getUsername$1$Proxy);
    }
    return super.getUsername();
}

```

##### 3.在调用上述实体方法的时候，会通过调用 CglibProxyFactory 的 intercept 方式执行特殊的逻辑处理，如下：
```java
public Object intercept(Object enhanced, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
      final String methodName = method.getName();
      try {
        synchronized (lazyLoader) {
          if (WRITE_REPLACE_METHOD.equals(methodName)) {
            Object original = null;
            if (constructorArgTypes.isEmpty()) {
              original = objectFactory.create(type);
            } else {
              original = objectFactory.create(type, constructorArgTypes, constructorArgs);
            }
            PropertyCopier.copyBeanProperties(type, enhanced, original);
            if (lazyLoader.size() > 0) {
              return new CglibSerialStateHolder(original, lazyLoader.getProperties(), objectFactory, constructorArgTypes, constructorArgs);
            } else {
              return original;
            }
          } else {
            if (lazyLoader.size() > 0 && !FINALIZE_METHOD.equals(methodName)) {
              if (aggressive || lazyLoadTriggerMethods.contains(methodName)) {
                lazyLoader.loadAll();
              } else if (PropertyNamer.isProperty(methodName)) {
                final String property = PropertyNamer.methodToProperty(methodName);
                if (lazyLoader.hasLoader(property)) {
                  lazyLoader.load(property);
                }
              }
            }
          }
        }
        return methodProxy.invokeSuper(enhanced, args);
      } catch (Throwable t) {
        throw ExceptionUtil.unwrapThrowable(t);
      }
    }
  }

```
上述代码最重要的根据 aggressive 和 lazyLoader 变量来决定是否懒加载数据。