package main;

import controller.MainController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.InputStream;

public class StartApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("views/singIn.fxml")) {
            Parent root = fxmlLoader.load(inputStream);
            Scene scene = new Scene(root, 1000, 700);
            primaryStage.setTitle("Сетевое Хранилище");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        try (InputStream iconStream = getClass().getClassLoader().getResourceAsStream("icons/ico-favicon.png")) {
            Image image = new Image(iconStream);
            primaryStage.getIcons().add(image);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
