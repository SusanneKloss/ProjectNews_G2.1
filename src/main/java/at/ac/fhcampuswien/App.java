package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class App extends Application {

    public static void main(String[] args) {

        Menu menu = new Menu();
        Menu.start();

        launch(args);

        AppController ac = new AppController();



    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Project News");
        stage.setScene(scene);
        stage.show();

        /**
        stage.setTitle("Hello World!");
        javafx.scene.control.Button btn = new Button();
        btn.setText("Hello JavaFX!");
        btn.setOnAction( (event) -> Platform.exit() );
        Pane root = new StackPane();
        root.getChildren().add(btn);
        stage.setScene(new Scene(root, 300, 150));
        stage.show();   */
    }
}
