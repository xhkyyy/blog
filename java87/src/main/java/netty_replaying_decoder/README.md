# io.netty.handler.codec.ReplayingDecoder 原理

## ReplayingDecoder 使用方法(相比直接使用 ByteToMessageDecoder 简化不少)：

```java
public class IntegerHeaderFrameDecoder extends ReplayingDecoder<Void> {
   protected void decode(ChannelHandlerContext ctx, ByteBuf buf) throws Exception {
     out.add(buf.readBytes(buf.readInt()));
   }
 }
```

## 原理

### 1.ReplayingDecoderByteBuf

ReplayingDecoder 内部使用的 ByteBuf 是 ReplayingDecoderByteBuf， 它继承自 ByteBuf，并重写了相关方法：

```java
final class ReplayingDecoderByteBuf extends ByteBuf {
  ...
    @Override
    public int readInt() {
        checkReadableBytes(4);
        return buffer.readInt();
    }

    @Override
    public long readLong() {
        checkReadableBytes(8);
        return buffer.readLong();
    }

    @Override
    public char readChar() {
        checkReadableBytes(2);
        return buffer.readChar();
    }
  ...
```

当调用 buf.read*() 的时候，都会做相关 bytes 大小的检查，比如：

```java
public int readInt() {
        checkReadableBytes(4); //检查 4 个字节是否可读
        return buffer.readInt();
    }
```

checkReadableBytes(int readableBytes) 的实现是：

```java
private void checkReadableBytes(int readableBytes) {
        if (buffer.readableBytes() < readableBytes) {
            throw REPLAY; //将抛出异常
        }
    }
```

一目了然，当可读字节数小于 buf.read*() 大小时（比如 int 4 个字节），将抛出相关异常。

我们再来看看异常 REPLAY 的定义：

```java
final class ReplayingDecoderByteBuf extends ByteBuf {
    // final 类型的
    private static final Signal REPLAY = ReplayingDecoder.REPLAY;
    ...
```

```java
public final class Signal extends Error implements Constant<Signal> {
  ...
```

可以看出 Signal 继承自 java.lang.Error。

总结：

以 IntegerHeaderFrameDecoder 举例来说，如果 buf.readInt() 没有读到 4 个字节的数据，那么将抛出 Signal 异常。这个过程将随着网络数据的到达不断的重试，直到可读数据的大小满足 >= 4 个字节为止。

```java
public class IntegerHeaderFrameDecoder extends ReplayingDecoder<Void> {
   protected void decode(ChannelHandlerContext ctx, ByteBuf buf) throws Exception {
     out.add(buf.readBytes(buf.readInt()));
   }
 }
```

### 2.ReplayingDecoder 调用流程

以 IntegerHeaderFrameDecoder 为例：
`callDecode(...)` --->> `decodeRemovalReentryProtection(ctx, ReplayingDecoderByteBuf, out)`  --->> `decode(ctx, in, out)` --->> `buf.readInt()等待 4 字节数据到达。`