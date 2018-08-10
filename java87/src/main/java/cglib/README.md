# cglib

### [Debug](https://github.com/cglib/cglib/wiki/How-To)
+ 将生成的 class 保存到文件中的 2 个方法：
    + 在 JVM 启动参数中加入 `-Dcglib.debugLocation=目录`
    + 直接在应用程序中设置 System Property：`System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, 目录);`
+ 反编译上述 class 文件
    + 可以直接使用反编译工具查看
    + 也可以将上述 `目录` 加入到 classpath 中，这样在使用 IDE （如 IntelliJ IDEA) 的时候就能够在 Debugger 窗口中直接定位和反编译相应的 class 文件。