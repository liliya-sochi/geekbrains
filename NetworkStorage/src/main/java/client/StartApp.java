package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.net.URL;

public class StartApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        // Парамнтры окна
        primaryStage.setTitle("Сетевое Хранилище");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(700);

        /** Не получается добавить иконку
        // Иконка окна
        InputStream iconStream = getClass().getResourceAsStream("icon/ico.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);
         */

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("view/singIn.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        // Запуск окна
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch();
    }
}
