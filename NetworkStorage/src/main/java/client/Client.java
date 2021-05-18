package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import network.common.JsonDecoder;
import network.common.JsonEncoder;

public class Client {
    private String host = ConfigClient.CLIENT_HOST;
    private int port = ConfigClient.CLIENT_PORT;

    public void start(String login) {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        try {
            Channel channel = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
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
                                    new ClientHandler());
                            }
                    })
                    .connect(host, port).sync().channel();
            channel.writeAndFlush("liliya");
            //while (channel.isActive()) {}
        } catch (InterruptedException e) {
            System.err.println("[ERROR]: Соединение разорвано!");
        } finally {
            group.shutdownGracefully();
        }
    }
}
