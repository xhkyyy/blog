package netty_time_server_pojo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UnixTime m = (UnixTime) msg;
        System.out.println(DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date(m.value() * 1000L)));
        ctx.close();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
