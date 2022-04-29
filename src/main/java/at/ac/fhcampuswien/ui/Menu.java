package at.ac.fhcampuswien.ui;

import at.ac.fhcampuswien.controllers.AppController;
import at.ac.fhcampuswien.models.Article;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class Menu {

    public TableColumn<Article, String> authorColumn = new TableColumn<>("");
    public TableColumn<Article, String> titleColumn = new TableColumn<>("");
    public TableView table = new TableView();
    public TableView focusTable = new TableView();
    public TableColumn<Article, String> articleFocusColumn;
    public Button focusCloseButton;
    public ScrollPane focusPane;
    public TextArea focusText;
    public ImageView infoPane;
    public Text infoCount;
    public AnchorPane undoClicks;
    private AppController controller = new AppController();
    private ArrayList<Article> outputList = new ArrayList<>();
    private static String INVALID_INPUT_MESSAGE;
    private static String EXIT_MESSAGE;

    private SFX soundInMenu = new SFX();

    public javafx.scene.control.Button austriaButton, bitcoinButton, countButton, quitButton;
    public Text textField, austriaLetters, bitcoinLetters, countLetters, quitLetters;

    public static void start() {
    }
    private void handleinput(String input){
    }

    //assign columns to table
    private void setupTable(){
        table.getItems().clear();
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    }

    //GUI functions
    public void getArticleCount(ActionEvent actionEvent){
        undoClicks.setOpacity(1); undoClicks.setDisable(false);
        infoCount.setOpacity(1); infoCount.setDisable(false);
        infoPane.setOpacity(1); infoPane.setDisable(false);
        infoCount.setText("Number of articles: " + Integer.toString(outputList.size()));
        soundInMenu.playClick();
    }

    public void getTopHeadlinesAustria(ActionEvent actionEvent) {
        outputList = controller.getTopHeadlinesAustria();
        setupTable();

        for (Article art : outputList) {
            table.getItems().add(art);
        }
        soundInMenu.playClick();
    }

    public void getAllNewsBitcoin(ActionEvent actionEvent) {
        outputList = controller.getAllNewsBitcoin();
        setupTable();

        for (Article art : outputList) {
            table.getItems().add(art);
        }
        soundInMenu.playClick();
    }

    //quit program event
    public void printExitMessages(ActionEvent actionEvent){
        infoCount.setOpacity(1); infoCount.setDisable(false);
        infoPane.setOpacity(1); infoPane.setDisable(false);
        infoCount.setText("Bye, bye!");
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

    //Choose An Article By Double Click and open article info pane
    public void tableFocusArticle(MouseEvent mouseEvent) {
        int articleIndex;
        if (mouseEvent.getClickCount() == 2) {
            articleIndex = table.getSelectionModel().getSelectedIndex();

            focusPane.setOpacity(1); focusPane.setDisable(false);
            focusText.setOpacity(1); focusText.setDisable(false);
            focusCloseButton.setOpacity(1); focusCloseButton.setDisable(false);

            Article focusArticle = outputList.get(articleIndex);

            focusText.setWrapText(true);
            focusText.setText(focusArticle.toString());
        }
    }

    //hover effects
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

    //closes articles info pane
    public void focusClose(ActionEvent actionEvent) {
        focusPane.setOpacity(0); focusPane.setDisable(true);
        focusText.setOpacity(0); focusText.setDisable(true);
        focusCloseButton.setDisable(true); focusCloseButton.setOpacity(0);
        soundInMenu.playClick();
    }

    //close info panes
    public void undoClick(MouseEvent mouseEvent) {
        if (!infoCount.isDisabled()){
            infoCount.setOpacity(0); infoCount.setDisable(true);
            infoPane.setOpacity(0); infoPane.setDisable(true);
            undoClicks.setOpacity(0); undoClicks.setDisable(true);
            soundInMenu.playClick();
        }
    }
}
