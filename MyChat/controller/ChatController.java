package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javafx.scene.layout.AnchorPane;
import network.User;

public class ChatController {
    User user;
    File messageHistory;

    @FXML
    private AnchorPane main;

    @FXML
    private ListView<String> lisUsers;

    @FXML
    private ListView<String> lisChat;

    @FXML
    private TextField texMessage;

    @FXML
    private Button butEnter;

    @FXML
    public void initialize() {
        butEnter.setOnAction(event -> {
            lisChat.getItems().add(texMessage.getText());
            try(FileWriter writer = new FileWriter(messageHistory, true)) {
                writer.write("\n" + texMessage.getText());
                writer.flush();
            } catch (IOException e) {
                System.err.println("[ERROR]: Что-то пошло не так...");
            }
            texMessage.setText("");
        });
    }

    public void loadingChat() {
        messageHistory = new File("src/history", user.getLogin() + "-MessageHistory.txt");
        try {
            List<String> lines = Files.readAllLines(Paths.get(String.valueOf(messageHistory)), StandardCharsets.UTF_8);
            for (String line : lines) lisChat.getItems().add(line);
        } catch (IOException e) {
            System.err.println("[ERROR]: Файл с историей сообщений для пользователя " + user.getLogin() + " не найден!");
        }
    }

    public void getUser(User user) {
        this.user = user;
    }
}