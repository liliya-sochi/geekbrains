package controller;

import animation.Shake;
import client.User;
import database.ConnectDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class SingUpController {
    User user;

    @FXML
    private AnchorPane main;

    @FXML
    private TextField texName;

    @FXML
    private TextField texLogin;

    @FXML
    private PasswordField pasPassword;

    @FXML
    private Button butSingUp;

    @FXML
    private Label labWarning;

    @FXML
    void initialize() {
        butSingUp.setOnAction(event -> {
            try {
                singUpNewUsers();
            } catch (SQLIntegrityConstraintViolationException e) {
                Shake loginAnim = new Shake(texLogin);
                loginAnim.playAnim();
                labWarning.setText("Пользователь с данным логином уже существует!");
            } catch (SQLException e) {
                labWarning.setText("Ошибка в работе программы, зайдите попозже!");
                System.err.println("[ERROR]: Ошибка в работе Базы Данных!");
            } catch (InterruptedException e) {
                labWarning.setText("Ошибка в работе программы, зайдите попозже!");
                System.err.println("[ERROR]: Клиен не подключился к серверу!");
            } catch (IOException e) {
                labWarning.setText("Ошибка в работе программы, зайдите попозже!");
                System.err.println("[ERROR]: Что-то пошло не так!");
            }
        });
    }

    private void singUpNewUsers() throws SQLIntegrityConstraintViolationException, SQLException, IOException, InterruptedException {
        ConnectDB connectDB = new ConnectDB();
        String name = texName.getText();
        String login = texLogin.getText();
        String password = pasPassword.getText();
        connectDB.singUpUser(name, login, password);
        System.out.println("[DEBUG]: Зарегистрирован новый пользователь с логином - " + login + "!");
        /** СОЗДАТЬ ПАПКУ ДЛЯ ПОЛЬЗОВАТЕЛЯ НА СЕРВЕРЕ! */
        user = new User(name, login, password);
        System.out.println("[DEBUG]: Открывается чат для пользователя - " + login + "...");
        openNewSceneWithUser("/view/chat.fxml", user);
    }

    public void openNewSceneWithUser(String window, User user) {
        main.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(window));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException ioException) {
            System.err.println("[ERROR]: Что-то пошло не так...");
        }
        ///MainController controller = loader.getController();
        //controller.getUser(user);
        //controller.loadingChat();
        stage.show();
    }

}
