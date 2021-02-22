package network;

import java.io.*;
import java.net.Socket;

public class User extends ConfigClient implements Serializable {
    private String name;
    private String login;
    private String password;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        connect();
    }

    public void connect() {
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(this);
        } catch (IOException e) {
            System.err.println("[ERROR]: Сервер не доступен!");
        }
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
