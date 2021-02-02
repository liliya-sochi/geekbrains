
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    public static Socket socket;
    public static DataInputStream is;
    public static DataOutputStream os;

    public static void main(String[] args) throws IOException {
        socket = new Socket("localhost", 8189);
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
            System.out.println("Сообщение от Сервера: \"" + messageFrom + "\"");
        }
    }
}
