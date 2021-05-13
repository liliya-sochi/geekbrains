package server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import network.message.FileMessage;
import network.message.Message;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;

public class ServerHandler extends SimpleChannelInboundHandler<Message> {
    private String login;
    private final ExecutorService threadPool;
    private final static int BASIC_BUFFER_SIZE = 1024 * 512;

    public ServerHandler(ExecutorService threadPool) {
        this.threadPool = threadPool;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /** ПРОВЕРКА, СУЩЕСТВУЕТ ЛИ ПАПКА ДЛЯ ПОЛЬЗОВАТЕЛЯ НА СЕРВЕРЕ, ЕСЛИ НЕТ - СОЗДАТЬ! */
        try {
            final String folderUser = "src/main/java/files/server/" + login;
            Path path = Paths.get(folderUser);
            if (!Files.exists(path)) Files.createDirectory(Paths.get(folderUser));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        threadPool.submit(() -> {
//            final String filePath = "src/main/java/files/server";
//            final String fileName = "file.txt";
//            ctx.writeAndFlush(filePath);
//            ctx.writeAndFlush(fileName);
//            final File file = new File(filePath, fileName);
//            try (RandomAccessFile accessFile = new RandomAccessFile(file, "r")) {
//                long length = file.length();
//                long pointer = accessFile.getFilePointer();
//                long available = length - pointer;
//                while (available > 0) {
//                    byte[] buffer;
//                    if(available > BASIC_BUFFER_SIZE) {
//                        buffer = new byte[BASIC_BUFFER_SIZE];
//                    } else {
//                        buffer = new byte[(int) available];
//                    }
//                    accessFile.read(buffer);
//                    FileMessage fileMessage = new FileMessage();
//                    fileMessage.setFileName(fileName);
//                    fileMessage.setStartPosition(pointer);
//                    fileMessage.setFileData(buffer);
//                    Channel channel = ctx.channel();
//                    while (true) {
//                        if (channel.isActive()) {
//                            if (channel.isWritable()) {
//                                ctx.writeAndFlush(fileMessage);
//                                break;
//                            } else {
//                                Thread.sleep(10);
//                            }
//                        } else {
//                            return;
//                        }
//                    }
//                    pointer = accessFile.getFilePointer();
//                    available = length - pointer;
//                }
//            } catch (IOException | InterruptedException e) {
//                System.err.println("[ERROR]: Файл не найден!");
//            }
//        });
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        login = "" + message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
