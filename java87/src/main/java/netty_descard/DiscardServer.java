package netty_descard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * DiscardServer
 */
public class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workeGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workeGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

            // bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync();

            System.out.println("A");

            // wait until the server socket is closed.
            // in this example, this does not happen, but you can do that to gracefully shut
            // down your server
            Channel channel = f.channel();

            ChannelFuture cf = channel.closeFuture();

            cf.sync();

            System.out.println("B");
        } finally {
            workeGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        int port = 9999;

        try {
            new DiscardServer(port).run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("B");
    }

}