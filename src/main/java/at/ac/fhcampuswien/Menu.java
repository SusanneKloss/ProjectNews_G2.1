package at.ac.fhcampuswien;

import at.ac.fhcampuswien.AppController;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Menu {

    private AppController controller = new AppController();
    private ArrayList<Article> outputList;
    private StringBuilder textOutput;
    private static String INVALID_INPUT_MESSAGE;
    private static String EXIT_MESSAGE;

    private SFX soundInMenu = new SFX();

    public javafx.scene.control.Button austriaButton, bitcoinButton, countButton, quitButton;
    public Text textField, austriaLetters, bitcoinLetters, countLetters, quitLetters;

    public static void start() {
    }

    private void handleinput(String input){

    }
    public void getArticleCount(ActionEvent actionEvent){
        textField.setText("Number of articles: " + String.valueOf(controller.getArticleCount()));
        soundInMenu.playClick();
    }

    public void getTopHeadlinesAustria(ActionEvent actionEvent) throws IOException {
        textOutput = new StringBuilder();
        outputList = controller.getTopHeadlinesAustria();

        for (Article art : outputList) {
            textOutput.append(art.toString());
        }
        textField.setText(textOutput.toString());
        soundInMenu.playClick();
    }

    public void getAllNewsBitcoin(ActionEvent actionEvent){
        textOutput = new StringBuilder();
        outputList = controller.getAllNewsBitcoin();

        for (Article art : outputList) {
            textOutput.append(art.toString());
        }
        textField.setText(textOutput.toString());
        soundInMenu.playClick();
    }

    public void printExitMessages(ActionEvent actionEvent){
        textField.setText("Bye bye!");
        soundInMenu.playClick();
        PauseTransition pauseEnd = new PauseTransition(Duration.seconds(2));
        pauseEnd.setOnFinished(event1 -> {
            System.exit(0);
        });
        pauseEnd.play();
    }
    private static void printInvalidInputMessages(){

    }
    private static void printMenu(){

    }

    public void austriaEntered(MouseEvent mouseEvent) {
        austriaLetters.setFill(Color.web("#080808"));
        soundInMenu.playHover();
    }

    public void austriaExited(MouseEvent mouseEvent) {
        austriaLetters.setFill(Color.web("#858585"));
    }

    public void bitcoinEntered(MouseEvent mouseEvent) {
        bitcoinLetters.setFill(Color.web("#080808"));
        soundInMenu.playHover();
    }

    public void bitcoinExited(MouseEvent mouseEvent) {
        bitcoinLetters.setFill(Color.web("#858585"));
    }

    public void countEntered(MouseEvent mouseEvent) {
        countLetters.setFill(Color.web("#080808"));
        soundInMenu.playHover();
    }

    public void countExited(MouseEvent mouseEvent) {
        countLetters.setFill(Color.web("#858585"));
    }

    public void quitEntered(MouseEvent mouseEvent) {
        quitLetters.setFill(Color.web("#080808"));
        soundInMenu.playHover();
    }

    public void quitExited(MouseEvent mouseEvent) {
        quitLetters.setFill(Color.web("#858585"));
    }
}
