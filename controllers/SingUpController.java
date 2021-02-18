package controllers;

import animations.Shake;
import database.DBHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.User;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class SingUpController {

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
                labWarning.setText("Ошибка в работе Базы Данных!");
            }
        });
    }

    private void singUpNewUsers() throws SQLIntegrityConstraintViolationException, SQLException {
        DBHandler dbHandler = new DBHandler();
        String name = texName.getText();
        String login = texLogin.getText();
        String password = pasPassword.getText();
        User user = new User(name, login, password);
        dbHandler.singUpUser(user);
        openNewScene("/view/chat.fxml");
    }

    public void openNewScene(String window) {
        butSingUp.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

}