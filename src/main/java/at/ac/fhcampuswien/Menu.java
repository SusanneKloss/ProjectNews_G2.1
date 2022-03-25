package at.ac.fhcampuswien;

import at.ac.fhcampuswien.AppController;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class Menu {

    private AppController controller;
    private static String INVALID_INPUT_MESSAGE;
    private static String EXIT_MESSAGE;

    public javafx.scene.control.Button austriaButton, bitcoinButton, countButton, quitButton;
    public Text textField;


    public static void start(){

    }
    private void handleinput(String input){

    }
    private void getArticleCount(AppController ctrl){

    }
    private void getTopHeadlinesAustria(AppController ctrl){
        controller.getTopHeadlinesAustria();
    }
    private void getAllNewsBitcoin(AppController ctrl){

    }
    private static void printExitMessages(){

    }
    private static void printInvalidInputMessages(){

    }
    private static void printMenu(){

    }


    public void austriaButtonAction(ActionEvent actionEvent) {

        textField.setText(controller.getTopHeadlinesAustria().toString());
    }

    public void bitcoinButtonAction(ActionEvent actionEvent) {
    }

    public void countButtonAction(ActionEvent actionEvent) {
    }

    public void quitButtonAction(ActionEvent actionEvent) {
    }
}
