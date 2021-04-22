package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Server extends ConfigServer {
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private User user;
    private Set<ClientHandler> clients;

    public Server() {
        start();
    }

    private void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[DEBUG]: Старт работы сервера!");
            clients = new HashSet<>();
            while (true) {
                System.out.println("[DEBUG]: Сервер ждёт новое подключение!");
                Socket socket = serverSocket.accept();
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());
                user = (User) in.readObject();
                System.out.println("[DEBUG]: " + new Date() + " - Пользователь " + user.getName() + " успешно добавился в чат!");
                ClientHandler client = new ClientHandler(socket, user);
                subscribe(client);
            }
        } catch (Exception e) {
            System.err.println("[ERROR]: Сервер не работает!");
        }
    }

    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }
}
