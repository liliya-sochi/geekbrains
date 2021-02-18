package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChatController {

    @FXML
    private ListView<?> lisUsers;

    @FXML
    private ListView<?> lisChat;

    @FXML
    private TextField texMessage;

    @FXML
    private Button butEnter;

}