package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import network.common.JsonDecoder;
import network.common.JsonEncoder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    int port = ConfigServer.SERVER_PORT;

    public void start(){
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        try {
            Channel server = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) {
                            nioSocketChannel.pipeline().addLast(
                                new LengthFieldBasedFrameDecoder(1024 * 1024, 0, 4, 0, 4),
                                new LengthFieldPrepender(4),
                                new StringDecoder(),
                                new StringEncoder(),
                                new ByteArrayDecoder(),
                                new ByteArrayEncoder(),
                                new JsonDecoder(),
                                new JsonEncoder(),
                                new LoginHandler(),
                                new ServerHandler(threadPool));
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(
                            ChannelOption.WRITE_BUFFER_WATER_MARK,
                            new WriteBufferWaterMark(1024 * 1024 * 5, 1024 * 1024 * 10)
                    )
                    .bind(port).sync().channel();
            System.out.println("[DEBUG]: Сервер запущен!");
            server.closeFuture().sync();
        } catch (InterruptedException e) {
            System.err.println("[ERROR]: Соединение разорвано!");
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            threadPool.shutdown();
        }
    }
}
