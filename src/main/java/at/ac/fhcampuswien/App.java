package at.ac.fhcampuswien;

import at.ac.fhcampuswien.controllers.NewsApiException;
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

    public static void main(String[] args) {

        /*try
        {
            // throw an object of user defined exception
            throw new NewsApiException();
        }
        catch (NewsApiException ex)
        {
            System.out.println("Caught the exception");
            System.out.println(ex.getMessage());
        }
        System.out.println("rest of the code...");*/

    launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(App.class.getResource("/menu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Project News");
        stage.setScene(scene);
        stage.show();
    }
}
