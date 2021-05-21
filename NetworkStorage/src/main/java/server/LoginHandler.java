package server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginHandler extends SimpleChannelInboundHandler<String> {
    private String login;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[DEBUG]: Установленно новое соединение!");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        login = s;
        System.out.println("[DEBUG]: Пользователь " + login + " подключился!");
        try {
            final String folderUser = "src/main/files/server/" + login;
            Path path = Paths.get(folderUser);
            if (!Files.exists(path)) Files.createDirectory(Paths.get(folderUser));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("[DEBUG]: Пользователь " + login + " отключился!");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
