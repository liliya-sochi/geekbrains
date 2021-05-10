package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    User user;
    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(5);

    public void start() throws InterruptedException {
        final NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(
                                    new StringEncoder(),
                                    new StringDecoder()
                            );
                        }
                    });
            System.out.println("[DEBUG]: Подключился новый клиент!");
            ChannelFuture channelFuture = bootstrap.connect(ConfigClient.CLIENT_HOST, ConfigClient.CLIENT_PORT).sync();
            channelFuture.channel().writeAndFlush(user.getLogin());
        //} catch (InterruptedException e ) {
            //e.printStackTrace();
        } finally {
            group.shutdownGracefully();
            THREAD_POOL.shutdown();
        }
    }
}
