### Pod 状态处于 Pending 状态

> https://kubernetes.io/cn/docs/tasks/debug-application-cluster/debug-application/

## 排查方法

 + 通过 kubectl describe pod 输出信息中是否包含无法调度的原因或者特殊的事件
 + 查看 Node 资源是否不足
 + 查看 Namespace 资源配额是否不足
 + 查看 Pod 调度是否包含特定的调度条件，而这些条件又不能得到满足，比如通过 Node-Selector 或者 Toleration 调度节点。
 + 查看 kubernetes event 是否有调度失败事件

## 遇到的几个案例

### 1. DaemonSet
```
场景：
使用 DaemonSet 调度 Pod 到一个 Node 上后，发现一直处于 pending 状态。
通过上述`排查方法`均查不到具体原因。

解决方法:
1) 使用 docker ps 查处相关的 POD 进程
2）docker stop POD 进程
```
