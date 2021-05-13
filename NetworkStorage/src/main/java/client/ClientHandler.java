package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import network.message.FileMessage;
import network.message.Message;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {

    }
}
