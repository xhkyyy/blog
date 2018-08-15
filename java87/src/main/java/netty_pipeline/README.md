# [pipeline](https://netty.io/4.0/api/io/netty/channel/ChannelPipeline.html)

> Each channel has its own pipeline and it is created automatically when a new channel is created. 

## inbound

+ 按照 add 顺序执行

## outbound


```java
ChannelPipeline p = ...
p.addLast("1", new InboundHandlerA());
p.addLast("2", new InboundHandlerB());
p.addLast("3", new OutboundHandlerA());
p.addLast("4", new OutboundHandlerB());
p.addLast("5", new InboundOutboundHandlerX());
```