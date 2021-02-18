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
import java.sql.ResultSet;
import java.sql.SQLException;

public class SingInController {

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
            String loginText = texLogin.getText().trim();
            String passwordText = pasPassword.getText().trim();
            if (!loginText.equals("") && !passwordText.equals("")) loginUser(loginText, passwordText);
            else labWarning.setText("Поля логин и пароль не заполнены!");
        });

        butSingUp.setOnAction(event -> {
            openNewScene("/view/singUp.fxml");
        });
    }

    private void loginUser(String loginText, String passwordText) {
        DBHandler dbHandler = new DBHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(passwordText);
        ResultSet result = dbHandler.getUser(user);
        int counter = 0;
        try {
            while (result.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (counter >=1) {
            openNewScene("/view/chat.fxml");
        } else {
            Shake loginAnim = new Shake(texLogin);
            Shake passwordAnim = new Shake(pasPassword);
            loginAnim.playAnim();
            passwordAnim.playAnim();
            labWarning.setText("Введён неверный логин или пароль!");
        }
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
