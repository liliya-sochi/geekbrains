package controller;

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
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import animation.Shake;
import database.DBHandler;
import network.User;

public class SingInController {
    User user;
    File messageHistory;

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
            else labWarning.setText("Поля логин и пароль не заполнены!");
        });

        butSingUp.setOnAction(event -> {
            openNewScene("/view/singUp.fxml");
        });
    }

    private void loginUser(String login, String password) {
        DBHandler dbHandler = new DBHandler();
        ResultSet result = dbHandler.getUser(login, password);
        int counter = 0;
        try {
            while (result.next()) {
                counter++;
                String userName = result.getString(1);
                String userLogin = result.getString(2);
                String userPassword = result.getString(3);
                user = new User(userName, userLogin, userPassword);
                System.out.println("[DEBUG]: Открывается чат для пользователя - " + login + "...");
                messageHistory = new File("src/history", login+"-MessageHistory.txt");
                if (!messageHistory.exists()) messageHistory.createNewFile();
                openNewSceneWithUser("/view/chat.fxml", user);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR]: Что-то пошло не так...");
        } catch (IOException e) {
            System.err.println("[ERROR]: Файл с историей сообщений для пользователя не создан!");
        }
        if (counter == 0) {
            Shake loginAnim = new Shake(texLogin);
            Shake passwordAnim = new Shake(pasPassword);
            loginAnim.playAnim();
            passwordAnim.playAnim();
            labWarning.setText("Введён неверный логин или пароль!");
        }
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
        ChatController controller = loader.getController();
        controller.getUser(user);
        controller.loadingChat();
        stage.show();
    }
}
