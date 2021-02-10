package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ChatController implements Initializable {
    private Network network;

    @FXML
    public TextField texMessage;

    @FXML
    public ListView texChat;

    @FXML
    public void sendMessage(ActionEvent actionEvent) throws IOException {
        network.writeMessage(texMessage.getText());
        texMessage.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        network = Network.getInstance();
        new Thread(() -> {
            try {
                while (true) {
                    String message = network.readMessage();
                    if (message.equals("/выход")) {
                        network.close();
                        break;
                    }
                    Platform.runLater(() -> texChat.getItems().add(message));
                }
            } catch (IOException ioException) {
                System.err.println("Сервер не работает!");
                Platform.runLater(() -> texChat.getItems().add("Сервер не работает!"));
            }
        }).start();
    }
}
