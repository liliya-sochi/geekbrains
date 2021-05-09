package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

public class StartApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        // Старт Приложения:
        FXMLLoader fxmlLoader = new FXMLLoader();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("view/singIn.fxml")) {
            Parent root = fxmlLoader.load(inputStream);
            Scene scene = new Scene(root, 1000, 700);
            primaryStage.setTitle("Сетевое Хранилище");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        try (InputStream iconStream = getClass().getClassLoader().getResourceAsStream("icon/ico-favicon.png")) {
            Image image = new Image(iconStream);
            primaryStage.getIcons().add(image);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
