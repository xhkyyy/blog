# 11 常见问题



### java 9+ 后不再支持 sum.misc

自己编译 `sum.misc` lib 后，通过 `--patch-module` 启动参数导入

```shell
--patch-module=jdk.unsupported=sum-misc.jar
```