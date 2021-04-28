# 云原生服务网格Istio：原理、实践、架构与源码解析

> https://book.douban.com/subject/34438220/?dt_dapp=1

+ 补齐了对k8s的缺失短板：熔断限流、动态路由、调用链路追踪等功能
+ 控制面和数据面都运行在k8s中
+ 网格外的服务也加入到网格内治理的方式是使用ServiceEntry和Egress Gateway
+ 流量治理可以到4到7层
+ 只要有访问就都可以使用Istio进行治理，不在乎服务是微服务还是其它什么类型
+ 一旦启用Istio，k8s API会监控pod被创建过程，就会注入sidecar
+ 
