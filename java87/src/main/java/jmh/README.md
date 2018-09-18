# JMH

> What is JMH?

     http://openjdk.java.net/projects/code-tools/jmh/
     http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/

> 一开始输出本次运行相关参数信息，然后开始预热，其后就开始正常运行了。

> Benchmark mode: Throughput, ops/time 说明运行类型是吞吐量，ops/time 操作数/时间

> 有几个 forks，最后就有几个结论对比结果

    Fork: 1 of 2
    ...
    
    # Fork: 2 of 2
    ...
    
    如果 fork 数是2的话，则 JMH 会 fork 出两个进程来进行测试。

> 最后，Result 输出总的结论。

> 如果 Benchmark mode 是 Throughput，那么 Cnt = formNumber * measurementIterations

> 模式分类：

    SingleShotTime: 用于测试冷启动的性能
    Throughput:     吞吐量，如 1 秒内可以执行多少次调用
    AverageTime:    每次调用的平均耗时
    SampleTime:     随机采样，例如 80%在多少毫秒内
    All:            所有模式
    

文章参考：

http://www.importnew.com/12548.html




### 其它

+ @OperationsPerInvocation 指明方法中循环迭代的次数，以让 JMH 适当的调整得分。
+ @CompilerControl 控制编译的行为。

### 完整输出：

    # JMH version: 1.21
    # VM version: JDK 1.8.0_162, Java HotSpot(TM) 64-Bit Server VM, 25.162-b12
    # VM invoker: /Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre/bin/java
    # VM options: -Dvisualvm.id=21609288242870 -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=51453:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8
    # Warmup: 5 iterations, 10 s each
    # Measurement: 5 iterations, 10 s each
    # Timeout: 10 min per iteration
    # Threads: 1 thread, will synchronize iterations
    # Benchmark mode: Throughput, ops/time
    # Benchmark: jmh.JMHSample_01_HelloWorld.wellHelloThere    

    # Run progress: 0.00% complete, ETA 00:03:20
    # Fork: 1 of 2
    # Warmup Iteration   1: 1901601104.671 ops/s
    # Warmup Iteration   2: 1949337987.779 ops/s
    # Warmup Iteration   3: 2073126866.033 ops/s
    # Warmup Iteration   4: 2420889484.256 ops/s
    # Warmup Iteration   5: 2600587447.448 ops/s
    Iteration   1: 2565847760.892 ops/s
    Iteration   2: 2435371800.312 ops/s
    Iteration   3: 2668679678.889 ops/s
    Iteration   4: 2627924108.397 ops/s
    Iteration   5: 2399851423.400 ops/s    

    # Run progress: 50.00% complete, ETA 00:01:42
    # Fork: 2 of 2
    # Warmup Iteration   1: 2532773403.757 ops/s
    # Warmup Iteration   2: 2596854826.345 ops/s
    # Warmup Iteration   3: 2522539961.071 ops/s
    # Warmup Iteration   4: 2458207799.600 ops/s
    # Warmup Iteration   5: 2625368639.928 ops/s
    Iteration   1: 2732056384.577 ops/s
    Iteration   2: 2610265815.025 ops/s
    Iteration   3: 2743990703.195 ops/s
    Iteration   4: 2797743728.898 ops/s
    Iteration   5: 2973266402.912 ops/s    
    

    Result "jmh.JMHSample_01_HelloWorld.wellHelloThere":
      2655499780.650 ±(99.9%) 256833196.122 ops/s [Average]
      (min, avg, max) = (2399851423.400, 2655499780.650, 2973266402.912), stdev = 169879257.127
      CI (99.9%): [2398666584.527, 2912332976.772] (assumes normal distribution)    
    

    # Run complete. Total time: 00:03:23    

    REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
    why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
    experiments, perform baseline and negative tests that provide experimental control, make sure
    the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
    Do not assume the numbers tell you what you want them to tell.    

    Benchmark                                Mode  Cnt           Score           Error  Units
    JMHSample_01_HelloWorld.wellHelloThere  thrpt   10  2655499780.650 ± 256833196.122  ops/s    

    Process finished with exit code 0    

    # JMH version: 1.21
    # VM version: JDK 1.8.0_162, Java HotSpot(TM) 64-Bit Server VM, 25.162-b12
    # VM invoker: /Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre/bin/java
    # VM options: -Dvisualvm.id=21609288242870 -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=51453:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8
    # Warmup: 5 iterations, 10 s each
    # Measurement: 5 iterations, 10 s each
    # Timeout: 10 min per iteration
    # Threads: 1 thread, will synchronize iterations
    # Benchmark mode: Throughput, ops/time
    # Benchmark: jmh.JMHSample_01_HelloWorld.wellHelloThere    

    # Run progress: 0.00% complete, ETA 00:03:20
    # Fork: 1 of 2
    # Warmup Iteration   1: 1901601104.671 ops/s
    # Warmup Iteration   2: 1949337987.779 ops/s
    # Warmup Iteration   3: 2073126866.033 ops/s
    # Warmup Iteration   4: 2420889484.256 ops/s
    # Warmup Iteration   5: 2600587447.448 ops/s
    Iteration   1: 2565847760.892 ops/s
    Iteration   2: 2435371800.312 ops/s
    Iteration   3: 2668679678.889 ops/s
    Iteration   4: 2627924108.397 ops/s
    Iteration   5: 2399851423.400 ops/s    

    # Run progress: 50.00% complete, ETA 00:01:42
    # Fork: 2 of 2
    # Warmup Iteration   1: 2532773403.757 ops/s
    # Warmup Iteration   2: 2596854826.345 ops/s
    # Warmup Iteration   3: 2522539961.071 ops/s
    # Warmup Iteration   4: 2458207799.600 ops/s
    # Warmup Iteration   5: 2625368639.928 ops/s
    Iteration   1: 2732056384.577 ops/s
    Iteration   2: 2610265815.025 ops/s
    Iteration   3: 2743990703.195 ops/s
    Iteration   4: 2797743728.898 ops/s
    Iteration   5: 2973266402.912 ops/s    
    

    Result "jmh.JMHSample_01_HelloWorld.wellHelloThere":
      2655499780.650 ±(99.9%) 256833196.122 ops/s [Average]
      (min, avg, max) = (2399851423.400, 2655499780.650, 2973266402.912), stdev = 169879257.127
      CI (99.9%): [2398666584.527, 2912332976.772] (assumes normal distribution)    
    

    # Run complete. Total time: 00:03:23    

    REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
    why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
    experiments, perform baseline and negative tests that provide experimental control, make sure
    the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
    Do not assume the numbers tell you what you want them to tell.    

    Benchmark                                Mode  Cnt           Score           Error  Units
    JMHSample_01_HelloWorld.wellHelloThere  thrpt   10  2655499780.650 ± 256833196.122  ops/s    

    Process finished with exit code 0    

    # JMH version: 1.21
    # VM version: JDK 1.8.0_162, Java HotSpot(TM) 64-Bit Server VM, 25.162-b12
    # VM invoker: /Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre/bin/java
    # VM options: -Dvisualvm.id=21609288242870 -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=51453:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8
    # Warmup: 5 iterations, 10 s each
    # Measurement: 5 iterations, 10 s each
    # Timeout: 10 min per iteration
    # Threads: 1 thread, will synchronize iterations
    # Benchmark mode: Throughput, ops/time
    # Benchmark: jmh.JMHSample_01_HelloWorld.wellHelloThere    

    # Run progress: 0.00% complete, ETA 00:03:20
    # Fork: 1 of 2
    # Warmup Iteration   1: 1901601104.671 ops/s
    # Warmup Iteration   2: 1949337987.779 ops/s
    # Warmup Iteration   3: 2073126866.033 ops/s
    # Warmup Iteration   4: 2420889484.256 ops/s
    # Warmup Iteration   5: 2600587447.448 ops/s
    Iteration   1: 2565847760.892 ops/s
    Iteration   2: 2435371800.312 ops/s
    Iteration   3: 2668679678.889 ops/s
    Iteration   4: 2627924108.397 ops/s
    Iteration   5: 2399851423.400 ops/s    

    # Run progress: 50.00% complete, ETA 00:01:42
    # Fork: 2 of 2
    # Warmup Iteration   1: 2532773403.757 ops/s
    # Warmup Iteration   2: 2596854826.345 ops/s
    # Warmup Iteration   3: 2522539961.071 ops/s
    # Warmup Iteration   4: 2458207799.600 ops/s
    # Warmup Iteration   5: 2625368639.928 ops/s
    Iteration   1: 2732056384.577 ops/s
    Iteration   2: 2610265815.025 ops/s
    Iteration   3: 2743990703.195 ops/s
    Iteration   4: 2797743728.898 ops/s
    Iteration   5: 2973266402.912 ops/s    
    

    Result "jmh.JMHSample_01_HelloWorld.wellHelloThere":
      2655499780.650 ±(99.9%) 256833196.122 ops/s [Average]
      (min, avg, max) = (2399851423.400, 2655499780.650, 2973266402.912), stdev = 169879257.127
      CI (99.9%): [2398666584.527, 2912332976.772] (assumes normal distribution)    
    

    # Run complete. Total time: 00:03:23    

    REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
    why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
    experiments, perform baseline and negative tests that provide experimental control, make sure
    the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
    Do not assume the numbers tell you what you want them to tell.    

    Benchmark                                Mode  Cnt           Score           Error  Units
    JMHSample_01_HelloWorld.wellHelloThere  thrpt   10  2655499780.650 ± 256833196.122  ops/s    

    Process finished with exit code 0    

    # JMH version: 1.21
    # VM version: JDK 1.8.0_162, Java HotSpot(TM) 64-Bit Server VM, 25.162-b12
    # VM invoker: /Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre/bin/java
    # VM options: -Dvisualvm.id=21609288242870 -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=51453:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8
    # Warmup: 5 iterations, 10 s each
    # Measurement: 5 iterations, 10 s each
    # Timeout: 10 min per iteration
    # Threads: 1 thread, will synchronize iterations
    # Benchmark mode: Throughput, ops/time
    # Benchmark: jmh.JMHSample_01_HelloWorld.wellHelloThere    

    # Run progress: 0.00% complete, ETA 00:03:20
    # Fork: 1 of 2
    # Warmup Iteration   1: 1901601104.671 ops/s
    # Warmup Iteration   2: 1949337987.779 ops/s
    # Warmup Iteration   3: 2073126866.033 ops/s
    # Warmup Iteration   4: 2420889484.256 ops/s
    # Warmup Iteration   5: 2600587447.448 ops/s
    Iteration   1: 2565847760.892 ops/s
    Iteration   2: 2435371800.312 ops/s
    Iteration   3: 2668679678.889 ops/s
    Iteration   4: 2627924108.397 ops/s
    Iteration   5: 2399851423.400 ops/s    

    # Run progress: 50.00% complete, ETA 00:01:42
    # Fork: 2 of 2
    # Warmup Iteration   1: 2532773403.757 ops/s
    # Warmup Iteration   2: 2596854826.345 ops/s
    # Warmup Iteration   3: 2522539961.071 ops/s
    # Warmup Iteration   4: 2458207799.600 ops/s
    # Warmup Iteration   5: 2625368639.928 ops/s
    Iteration   1: 2732056384.577 ops/s
    Iteration   2: 2610265815.025 ops/s
    Iteration   3: 2743990703.195 ops/s
    Iteration   4: 2797743728.898 ops/s
    Iteration   5: 2973266402.912 ops/s    
    

    Result "jmh.JMHSample_01_HelloWorld.wellHelloThere":
      2655499780.650 ±(99.9%) 256833196.122 ops/s [Average]
      (min, avg, max) = (2399851423.400, 2655499780.650, 2973266402.912), stdev = 169879257.127
      CI (99.9%): [2398666584.527, 2912332976.772] (assumes normal distribution)    
    

    # Run complete. Total time: 00:03:23    

    REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
    why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
    experiments, perform baseline and negative tests that provide experimental control, make sure
    the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
    Do not assume the numbers tell you what you want them to tell.    

    Benchmark                                Mode  Cnt           Score           Error  Units
    JMHSample_01_HelloWorld.wellHelloThere  thrpt   10  2655499780.650 ± 256833196.122  ops/s    

    Process finished with exit code 0
