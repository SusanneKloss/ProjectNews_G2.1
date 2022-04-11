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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static javafx.application.Application.launch;

public class App extends Application {

    public static void main(String[] args) throws IOException {
        //launch(args);

        AppController controller = new AppController();
        System.out.println(controller.getTopHeadlinesAustria().toString());

    }

   @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(App.class.getResource("/menu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Project News");
        stage.setScene(scene);
        stage.show();

    }
}
