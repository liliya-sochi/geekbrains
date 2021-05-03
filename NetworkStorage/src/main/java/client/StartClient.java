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

public class StartClient {
    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException {
        try {
            new StartClient().start();
        } finally {
            THREAD_POOL.shutdown();
        }
    }

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
            System.out.println("DEBUG: Клиент подключился!");
            ChannelFuture channelFuture = bootstrap.connect("localhost", 9000).sync();

            while (true) {
                final String message = String.format("[%s] %s", LocalDateTime.now(), Thread.currentThread().getName());
                System.out.println("MESSAGE: " + message);
                channelFuture.channel().writeAndFlush(message + "\n");
                channelFuture.channel().writeAndFlush(message + System.lineSeparator()).sync();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e ) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
