package client;

import core.AbstractMessage;
import java.io.*;
import java.net.Socket;

public class Network {
    private static final int PORT = 8189;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private static Network instance;

    public static Network getInstance() {
        if (instance == null) {
            instance = new Network();
        }
        return instance;
    }

    private Network() {
        try {
            socket = new Socket("localhost", PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (Exception exception) {
            System.err.println("Сервер на порте 8189 не доступен!");
        }
    }

    public void writeMessage(AbstractMessage message) throws IOException {
        out.writeObject(message);
        out.flush();
    }

    public AbstractMessage readMessage() throws IOException, ClassNotFoundException {
        return (AbstractMessage) in.readObject();
    }

    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }
}
