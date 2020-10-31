# Enabling Transparent Huge Pages On Linux （😭）

An alternative to using explicit large pages (as described above) is to use transparent huge pages. Use of transparent huge pages is usually not recommended for latency sensitive applications, because it tends to cause unwanted latency spikes. However, it might be worth experimenting with to see if/how your workload is affected by it. But be aware, your mileage may vary.

NOTE! On Linux, using ZGC with transparent huge pages enabled requires kernel >= 4.7.

Use the following options to enable transparent huge pages in the VM:

```shell
-XX:+UseLargePages -XX:+UseTransparentHugePages
```

These options tell the JVM to issue madvise(..., MADV_HUGEPAGE) calls for memory it mapps, which is useful when using transparent huge pages in madvise mode.

To enable transparent huge pages you also need to configure the kernel, by enabling the madvise mode.

```shell
echo madvise > /sys/kernel/mm/transparent_hugepage/enabled
```

and

```shell
echo advise > /sys/kernel/mm/transparent_hugepage/shmem_enabled
```

See the kernel documentation for more information(https://www.kernel.org/doc/Documentation/vm/transhuge.txt).
