
# JMHSample_02_BenchmarkModes


```
# JMH version: 1.21
# VM version: JDK 1.8.0_171, Java HotSpot(TM) 64-Bit Server VM, 25.171-b11


# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: jmh.JMHSample_02_BenchmarkModes.measureAll

# Run progress: 0.00% complete, ETA 00:15:00
# Fork: 1 of 1
# Warmup Iteration   1: ≈ 10⁻⁵ ops/us
# Warmup Iteration   2: ≈ 10⁻⁵ ops/us
# Warmup Iteration   3: ≈ 10⁻⁵ ops/us
# Warmup Iteration   4: ≈ 10⁻⁵ ops/us
# Warmup Iteration   5: ≈ 10⁻⁵ ops/us
Iteration   1: ≈ 10⁻⁵ ops/us
Iteration   2: ≈ 10⁻⁵ ops/us
Iteration   3: ≈ 10⁻⁵ ops/us
Iteration   4: ≈ 10⁻⁵ ops/us
Iteration   5: ≈ 10⁻⁵ ops/us


Result "jmh.JMHSample_02_BenchmarkModes.measureAll":
  ≈ 10⁻⁵ ops/us


# JMH version: 1.21
# VM version: JDK 1.8.0_171, Java HotSpot(TM) 64-Bit Server VM, 25.171-b11


# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: jmh.JMHSample_02_BenchmarkModes.measureMultiple

# Run progress: 11.11% complete, ETA 00:13:29
# Fork: 1 of 1
# Warmup Iteration   1: ≈ 10⁻⁵ ops/us
# Warmup Iteration   2: ≈ 10⁻⁵ ops/us
# Warmup Iteration   3: ≈ 10⁻⁵ ops/us
# Warmup Iteration   4: ≈ 10⁻⁵ ops/us
# Warmup Iteration   5: ≈ 10⁻⁵ ops/us
Iteration   1: ≈ 10⁻⁵ ops/us
Iteration   2: ≈ 10⁻⁵ ops/us
Iteration   3: ≈ 10⁻⁵ ops/us
Iteration   4: ≈ 10⁻⁵ ops/us
Iteration   5: ≈ 10⁻⁵ ops/us


Result "jmh.JMHSample_02_BenchmarkModes.measureMultiple":
  ≈ 10⁻⁵ ops/us


# JMH version: 1.21
# VM version: JDK 1.8.0_171, Java HotSpot(TM) 64-Bit Server VM, 25.171-b11


# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: jmh.JMHSample_02_BenchmarkModes.measureThroughput

# Run progress: 22.22% complete, ETA 00:11:48
# Fork: 1 of 1
# Warmup Iteration   1: 9.952 ops/s
# Warmup Iteration   2: 9.945 ops/s
# Warmup Iteration   3: 9.954 ops/s
# Warmup Iteration   4: 9.945 ops/s
# Warmup Iteration   5: 9.948 ops/s
Iteration   1: 9.958 ops/s
Iteration   2: 9.948 ops/s
Iteration   3: 9.955 ops/s
Iteration   4: 9.940 ops/s
Iteration   5: 9.948 ops/s


Result "jmh.JMHSample_02_BenchmarkModes.measureThroughput":
  9.950 ±(99.9%) 0.027 ops/s [Average]
  (min, avg, max) = (9.940, 9.950, 9.958), stdev = 0.007
  CI (99.9%): [9.923, 9.977] (assumes normal distribution)


# JMH version: 1.21
# VM version: JDK 1.8.0_171, Java HotSpot(TM) 64-Bit Server VM, 25.171-b11


# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: jmh.JMHSample_02_BenchmarkModes.measureAll

# Run progress: 33.33% complete, ETA 00:10:07
# Fork: 1 of 1
# Warmup Iteration   1: 100523.461 us/op
# Warmup Iteration   2: 100523.574 us/op
# Warmup Iteration   3: 100524.482 us/op
# Warmup Iteration   4: 100543.507 us/op
# Warmup Iteration   5: 100553.692 us/op
Iteration   1: 100524.085 us/op
Iteration   2: 100525.682 us/op
Iteration   3: 100524.285 us/op
Iteration   4: 100524.296 us/op
Iteration   5: 100523.986 us/op


Result "jmh.JMHSample_02_BenchmarkModes.measureAll":
  100524.467 ±(99.9%) 2.665 us/op [Average]
  (min, avg, max) = (100523.986, 100524.467, 100525.682), stdev = 0.692
  CI (99.9%): [100521.802, 100527.132] (assumes normal distribution)


# JMH version: 1.21
# VM version: JDK 1.8.0_171, Java HotSpot(TM) 64-Bit Server VM, 25.171-b11


# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: jmh.JMHSample_02_BenchmarkModes.measureAvgTime

# Run progress: 44.44% complete, ETA 00:08:25
# Fork: 1 of 1
# Warmup Iteration   1: 100520.292 us/op
# Warmup Iteration   2: 100523.155 us/op
# Warmup Iteration   3: 100523.961 us/op
# Warmup Iteration   4: 100543.230 us/op
# Warmup Iteration   5: 100522.538 us/op
Iteration   1: 100524.416 us/op
Iteration   2: 100523.483 us/op
Iteration   3: 100523.734 us/op
Iteration   4: 100524.478 us/op
Iteration   5: 100523.063 us/op


Result "jmh.JMHSample_02_BenchmarkModes.measureAvgTime":
  100523.835 ±(99.9%) 2.343 us/op [Average]
  (min, avg, max) = (100523.063, 100523.835, 100524.478), stdev = 0.609
  CI (99.9%): [100521.492, 100526.178] (assumes normal distribution)


# JMH version: 1.21
# VM version: JDK 1.8.0_171, Java HotSpot(TM) 64-Bit Server VM, 25.171-b11


# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: jmh.JMHSample_02_BenchmarkModes.measureMultiple

# Run progress: 55.56% complete, ETA 00:06:44
# Fork: 1 of 1
# Warmup Iteration   1: 100522.673 us/op
# Warmup Iteration   2: 100526.079 us/op
# Warmup Iteration   3: 100524.777 us/op
# Warmup Iteration   4: 100543.853 us/op
# Warmup Iteration   5: 100524.209 us/op
Iteration   1: 100534.142 us/op
Iteration   2: 100469.194 us/op
Iteration   3: 100488.937 us/op
Iteration   4: 100542.646 us/op
Iteration   5: 100491.308 us/op


Result "jmh.JMHSample_02_BenchmarkModes.measureMultiple":
  100505.246 ±(99.9%) 121.673 us/op [Average]
  (min, avg, max) = (100469.194, 100505.246, 100542.646), stdev = 31.598
  CI (99.9%): [100383.573, 100626.919] (assumes normal distribution)


# JMH version: 1.21
# VM version: JDK 1.8.0_171, Java HotSpot(TM) 64-Bit Server VM, 25.171-b11


# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Sampling time
# Benchmark: jmh.JMHSample_02_BenchmarkModes.measureAll

# Run progress: 66.67% complete, ETA 00:05:03
# Fork: 1 of 1
# Warmup Iteration   1: 100428.677 ±(99.9%) 103.300 us/op
# Warmup Iteration   2: 100450.959 ±(99.9%) 95.149 us/op
# Warmup Iteration   3: 100440.474 ±(99.9%) 94.247 us/op
# Warmup Iteration   4: 100467.999 ±(99.9%) 119.964 us/op
# Warmup Iteration   5: 100411.638 ±(99.9%) 95.547 us/op
Iteration   1: 100466.688 ±(99.9%) 96.961 us/op
                 measureAll·p0.00:   100007.936 us/op
                 measureAll·p0.50:   100532.224 us/op
                 measureAll·p0.90:   100794.368 us/op
                 measureAll·p0.95:   100925.440 us/op
                 measureAll·p0.99:   100925.440 us/op
                 measureAll·p0.999:  100925.440 us/op
                 measureAll·p0.9999: 100925.440 us/op
                 measureAll·p1.00:   100925.440 us/op

Iteration   2: 100402.463 ±(99.9%) 107.689 us/op
                 measureAll·p0.00:   99614.720 us/op
                 measureAll·p0.50:   100401.152 us/op
                 measureAll·p0.90:   100794.368 us/op
                 measureAll·p0.95:   100925.440 us/op
                 measureAll·p0.99:   101055.201 us/op
                 measureAll·p0.999:  101056.512 us/op
                 measureAll·p0.9999: 101056.512 us/op
                 measureAll·p1.00:   101056.512 us/op

Iteration   3: 100461.445 ±(99.9%) 99.586 us/op
                 measureAll·p0.00:   99876.864 us/op
                 measureAll·p0.50:   100532.224 us/op
                 measureAll·p0.90:   100794.368 us/op
                 measureAll·p0.95:   100925.440 us/op
                 measureAll·p0.99:   100925.440 us/op
                 measureAll·p0.999:  100925.440 us/op
                 measureAll·p0.9999: 100925.440 us/op
                 measureAll·p1.00:   100925.440 us/op

Iteration   4: 100487.660 ±(99.9%) 99.144 us/op
                 measureAll·p0.00:   100007.936 us/op
                 measureAll·p0.50:   100532.224 us/op
                 measureAll·p0.90:   100794.368 us/op
                 measureAll·p0.95:   100925.440 us/op
                 measureAll·p0.99:   101055.201 us/op
                 measureAll·p0.999:  101056.512 us/op
                 measureAll·p0.9999: 101056.512 us/op
                 measureAll·p1.00:   101056.512 us/op

Iteration   5: 100449.649 ±(99.9%) 95.333 us/op
                 measureAll·p0.00:   99745.792 us/op
                 measureAll·p0.50:   100401.152 us/op
                 measureAll·p0.90:   100794.368 us/op
                 measureAll·p0.95:   100925.440 us/op
                 measureAll·p0.99:   100925.440 us/op
                 measureAll·p0.999:  100925.440 us/op
                 measureAll·p0.9999: 100925.440 us/op
                 measureAll·p1.00:   100925.440 us/op



Result "jmh.JMHSample_02_BenchmarkModes.measureAll":
  N = 500
  mean = 100453.581 ±(99.9%) 43.603 us/op

  Histogram, us/op:
    [ 99000.000,  99250.000) = 0 
    [ 99250.000,  99500.000) = 0 
    [ 99500.000,  99750.000) = 2 
    [ 99750.000, 100000.000) = 6 
    [100000.000, 100250.000) = 120 
    [100250.000, 100500.000) = 119 
    [100500.000, 100750.000) = 143 
    [100750.000, 101000.000) = 108 
    [101000.000, 101250.000) = 2 
    [101250.000, 101500.000) = 0 
    [101500.000, 101750.000) = 0 

  Percentiles, us/op:
      p(0.0000) =  99614.720 us/op
     p(50.0000) = 100532.224 us/op
     p(90.0000) = 100794.368 us/op
     p(95.0000) = 100925.440 us/op
     p(99.0000) = 100925.440 us/op
     p(99.9000) = 101056.512 us/op
     p(99.9900) = 101056.512 us/op
     p(99.9990) = 101056.512 us/op
     p(99.9999) = 101056.512 us/op
    p(100.0000) = 101056.512 us/op


# JMH version: 1.21
# VM version: JDK 1.8.0_171, Java HotSpot(TM) 64-Bit Server VM, 25.171-b11


# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Sampling time
# Benchmark: jmh.JMHSample_02_BenchmarkModes.measureMultiple

# Run progress: 77.78% complete, ETA 00:03:22
# Fork: 1 of 1
# Warmup Iteration   1: 100412.948 ±(99.9%) 107.243 us/op
# Warmup Iteration   2: 100458.824 ±(99.9%) 96.511 us/op
# Warmup Iteration   3: 100363.141 ±(99.9%) 99.159 us/op
# Warmup Iteration   4: 100532.224 ±(99.9%) 221.683 us/op
# Warmup Iteration   5: 100437.852 ±(99.9%) 95.422 us/op
Iteration   1: 100457.513 ±(99.9%) 96.291 us/op
                 measureMultiple·p0.00:   100007.936 us/op
                 measureMultiple·p0.50:   100466.688 us/op
                 measureMultiple·p0.90:   100794.368 us/op
                 measureMultiple·p0.95:   100925.440 us/op
                 measureMultiple·p0.99:   101055.201 us/op
                 measureMultiple·p0.999:  101056.512 us/op
                 measureMultiple·p0.9999: 101056.512 us/op
                 measureMultiple·p1.00:   101056.512 us/op

Iteration   2: 100481.106 ±(99.9%) 96.009 us/op
                 measureMultiple·p0.00:   99876.864 us/op
                 measureMultiple·p0.50:   100532.224 us/op
                 measureMultiple·p0.90:   100794.368 us/op
                 measureMultiple·p0.95:   100925.440 us/op
                 measureMultiple·p0.99:   100925.440 us/op
                 measureMultiple·p0.999:  100925.440 us/op
                 measureMultiple·p0.9999: 100925.440 us/op
                 measureMultiple·p1.00:   100925.440 us/op

Iteration   3: 100443.095 ±(99.9%) 98.266 us/op
                 measureMultiple·p0.00:   99876.864 us/op
                 measureMultiple·p0.50:   100401.152 us/op
                 measureMultiple·p0.90:   100794.368 us/op
                 measureMultiple·p0.95:   100925.440 us/op
                 measureMultiple·p0.99:   100925.440 us/op
                 measureMultiple·p0.999:  100925.440 us/op
                 measureMultiple·p0.9999: 100925.440 us/op
                 measureMultiple·p1.00:   100925.440 us/op

Iteration   4: 100424.745 ±(99.9%) 103.702 us/op
                 measureMultiple·p0.00:   99876.864 us/op
                 measureMultiple·p0.50:   100401.152 us/op
                 measureMultiple·p0.90:   100794.368 us/op
                 measureMultiple·p0.95:   100925.440 us/op
                 measureMultiple·p0.99:   100925.440 us/op
                 measureMultiple·p0.999:  100925.440 us/op
                 measureMultiple·p0.9999: 100925.440 us/op
                 measureMultiple·p1.00:   100925.440 us/op

Iteration   5: 100440.474 ±(99.9%) 103.532 us/op
                 measureMultiple·p0.00:   99876.864 us/op
                 measureMultiple·p0.50:   100466.688 us/op
                 measureMultiple·p0.90:   100925.440 us/op
                 measureMultiple·p0.95:   100925.440 us/op
                 measureMultiple·p0.99:   100925.440 us/op
                 measureMultiple·p0.999:  100925.440 us/op
                 measureMultiple·p0.9999: 100925.440 us/op
                 measureMultiple·p1.00:   100925.440 us/op



Result "jmh.JMHSample_02_BenchmarkModes.measureMultiple":
  N = 500
  mean = 100449.386 ±(99.9%) 43.398 us/op

  Histogram, us/op:
    [ 99000.000,  99250.000) = 0 
    [ 99250.000,  99500.000) = 0 
    [ 99500.000,  99750.000) = 0 
    [ 99750.000, 100000.000) = 6 
    [100000.000, 100250.000) = 120 
    [100250.000, 100500.000) = 126 
    [100500.000, 100750.000) = 141 
    [100750.000, 101000.000) = 106 
    [101000.000, 101250.000) = 1 
    [101250.000, 101500.000) = 0 
    [101500.000, 101750.000) = 0 

  Percentiles, us/op:
      p(0.0000) =  99876.864 us/op
     p(50.0000) = 100401.152 us/op
     p(90.0000) = 100794.368 us/op
     p(95.0000) = 100925.440 us/op
     p(99.0000) = 100925.440 us/op
     p(99.9000) = 101056.512 us/op
     p(99.9900) = 101056.512 us/op
     p(99.9990) = 101056.512 us/op
     p(99.9999) = 101056.512 us/op
    p(100.0000) = 101056.512 us/op


# JMH version: 1.21
# VM version: JDK 1.8.0_171, Java HotSpot(TM) 64-Bit Server VM, 25.171-b11


# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Sampling time
# Benchmark: jmh.JMHSample_02_BenchmarkModes.measureSamples

# Run progress: 88.89% complete, ETA 00:01:41
# Fork: 1 of 1
# Warmup Iteration   1: 100432.609 ±(99.9%) 97.499 us/op
# Warmup Iteration   2: 100428.677 ±(99.9%) 105.214 us/op
# Warmup Iteration   3: 100399.841 ±(99.9%) 99.801 us/op
# Warmup Iteration   4: 100530.913 ±(99.9%) 250.791 us/op
# Warmup Iteration   5: 100479.795 ±(99.9%) 95.614 us/op
Iteration   1: 100415.570 ±(99.9%) 93.267 us/op
                 measureSamples·p0.00:   99876.864 us/op
                 measureSamples·p0.50:   100401.152 us/op
                 measureSamples·p0.90:   100794.368 us/op
                 measureSamples·p0.95:   100794.368 us/op
                 measureSamples·p0.99:   100925.440 us/op
                 measureSamples·p0.999:  100925.440 us/op
                 measureSamples·p0.9999: 100925.440 us/op
                 measureSamples·p1.00:   100925.440 us/op

Iteration   2: 100419.502 ±(99.9%) 107.043 us/op
                 measureSamples·p0.00:   99614.720 us/op
                 measureSamples·p0.50:   100401.152 us/op
                 measureSamples·p0.90:   100794.368 us/op
                 measureSamples·p0.95:   100925.440 us/op
                 measureSamples·p0.99:   100925.440 us/op
                 measureSamples·p0.999:  100925.440 us/op
                 measureSamples·p0.9999: 100925.440 us/op
                 measureSamples·p1.00:   100925.440 us/op

Iteration   3: 100466.688 ±(99.9%) 98.998 us/op
                 measureSamples·p0.00:   99876.864 us/op
                 measureSamples·p0.50:   100532.224 us/op
                 measureSamples·p0.90:   100794.368 us/op
                 measureSamples·p0.95:   100925.440 us/op
                 measureSamples·p0.99:   100925.440 us/op
                 measureSamples·p0.999:  100925.440 us/op
                 measureSamples·p0.9999: 100925.440 us/op
                 measureSamples·p1.00:   100925.440 us/op

Iteration   4: 100402.463 ±(99.9%) 105.819 us/op
                 measureSamples·p0.00:   99876.864 us/op
                 measureSamples·p0.50:   100401.152 us/op
                 measureSamples·p0.90:   100794.368 us/op
                 measureSamples·p0.95:   100925.440 us/op
                 measureSamples·p0.99:   100925.440 us/op
                 measureSamples·p0.999:  100925.440 us/op
                 measureSamples·p0.9999: 100925.440 us/op
                 measureSamples·p1.00:   100925.440 us/op

Iteration   5: 100389.356 ±(99.9%) 100.518 us/op
                 measureSamples·p0.00:   99876.864 us/op
                 measureSamples·p0.50:   100401.152 us/op
                 measureSamples·p0.90:   100794.368 us/op
                 measureSamples·p0.95:   100794.368 us/op
                 measureSamples·p0.99:   100925.440 us/op
                 measureSamples·p0.999:  100925.440 us/op
                 measureSamples·p0.9999: 100925.440 us/op
                 measureSamples·p1.00:   100925.440 us/op



Result "jmh.JMHSample_02_BenchmarkModes.measureSamples":
  N = 500
  mean = 100418.716 ±(99.9%) 44.188 us/op

  Histogram, us/op:
    [ 99000.000,  99125.000) = 0 
    [ 99125.000,  99250.000) = 0 
    [ 99250.000,  99375.000) = 0 
    [ 99375.000,  99500.000) = 0 
    [ 99500.000,  99625.000) = 1 
    [ 99625.000,  99750.000) = 0 
    [ 99750.000,  99875.000) = 0 
    [ 99875.000, 100000.000) = 12 
    [100000.000, 100125.000) = 73 
    [100125.000, 100250.000) = 59 
    [100250.000, 100375.000) = 60 
    [100375.000, 100500.000) = 66 
    [100500.000, 100625.000) = 71 
    [100625.000, 100750.000) = 56 
    [100750.000, 100875.000) = 73 

  Percentiles, us/op:
      p(0.0000) =  99614.720 us/op
     p(50.0000) = 100401.152 us/op
     p(90.0000) = 100794.368 us/op
     p(95.0000) = 100925.440 us/op
     p(99.0000) = 100925.440 us/op
     p(99.9000) = 100925.440 us/op
     p(99.9900) = 100925.440 us/op
     p(99.9990) = 100925.440 us/op
     p(99.9999) = 100925.440 us/op
    p(100.0000) = 100925.440 us/op


# JMH version: 1.21
# VM version: JDK 1.8.0_171, Java HotSpot(TM) 64-Bit Server VM, 25.171-b11


# Warmup: <none>
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: jmh.JMHSample_02_BenchmarkModes.measureAll

# Run progress: 100.00% complete, ETA 00:00:00
# Fork: 1 of 1
Iteration   1: 99282.654 us/op



# JMH version: 1.21
# VM version: JDK 1.8.0_171, Java HotSpot(TM) 64-Bit Server VM, 25.171-b11


# Warmup: <none>
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: jmh.JMHSample_02_BenchmarkModes.measureMultiple

# Run progress: 100.00% complete, ETA 00:00:00
# Fork: 1 of 1
Iteration   1: 100280.031 us/op



# JMH version: 1.21
# VM version: JDK 1.8.0_171, Java HotSpot(TM) 64-Bit Server VM, 25.171-b11


# Warmup: <none>
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: jmh.JMHSample_02_BenchmarkModes.measureSingleShot

# Run progress: 100.00% complete, ETA 00:00:00
# Fork: 1 of 1
Iteration   1: 99882.175 us/op



# Run complete. Total time: 00:15:12

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

Benchmark                                                              Mode  Cnt       Score     Error   Units
JMHSample_02_BenchmarkModes.measureAll                                thrpt    5      ≈ 10⁻⁵            ops/us
JMHSample_02_BenchmarkModes.measureMultiple                           thrpt    5      ≈ 10⁻⁵            ops/us
JMHSample_02_BenchmarkModes.measureThroughput                         thrpt    5       9.950 ±   0.027   ops/s
JMHSample_02_BenchmarkModes.measureAll                                 avgt    5  100524.467 ±   2.665   us/op
JMHSample_02_BenchmarkModes.measureAvgTime                             avgt    5  100523.835 ±   2.343   us/op
JMHSample_02_BenchmarkModes.measureMultiple                            avgt    5  100505.246 ± 121.673   us/op
JMHSample_02_BenchmarkModes.measureAll                               sample  500  100453.581 ±  43.603   us/op
JMHSample_02_BenchmarkModes.measureAll:measureAll·p0.00              sample        99614.720             us/op
JMHSample_02_BenchmarkModes.measureAll:measureAll·p0.50              sample       100532.224             us/op
JMHSample_02_BenchmarkModes.measureAll:measureAll·p0.90              sample       100794.368             us/op
JMHSample_02_BenchmarkModes.measureAll:measureAll·p0.95              sample       100925.440             us/op
JMHSample_02_BenchmarkModes.measureAll:measureAll·p0.99              sample       100925.440             us/op
JMHSample_02_BenchmarkModes.measureAll:measureAll·p0.999             sample       101056.512             us/op
JMHSample_02_BenchmarkModes.measureAll:measureAll·p0.9999            sample       101056.512             us/op
JMHSample_02_BenchmarkModes.measureAll:measureAll·p1.00              sample       101056.512             us/op
JMHSample_02_BenchmarkModes.measureMultiple                          sample  500  100449.386 ±  43.398   us/op
JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p0.00    sample        99876.864             us/op
JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p0.50    sample       100401.152             us/op
JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p0.90    sample       100794.368             us/op
JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p0.95    sample       100925.440             us/op
JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p0.99    sample       100925.440             us/op
JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p0.999   sample       101056.512             us/op
JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p0.9999  sample       101056.512             us/op
JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p1.00    sample       101056.512             us/op
JMHSample_02_BenchmarkModes.measureSamples                           sample  500  100418.716 ±  44.188   us/op
JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p0.00      sample        99614.720             us/op
JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p0.50      sample       100401.152             us/op
JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p0.90      sample       100794.368             us/op
JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p0.95      sample       100925.440             us/op
JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p0.99      sample       100925.440             us/op
JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p0.999     sample       100925.440             us/op
JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p0.9999    sample       100925.440             us/op
JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p1.00      sample       100925.440             us/op
JMHSample_02_BenchmarkModes.measureAll                                   ss        99282.654             us/op
JMHSample_02_BenchmarkModes.measureMultiple                              ss       100280.031             us/op
JMHSample_02_BenchmarkModes.measureSingleShot                            ss        99882.175             us/op

Process finished with exit code 0

```
