package server;

import core.*;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private Server server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean running;

    private String nickName;
    private static int cnt = 0;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        running = true;
        cnt++;
        nickName = "Пользователь" + cnt;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Клиент подключается!");
            server.broadCastMessage(UserListMessage.of(server.getUserNickNames()));
            while (running) {
                /** 7-ое Домашнее Задание */
//                String message = in.readUTF();
//                if (message.charAt(0) == '/') {
//                    String[] split = message.split(" ",3);
//                    if (split[0].equals("/выход")) out.writeUTF(message);
//                    if (split[0].equals("/с")) {
//                        server.sendMessageTo(nickName, split[1], nickName + ": " + split[2]);
//                    }
//                }
//                else server.broadCastMessage(nickName + ": " + message);
//                System.out.println("Сообщение от клиента: " + message);
                AbstractMessage message = (AbstractMessage) in.readObject();
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    if (textMessage.getTo() != null) {
                        server.sendMessageTo(textMessage);
                    } else {
                        server.broadCastMessage(textMessage);
                    }
                }
                if (message instanceof NickRequest) {
                    out.writeObject(new NickResponse(nickName));
                    out.flush();
                }
                if (message instanceof QuitRequest) {
                    out.writeObject(message);
                    out.flush();
                }
                System.out.println("Сообщение от клиента: " + message);
            }
        } catch (Exception exception) {
            System.err.println("Подключение разорвано!");
            server.removeClient(this);
            try {
                server.broadCastMessage(UserListMessage.of(server.getUserNickNames()));
            } catch (IOException ignored) {}
        }
    }

    public void sendMessage(AbstractMessage message) throws IOException {
        out.writeObject(message);
        out.flush();
    }
}
