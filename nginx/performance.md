

## 为什么 Nginx 性能好？
***(TODO)***
+ 每个 Worker 绑定一个 CPU 核心，不会发生大量的上下文切换（与之对比的模型是：一个连接对应一个线程）

+ 每个连接占用的内存都非常小

+ 基于 event‑driven architecture

