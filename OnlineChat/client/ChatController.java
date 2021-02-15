package client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import core.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class ChatController implements Initializable {
    private Network network;
    private String nick;

    @FXML
    public TextField texMessage;

    @FXML
    public ListView<String> lisChat;

    @FXML
    public ListView<String> lisUsers;

    @FXML
    public void sendMessage(ActionEvent actionEvent) throws IOException {
        String to = lisUsers.getSelectionModel().getSelectedItem();
        if (to != null) {
            network.writeMessage(TextMessage.of(nick, to, texMessage.getText()));
        } else {
            network.writeMessage(TextMessage.of(nick, texMessage.getText()));
        }
        lisUsers.getSelectionModel().clearSelection();
        texMessage.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        network = Network.getInstance();
        new Thread(() -> {
            try {
                network.writeMessage(new NickRequest());
                while (true) {
                    AbstractMessage message = network.readMessage();
                    if (message instanceof NickResponse) {
                        nick = ((NickResponse) message).getNick();
                    }
                    if (message instanceof UserListMessage) {
                        Platform.runLater(() -> {
                            lisUsers.getItems().clear();
                            lisUsers.getItems().addAll(((UserListMessage) message).getNames());
                        });
                    }
                    if (message instanceof QuitRequest) {
                        network.close();
                        break;
                    }
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        String out = textMessage.getSendAt() + " " + textMessage.getFrom() + ": " + textMessage.getMessage();
                        Platform.runLater(() -> lisChat.getItems().add(out));
                    }
                }
            } catch (IOException ioException) {
                System.err.println("Сервер не работает!");
                Platform.runLater(() -> lisChat.getItems().add("Сервер не работает!"));
            } catch (ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        }).start();
    }

    public void quit(ActionEvent actionEvent) throws IOException {
        network.writeMessage(new QuitRequest());
    }
}
