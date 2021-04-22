package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private User user;

    public ClientHandler(Socket socket, User user) {
        this.user = user;
        try {
            this.socket = socket;
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.err.println("[ERROR]: Что-то пошло не так...");
        }
    }
}
