package netty_descard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //channelReadProcessDiscard(((ByteBuf) msg));
        channelReadProcessLookingInto(ctx, ((ByteBuf) msg));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void channelReadProcessDiscard(ByteBuf msg) {
        msg.release();
    }

    private void channelReadProcessLookingInto(ChannelHandlerContext ctx, ByteBuf msg) {
        try {
            System.out.println(msg.toString(CharsetUtil.UTF_8));


            // the two methods below requests to close the channel.
            // 1. ctx.close();
            // 2. ctx.channel().close();
        } finally {
            msg.release();
        }
    }
}
