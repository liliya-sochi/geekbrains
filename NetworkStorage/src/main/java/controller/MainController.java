package controller;

import client.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainController {
    User user;

    @FXML
    private AnchorPane main;

    @FXML
    private TextField texConsole;

    @FXML
    private Button butConsole;

    @FXML
    private Label labStatus;

    @FXML
    private ImageView icoExit;

    @FXML
    private ImageView icoAddFolder;

    @FXML
    private ImageView icoDelFolder;

    @FXML
    private ImageView icoAddFile;

    @FXML
    private ImageView icoDelFile;

    @FXML
    public GridPane gpBox;

    public void user(User user) {
        this.user = user;
    }

    @FXML
    public void loading() {
        try {
            final String folderUser = "src/main/files/client/" + user.getLogin();
            Path path = Paths.get(folderUser);
            if (!Files.exists(path)) Files.createDirectory(Paths.get(folderUser));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gpBox.setGridLinesVisible(true);
        final int numCols = 15;
        final int numRows = 5;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            gpBox.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            gpBox.getRowConstraints().add(rowConst);
        }

        File[] myFiles = new File("src/main/files/client/" + user.getLogin()).listFiles();
        int index_i = 0;
        int index_j = 0;
        for (File file : myFiles) {
            if (file.isFile()) gpBox.add(new VBox(new ImageView("/icons/ico-x50-filered.png"), new Label(file.getName())), index_i, index_j);
            if (file.isDirectory()) gpBox.add(new VBox(new ImageView("/icons/ico-x50-folder.png"), new Label(file.getName())), index_i, index_j);
            index_i++;
            if (index_i == 15) {
                index_i = 0 ;
                index_j++;
            }
        }

        labStatus.setText("Добро пожаловать, " + user.getName() + "!");
    }

    @FXML
    public void initialize() {
        icoExit.setOnMouseClicked(event -> {
            user.userExit();
            openNewScene("/views/singIn.fxml");
        });
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
}