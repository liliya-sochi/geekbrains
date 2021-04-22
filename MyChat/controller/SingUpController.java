package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import animation.Shake;
import database.DBHandler;
import javafx.stage.StageStyle;
import network.User;

public class SingUpController {
    User user;
    File messageHistory;

    @FXML
    private AnchorPane main;

    @FXML
    private TextField texName;

    @FXML
    private Button butSingUp;

    @FXML
    private TextField texLogin;

    @FXML
    private PasswordField pasPassword;

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
            }catch (IOException e) {
                labWarning.setText("Ошибка в работе программы, зайдите попозже!");
                System.err.println("[ERROR]: Файл с историей сообщений для пользователя не создан!");
            }
        });
    }

    private void singUpNewUsers() throws SQLIntegrityConstraintViolationException, SQLException, IOException {
        DBHandler dbHandler = new DBHandler();
        String name = texName.getText();
        String login = texLogin.getText();
        String password = pasPassword.getText();
        dbHandler.singUpUser(name, login, password);
        System.out.println("[DEBUG]: Зарегистрирован новый пользователь с логином - " + login + "!");
        messageHistory = new File("src/history", login+"-MessageHistory.txt");
        messageHistory.createNewFile();
        System.out.println("[DEBUG]: Создан файл с историей сообщений для пользователя - " + login + "!");
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
        ChatController controller = loader.getController();
        controller.getUser(user);
        controller.loadingChat();
        stage.show();
    }

}