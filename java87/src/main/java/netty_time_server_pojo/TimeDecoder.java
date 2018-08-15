package netty_time_server_pojo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Decoder corresponds to Client.
 */
public class TimeDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("...TimeDecoder...");
        if (in.readableBytes() < 4) {
        } else {
            out.add(new UnixTime(in.readUnsignedInt()));
        }
    }
}
