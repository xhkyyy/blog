### kubernetes 下运行 dubbo 服务方案总结

目前 kubernetes 还没有提供“随机端口”的特性，所以实现起来比较麻烦。

下面前 2 种方案主要考虑的场景是 dubbo 运行在 kubernetes 集群中，但是也希望 kubernetes 集群“外”的应用也能使用这些 dubbo 服务。

实现原理：


 - dubbo 已经提供了 2 个环境变量： DUBBO_IP_TO_REGISTRY、DUBBO_PORT_TO_REGISTRY 分别表示服务注册到注册中心的 IP 和 PORT。

 - kubernetes 的 client-go 可以获取到当前 Pod 对应的 RC 或 Deployment 的信息，比如，它对应的 Service IP 和 PORT。（可以把这部分功能嵌入到 dubbo 启动脚本中）

 - Pod 在启动之后，在环境变量中会提供当前 Pod 的宿主主机 IP 等等一系列环境变量信息（进入 Pod，输入 env 查看）。


具体方案如下：

### 1.dubbo 注册 service 的 ip + port

调用方式：
 - k8s service -> dubbo 服务

实现方法：

 - 在 dubbo 启动前，通过 kubernetes 的 client-go 获取到当前 Pod 对应的 Service IP 和 PORT。

 - 如果希望 kubernetes 集群外的服务也能够访问当前的 dubbo，那么，Service IP 可以使用 Node IP，Service PORT 可以使用 NODE PORT。

 - 指定环境变量 DUBBO_IP_TO_REGISTRY、DUBBO_PORT_TO_REGISTRY 分别为上述 IP 和 PORT，并启动 dubbo。


缺点：

 - 同一个 dubbo 服务有可能会有多个实例（Pod），由于都是使用了 service ip+port 的注册方式 ,所以在 dubbo 注册中心中，它们的 IP+PORT 是相同的。
目前还不清楚这回带来什么问题，测试中暂未发现有任何异常。

优点：

 - 没有破坏 kubernetes 的 service -> pod 的访问模式。

 - 不用管理 dubbo 端口号。



### 2.dubbo 注册特定端口号

调用方式：

 - 直接访问 Pod，不经过 service

实现方法：

 - 自己实现一个 dubbo 端口号池。

 - 应用创建时，从池中获取一个可用的端口号，分配给 dubbo。并在 RC 或 Deployment 中做好端口映射。

 - 当然，实例释放时，也要释放端口号到池中。

 - Pod 以 HOST 模式运行时，这时向注册中心注册时可以使用 HOST IP + 上述端口号运行。如果不是 HOST 模式，
 可以使用前述的环境变量获取当前 Pod HOST IP 来完成注册。


缺点：

 - 需要管理 dubbo 端口号。

 - 实例释放时需要回收端口号。

 - 由于要规避同一个 Node 上端口号冲突，所以，如果 dubbo 实例特别特别的多（假如），那么端口号的利用率会比较低，且有限。




### 3.dubbo 注册 kubernetes 的虚拟 IP + 端口
如果 dubbo 不对 kubernetes 集群外开放，完全可以直接使用相关 pod 或 service 的虚拟 ip 注册即可。
原理一样，借助 DUBBO_IP_TO_REGISTRY、DUBBO_PORT_TO_REGISTRY  指定 IP 和 PORT。