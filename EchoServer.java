
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    public static Socket socket;
    public static DataInputStream is;
    public static DataOutputStream os;
    private  boolean running;

    public EchoServer() {
        running = true;
        try(ServerSocket server = new ServerSocket(8189)) {
            while(running) {
                socket = server.accept();
                System.out.println("Клиент подключился!");
                try {
                    handle(socket);
                } catch(IOException ioException) {
                    System.out.println("Клиент отключился!");
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void handle(Socket socket) throws IOException {
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    methodIS();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    methodOS();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    public static void methodOS() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String messageTo = scanner.nextLine();
            os.writeUTF(messageTo);
            os.flush();
        }
    }

    public static void methodIS() throws IOException {
        while(true) {
            String messageFrom = is.readUTF();
            System.out.println("Сообщение от Клиента: \"" + messageFrom + "\"");
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public static void main(String[] args) {
        new EchoServer();
    }
}
