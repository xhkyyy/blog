# 关于堆

> OOP = ordinary object pointer 普通对象指针。

> https://docs.oracle.com/javase/7/docs/technotes/guides/vm/performance-enhancements-7.html#zeroBasedCompressedOop
> https://www.elastic.co/blog/a-heap-of-trouble


### 关于对象指针

关于堆：

+ 对于 32-bit 的 jvm，堆的大小只能在 4G 一下，使用 32-bit 对象指针
+ 对于 64-bit 的 jvm，当堆大小：
    + 小于 4G 时，jvm 使用的是 32-bit 指针（64位下模拟32位）
    + 大于 4G，且小于 32G 时(有些文章说是 30.5GB)，jvm 使用的是 zero-based compressed 指针
        + 为什么是 32G，因为 2^35 = 32 GB
        + native oop = (compressed oop << 3)
            + 为什么是 3（ `<< 3`），而不是其它数字？因为地址保持 8 字节对齐，对现代 CPU 架构更友好（cache）
            + 堆地址强制从 0 开始分配
            + jvm 会向操作系统申请保留一个虚拟地址为 0 开始的内存空间，如果操作系统同意（支持），那么，zero-based compressed oop 将启用
            + byte offset vs object offset
        + 使用 zero-based compressed 指针后，对象的指针表示就是内存的偏移量，而不是精确的地址
    + 大于 32G 时，jvm 使用 64-bit 指针

关于指针压缩

+ jvm 植入压缩指令，即当对象读取时解压指针，对象存入堆中时压缩

### 其它

+ 检查堆是否运行在 zero-based 下：-XX:+UnlockDiagnosticVMOptions -XX:+PrintCompressedOopsMode
    + 在 jvm 启动日志中会出现类似的信息：heap address: 0x000000011be00000, size: 27648 MB, zero based Compressed Oops


### 关于 G1

由于 G1 的 Bug 会引起 Lucene 一些问题，所以，ES 当前并不建议使用 G1 作为默认的垃圾收集器。