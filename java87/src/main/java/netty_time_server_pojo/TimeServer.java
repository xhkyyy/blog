package netty_time_server_pojo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import netty_time_server.Constant;

import java.net.InetSocketAddress;

public class TimeServer {
    private int port;

    public TimeServer(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workeGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workeGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new TimeEncoder());
                            pipeline.addLast(new TimeServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 1000)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(new InetSocketAddress(Constant.HOST, Constant.PORT)).sync();
            f.channel().closeFuture().sync();
        } finally {
            workeGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new TimeServer(Constant.PORT).run();
    }
}
