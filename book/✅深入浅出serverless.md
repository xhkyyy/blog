
# 深入浅出 serverless

> https://book.douban.com/subject/30465267/?dt_dapp=1

serverless 核心理想是用户无需关注支撑服务的底层资源，如主机。


+ serverless并不是不存在服务器，而是对用户透明
+ 大多数serverless云平台，用户只需要为处理请求的资源付费，在空闲时间段不会产生费用
+ 在 PaaS 下用户的关注度是应用和数据，而在serverless下用户的关注度是函数和数据
+ serverless由FaaS和BaaS构成
+ 是构建云原生应用的一种模式和思想
+ 用户可以以更低的成本，构建高性能、易扩展和高可用的云应用
+ 核心是用服务器、基础服务不再是用户关注的焦点
+ 为了防止被绑定到单一云厂商，在设计serverless应用时就应该考虑到兼容多个云厂的serverless产品，或者在云中自己构建serverless平台，如kuneless，这样就很容易在多个云厂商之间做到兼容和迁移了
+ 并不是任意的应用都适合迁移到serverless平台，要考虑如启动时间、是否是有状态的函数、请求执行时长等方面的诸多考量因素
