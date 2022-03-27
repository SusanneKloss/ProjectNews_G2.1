package at.ac.fhcampuswien;

import at.ac.fhcampuswien.AppController;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class Menu {

    private AppController controller = new AppController();
    private ArrayList<Article> outputList;
    private StringBuilder textOutput;
    private static String INVALID_INPUT_MESSAGE;
    private static String EXIT_MESSAGE;

    public javafx.scene.control.Button austriaButton, bitcoinButton, countButton, quitButton;
    public Text textField;


    public static void start(){
    }

    private void handleinput(String input){

    }
    public void getArticleCount(ActionEvent actionEvent){
        textField.setText("Number of articles: " + String.valueOf(controller.getArticleCount()));
    }
    public void getTopHeadlinesAustria(ActionEvent actionEvent){
        textOutput = new StringBuilder();
        outputList = controller.getTopHeadlinesAustria();

        for (Article art : outputList) {
            textOutput.append(art.toString());
        }
        textField.setText(textOutput.toString());
    }
    public void getAllNewsBitcoin(ActionEvent actionEvent){
        textOutput = new StringBuilder();
        outputList = controller.getAllNewsBitcoin();

        for (Article art : outputList) {
            textOutput.append(art.toString());
        }
        textField.setText(textOutput.toString());
    }
    public void printExitMessages(ActionEvent actionEvent){
        System.exit(0);
    }
    private static void printInvalidInputMessages(){

    }
    private static void printMenu(){

    }
}
