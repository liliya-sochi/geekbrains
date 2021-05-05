package client;

import controller.SingInController;
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

        //Иконка окна
        //InputStream iconStream = getClass().getResourceAsStream("icon/ico.png");
        //Image image = new Image(iconStream);
        //primar yStage.getIcons().add(image);

        //Parent root = FXMLLoader.load(getClass().getResource("/view/singIn.fxml"));
        //primaryStage.setTitle("Сетевое Хранилище");
        //primaryStage.setScene(new Scene(root, 1000, 700));

        FXMLLoader fxmlLoader = new FXMLLoader();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("view/singIn.fxml")) {
            Parent root = fxmlLoader.load(inputStream); /** <- НЕ МОГУ ПОНЯТЬ, В ЧЁМ ТУТ ПРОБЛЕМА? */
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            SingInController singInController = fxmlLoader.getController();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
