package server;

import core.AbstractMessage;
import core.TextMessage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

public class Server {
    private  static final int DEFAULT_PORT = 8189;
    private ConcurrentLinkedDeque<ClientHandler> clients;

    public Server(int port) {
        clients = new ConcurrentLinkedDeque<>();
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Сервер работает на порте " + port);
            while (true) {
                Socket socket = server.accept();
                System.out.println("Клиент подключился!");
                ClientHandler handler = new ClientHandler(socket, this);
                addClient(handler);
                new Thread(handler).start();
            }
        } catch (Exception exception) {
            System.err.println("Сервер не работает!");
        }
    }

    public static void main(String[] args) {
        int port = -1;
        if (args != null && args.length == 1) {
            port = Integer.parseInt(args[0]);
        }
        if (port == -1) {
            port = DEFAULT_PORT;
        }
        new Server(port);
    }

    public List<String> getUserNickNames() {
        return clients.stream().map(ClientHandler::getNickName).collect(Collectors.toList());
    }

    public void addClient(ClientHandler clientHandler) {
        clients.add(clientHandler);
        System.out.println("Клиент добавлен в чат!");
    }

    public void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        System.out.println("Клиент удалён из чата!");
    }

    public void broadCastMessage(AbstractMessage message) throws IOException {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }
/** 7-ое Домашнее Задание */
//    public void sendMessageTo(String fromM, String toM, String message) throws IOException {
//        for (ClientHandler client : clients) {
//            if(client.getNickName().equals(fromM) || client.getNickName().equals(toM)) {
//                client.sendMessage(message);
//            }
//        }
//    }

    public void sendMessageTo(TextMessage message) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getNickName().equals(message.getTo()) || client.getNickName().equals(message.getFrom())) {
                client.sendMessage(message);
            }
        }
    }
}
