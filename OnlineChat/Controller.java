package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {
    StringBuilder chat = new StringBuilder();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labTopName;

    @FXML
    private TextArea texChat;

    @FXML
    private TextField texMessage;

    @FXML
    private Button butSend;

    @FXML
    void initialize() {
        butSend.setOnAction(event -> {
            sendMessage();
        });
    }

    @FXML
    public void handleEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            sendMessage();
        }
    }

    public void sendMessage() {
        String text = texMessage.getText();
        if(!text.isEmpty()) {
            chat = chat.append(text + "\n\n");
            texChat.setText(chat.toString());
            System.out.println("Cообщение: \"" + text + "\"");
            texMessage.setText("");
        }
    }
}