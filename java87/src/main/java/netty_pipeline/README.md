# [netty pipeline 执行顺序](https://netty.io/4.0/api/io/netty/channel/ChannelPipeline.html)

> Each channel has its own pipeline and it is created automatically when a new channel is created. 

## 例子：

```java
ChannelPipeline p = ...
p.addLast("1", new InboundHandlerA());
p.addLast("2", new InboundHandlerB());
p.addLast("3", new OutboundHandlerA());
p.addLast("4", new OutboundHandlerB());
p.addLast("5", new InboundOutboundHandlerX());
```


## inbound

+ 如果事件是 inbound 事件，则按照 addLast `正序`执行，即：1, 2, 5
+ 由于 3 和 4 没有实现  Channel`Inbound`Handler 所以会直接跳过

## outbound

+ 如果事件是 outbound 事件，则按照 addLast `反序`执行，即：5, 4, 3
+ 由于 1 和 2 没有实现  Channel`Outbound`Handler, 所以会直接跳过