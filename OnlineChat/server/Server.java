package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedDeque;

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
        } catch (Exception serverException) {
            System.err.println("Сервер не работает!");
        }
    }

    public void addClient(ClientHandler clientHandler) {
        clients.add(clientHandler);
        System.out.println("Клиент добавлен в чат!");
    }

    public void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        System.out.println("Клиент удалён из чата!");
    }

    public void broadCastMessage(String message) throws IOException {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void sendMessageTo(String fromM, String toM, String message) throws IOException {
        for (ClientHandler client : clients) {
            if(client.getNickName().equals(fromM) || client.getNickName().equals(toM)) {
                client.sendMessage(message);
            }
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
}
