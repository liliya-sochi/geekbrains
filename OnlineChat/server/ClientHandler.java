package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.regex.Pattern;

public class ClientHandler implements Runnable {
    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
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

    @Override
    public void run() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            System.out.println("Клиент подключается!");
            while (running) {
                String message = in.readUTF();
                if (message.equals("/выход")) out.writeUTF(message);
                //if (message.equals("/c " + nickName + "\\d")) server.sendMessageTo(nickName + "\\d", message);
                else server.broadCastMessage(nickName + ": " + message);
                System.out.println("Сообщение от клиента: " + message);
            }
        } catch (Exception handlerException) {
            System.err.println("Подключение разорвано!");
            server.removeClient(this);
        }
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }
}
