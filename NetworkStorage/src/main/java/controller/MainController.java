package controller;

import client.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MainController {
    User user;

    @FXML
    private AnchorPane main;

    @FXML
    private TextField texConsole;

    @FXML
    private Button butConsole;

    @FXML
    private Label labStatus;

    @FXML
    private ImageView icoExit;

    @FXML
    private ImageView icoAddFolder;

    @FXML
    private ImageView icoDelFolder;

    @FXML
    private ImageView icoAddFile;

    @FXML
    private ImageView icoDelFile;

    public void user(User user) {
        this.user = user;
    }

    public void loading() {
        /** ПРОВЕРИТЬ, СУЩЕСТВУЕТ ЛИ ПАПКА ДЛЯ ПОЛЬЗОВАТЕЛЯ НА СЕРВЕРЕ, ЕСЛИ НЕТ - СОЗДАТЬ! */
        /** ЗАГРУЗИТЬ ИЕРАРХИЮ ПАПОК И ФАЙЛОВ В ПРИЛОЖЕНИЕ! */
        labStatus.setText("Добро пожаловать, " + user.getName() + "!");
    }
}