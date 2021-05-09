package controller;

import animation.Shake;
import client.User;
import database.ConnectDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SingInController {
    User user;

    @FXML
    private AnchorPane main;

    @FXML
    private TextField texLogin;

    @FXML
    private PasswordField pasPassword;

    @FXML
    private Button butSingIn;

    @FXML
    private Button butSingUp;

    @FXML
    private Label labWarning;

    @FXML
    void initialize() {
        butSingIn.setOnAction(event -> {
            String login = texLogin.getText().trim();
            String password = pasPassword.getText().trim();
            if (!login.equals("") && !password.equals("")) loginUser(login, password);
            else {
                thisShake();
                labWarning.setText("Поля логин и пароль не заполнены!");
            }
        });

        butSingUp.setOnAction(event -> {
            openNewScene("/view/singUp.fxml");
        });
    }

    private void loginUser(String login, String password) {
        ConnectDB connectDB = new ConnectDB();
        ResultSet result = connectDB.getUser(login, password);
        int counter = 0;
        try {
            while (result.next()) {
                counter++;
                String userName = result.getString(1);
                String userLogin = result.getString(2);
                String userPassword = result.getString(3);
                user = new User(userName, userLogin, userPassword);
                System.out.println("[DEBUG]: Открывается чат для пользователя - " + login + "...");
                /** ПРОВЕРИТЬ, СУЩЕСТВУЕТ ЛИ ПАПКА ДЛЯ ПОЛЬЗОВАТЕЛЯ НА СЕРВЕРЕ, ЕСЛИ НЕТ - СОЗДАТЬ! */
                openNewSceneWithUser("/view/mainWin.fxml", user);
            }
        } catch (SQLException | InterruptedException e) {
            System.err.println("[ERROR]: Что-то пошло не так...");
        }
        if (counter == 0) {
            thisShake();
            labWarning.setText("Введён неверный логин или пароль!");
        }
    }

    private void thisShake() {
        Shake loginAnim = new Shake(texLogin);
        Shake passwordAnim = new Shake(pasPassword);
        loginAnim.playAnim();
        passwordAnim.playAnim();
    }

    private void openNewScene(String window) {
        main.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            System.err.println("[ERROR]: Что-то пошло не так...");
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    private void openNewSceneWithUser(String window, User user) {
        main.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(window));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            System.err.println("[ERROR]: Что-то пошло не так...");
            e.printStackTrace();
        }
        //MainController controller = loader.getController();
        //controller.getUser(user);
        //controller.loadingChat();
        stage.show();
    }
}
