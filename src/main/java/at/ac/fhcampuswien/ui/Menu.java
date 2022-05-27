package at.ac.fhcampuswien.ui;

import at.ac.fhcampuswien.controllers.AppController;
import at.ac.fhcampuswien.models.Article;
import at.ac.fhcampuswien.models.enums.*;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Menu {

    public TableColumn<Article, String> authorColumn = new TableColumn<>("");
    public TableColumn<Article, String> titleColumn = new TableColumn<>("");
    public TableView table = new TableView();
    public TableView focusTable = new TableView();
    public ScrollPane focusPane;

    public TextArea focusText;
    public Text infoCount;
    public TextField textParameter, textCount, textAPIKey;

    public ImageView infoPane, paneGetNewsBlank, paneRelevancyHover, paneRelevancy, panePublishedAtHover, panePublishedAt;
    public ImageView panePopularityHover, panePopularity, paneSource, paneQuery, paneLanguage, paneDomain, paneCountry;
    public ImageView paneNoneParaHover, paneNonePara, paneNoneCatHover, paneNoneCat, paneTechnologyHover, paneTechnology;
    public ImageView paneSportsHover, paneSports, paneScienceHover, paneScience, paneHealthHover, paneHealth, paneGeneralHover;
    public ImageView paneGeneral, paneEntertainmentHover, paneEntertainment, paneBusinessHover, paneBusiness, paneTopHeadlineHover;
    public ImageView paneTopHeadline, paneEverythingHover, paneEverything, paneGetNews, paneGetNewsHover, paneSourceMostAHover;
    public ImageView paneSourceMostA, paneSortDescriptionHover, paneSortDescription, paneLongestNameHover, paneLongestName;
    public ImageView paneHeadline15Hover, paneHeadline15, paneCountNYTHover, paneCountNYT, paneFilter, paneFilterHover;
    public ImageView paneCountBlank, paneCount, paneCountHover, paneKey, paneKeyHover, paneAPIKey, paneExit, paneExitHover;

    public Button focusCloseButton, buttonRelevancy, buttonPublishedAt, buttonPopularity, buttonNonePara, buttonNoneCat;
    public Button buttonTechnology, buttonSports, buttonScience, buttonHealth, buttonGeneral, buttonEntertainment, buttonBusiness;
    public Button buttonTopHeadline, buttonEverything, buttonGetNews, buttonSourceMostA, buttonSortDescription, buttonLongestName;
    public Button buttonHeadline15, buttonCountNYT, buttonFilter, buttonCount, buttonKey, buttonExit, buttonCloseArticle;

    public AnchorPane undoClicks;
    public Group groupSortBy, groupCategory, groupEndpoint, groupFilter, groupCountDisplay, groupParameter, groupGetNews;
    public Group groupFilterOptions, groupCount, groupKey, groupExit, groupAllButtons, APIKeyGroup, groupParaNone;
    public AnchorPane root;

    private ArrayList<Group> allGroups = new ArrayList<>();
    private AppController controller = new AppController();
    private ArrayList<Article> outputList = new ArrayList<>();
    private SFX soundInMenu = new SFX();
    private ArrayList<Object> userInput;
    private String parameterState = "country";


    //assign columns to table
    private void setupTable(){
        table.getItems().clear();
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    }

    //Get News

    public void displayNews(){
        outputList = AppController.generateRequestParameter(userInput);
        setupTable();

        for (Article art : outputList) {
            table.getItems().add(art);
        }
    }

    /**
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
    } **/

    //quit program event
    public void exitClick(ActionEvent actionEvent){
        soundInMenu.playMeow();
        PauseTransition pauseEnd = new PauseTransition(Duration.millis(500));
        pauseEnd.setOnFinished(event1 -> {
            System.exit(0);
        });
        pauseEnd.play();
    }

    //Choose An Article By Double Click and open article info pane
    public void tableFocusArticle(MouseEvent mouseEvent) {
        int articleIndex;
        if (mouseEvent.getClickCount() == 2) {
            articleIndex = table.getSelectionModel().getSelectedIndex();

            focusPane.setOpacity(1); focusPane.setDisable(false);
            focusText.setOpacity(1); focusText.setDisable(false);
            buttonCloseArticle.setDisable(false);

            Article focusArticle = outputList.get(articleIndex);

            focusText.setWrapText(true);
            focusText.setText(focusArticle.toString());
        }
    }

    //closes articles info pane
    public void closeArticle(ActionEvent actionEvent) {
        focusPane.setOpacity(0); focusPane.setDisable(true);
        focusText.setOpacity(0); focusText.setDisable(true);
        buttonCloseArticle.setDisable(true);
        soundInMenu.playClick();
    }

    // export selected article
    public void exportArticle(ActionEvent actionEvent) {
    }

    // Button Hover Effects

    /**
     https://stackoverflow.com/questions/24986776/how-do-i-get-all-nodes-in-a-parent-in-javafx
     **/
    public static ArrayList<Node> getAllChildren(Parent root, ArrayList<Node> nodes) {
        for (Node node : root.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent)
                getAllChildren((Parent)node, nodes);
        }
        return nodes;
    }
    /** bis hier **/

    public void buttonEntered(MouseEvent mouseEvent) {
        String buttonID = ((Control)mouseEvent.getSource()).getId();
        buttonID = buttonID.replace("button", "pane");
        String buttonIDHover = buttonID + "Hover";

        ArrayList<Node> nodes = new ArrayList<>();
        ArrayList<Node> output = getAllChildren(root, nodes);

        for (Node x : output){
            if(x.getId() != null) {
                if (x.getId().equals(buttonID) && !x.getParent().isDisabled()) {
                    x.setOpacity(0);
                    soundInMenu.playHover();
                }
                if (x.getId().equals(buttonIDHover)){
                    x.setOpacity(1);
                }
            }
        }
    }

    public void buttonExited(MouseEvent mouseEvent) {
        String buttonID = ((Control)mouseEvent.getSource()).getId();
        buttonID = buttonID.replace("button", "pane");
        String buttonIDHover = buttonID + "Hover";

        ArrayList<Node> nodes = new ArrayList<>();
        ArrayList<Node> output = getAllChildren(root, nodes);

        for (Node x : output){
            if(x.getId() != null) {
                if (x.getId().equals(buttonID) && !x.getParent().isDisabled()) {
                    x.setOpacity(1);
                }
                if (x.getId().equals(buttonIDHover)){
                    x.setOpacity(0);
                }
            }
        }
    }

    // Menu Options

    //Filters (-> Streams)
    public void filterClick(ActionEvent actionEvent) {
        if (groupFilterOptions.isDisable()){
            closeGetNews(); closeCount(); closeKey();
            groupFilterOptions.setOpacity(1);
            groupFilterOptions.setDisable(false);
        } else {
            closeFilter();
        }
        soundInMenu.playClick();
    }

    public void closeFilter(){
        groupFilterOptions.setOpacity(0);
        groupFilterOptions.setDisable(true);
    }

    // ---------------------------------------------- FILTER -------------------


    public void countNYTClick(ActionEvent actionEvent) {
        paneCountNYT.setOpacity(1); paneCountNYTHover.setOpacity(0);
        closeFilter();
        //outputList = AppController.countNYT(outputList);
        setupTable();

        for (Article art : outputList) {
            table.getItems().add(art);
        }
        soundInMenu.playClick();
    }

    public void headline15Click(ActionEvent actionEvent) {
        paneHeadline15.setOpacity(1); paneHeadline15Hover.setOpacity(0);
        closeFilter();
        //outputList = AppController.shortHeadline(outputList);
        setupTable();

        for (Article art : outputList) {
            table.getItems().add(art);
        }
        soundInMenu.playClick();
    }

    public void longestNameClick(ActionEvent actionEvent) {
        paneLongestName.setOpacity(1); paneLongestNameHover.setOpacity(0);
        closeFilter();
        //outputList = AppController.longestAuthorName(outputList);
        setupTable();

        for (Article art : outputList) {
            table.getItems().add(art);
        }
        soundInMenu.playClick();
    }

    public void sortDescriptionClick(ActionEvent actionEvent) {
        paneSortDescription.setOpacity(1); paneSortDescriptionHover.setOpacity(0);
        closeFilter();
        //outputList = AppController.sortByDescription(outputList);
        setupTable();

        for (Article art : outputList) {
            table.getItems().add(art);
        }
        soundInMenu.playClick();
    }

    public void sourceMostAClick(ActionEvent actionEvent) {
        paneSourceMostA.setOpacity(1); paneSourceMostAHover.setOpacity(0);
        closeFilter();
        //outputList = AppController.mostArticleSource(outputList);
        setupTable();

        for (Article art : outputList) {
            table.getItems().add(art);
        }
        soundInMenu.playClick();
    }

    // ----------------------------------------------------------------------------

    //get article count
    public void countClick(ActionEvent actionEvent) {
        if (groupCountDisplay.isDisable()){
            closeGetNews(); closeFilter(); closeKey();
            groupCountDisplay.setDisable(false);
            groupCountDisplay.setOpacity(1);
            textCount.setText(Integer.toString(outputList.size()) + " Articles");
        } else {
            closeCount();
        }
        soundInMenu.playClick();
    }

    public void closeCount(){
        groupCountDisplay.setDisable(true);
        groupCountDisplay.setOpacity(0);
        textCount.setText("");
    }

    //field for api key
    public void keyClick(ActionEvent actionEvent) {
        if (APIKeyGroup.isDisable()){
            closeGetNews(); closeFilter(); closeCount();
            APIKeyGroup.setDisable(false);
            APIKeyGroup.setOpacity(1);
        } else {
            closeKey();
        }
        soundInMenu.playClick();
    }

    public void closeKey(){
        APIKeyGroup.setDisable(true);
        APIKeyGroup.setOpacity(0);
    }

    // ---------------------------------------------- GET NEWS -------------------

    //get news button
    public void getNewsClick(ActionEvent actionEvent) {
        userInput = new ArrayList<>();
        if (groupEndpoint.isDisable() && groupCategory.isDisable() && groupParaNone.isDisable() && groupSortBy.isDisable()) {
            closeFilter(); closeCount(); closeKey();
            groupEndpoint.setOpacity(1); groupEndpoint.setDisable(false);
        } else {
            closeGetNews();
        }
        soundInMenu.playClick();
    }

    public void closeGetNews(){
        groupEndpoint.setOpacity(0); groupEndpoint.setDisable(true);
        groupCategory.setOpacity(0); groupCategory.setDisable(true);
        groupParaNone.setOpacity(0); groupParaNone.setDisable(true);
        textParameter.setOpacity(0); textParameter.setDisable(true);
        groupSortBy.setOpacity(0); groupSortBy.setDisable(true);
    }

    // ------------------ Endpoint ---------------
    public void everythingClick(ActionEvent actionEvent) {
        userInput.add(Endpoint.EVERYTHING);
        paneEverything.setOpacity(1); paneEverything.setDisable(false);
        paneEverythingHover.setOpacity(0); paneEverythingHover.setDisable(true);
        groupEndpoint.setOpacity(0); groupEndpoint.setDisable(true);
        groupParaNone.setOpacity(1); groupParaNone.setDisable(false);
        textParameter.setOpacity(1); textParameter.setDisable(false);
        textParameter.setPromptText("enter query ..."); parameterState = "query";
        soundInMenu.playClick();
    }

    public void topHeadlineClick(ActionEvent actionEvent) {
        userInput.add(Endpoint.TOP_HEADLINES);
        paneTopHeadline.setOpacity(1); paneTopHeadline.setDisable(false);
        paneTopHeadlineHover.setOpacity(0); paneTopHeadlineHover.setDisable(true);
        groupEndpoint.setOpacity(0); groupEndpoint.setDisable(true);
        groupCategory.setOpacity(1); groupCategory.setDisable(false);
        soundInMenu.playClick();
    }


    // ------------------ Category ---------------
    public void businessClick(ActionEvent actionEvent) {
        userInput.add(Category.BUSINESS);
        paneBusiness.setOpacity(1);
        paneBusinessHover.setOpacity(1);
        openCountry();
    }

    public void entertainmentClick(ActionEvent actionEvent) {
        userInput.add(Category.ENTERTAINMENT);
        paneEntertainment.setOpacity(1);
        paneEntertainmentHover.setOpacity(1);
        openCountry();
    }

    public void generalClick(ActionEvent actionEvent) {
        userInput.add(Category.GENERAL);
        paneGeneral.setOpacity(1);
        paneGeneralHover.setOpacity(1);
        openCountry();
    }

    public void healthClick(ActionEvent actionEvent) {
        userInput.add(Category.HEALTH);
        paneHealth.setOpacity(1);
        paneHealthHover.setOpacity(1);
        openCountry();
    }

    public void scienceClick(ActionEvent actionEvent) {
        userInput.add(Category.SCIENCE);
        paneScience.setOpacity(1);
        paneScienceHover.setOpacity(1);
        openCountry();
    }

    public void sportsClick(ActionEvent actionEvent) {
        userInput.add(Category.SPORTS);
        paneSports.setOpacity(1);
        paneSportsHover.setOpacity(1);
        openCountry();
    }

    public void technologyClick(ActionEvent actionEvent) {
        userInput.add(Category.TECHNOLOGY);
        paneTechnology.setOpacity(1);
        paneTechnologyHover.setOpacity(1);
        openCountry();
    }

    public void noneCatClick(ActionEvent actionEvent) {
        paneNoneCat.setOpacity(1);
        paneNoneCatHover.setOpacity(1);
        openCountry();
    }

    public void openCountry(){
        groupCategory.setOpacity(0); groupCategory.setDisable(true);
        groupParaNone.setOpacity(1); groupParaNone.setDisable(false);
        textParameter.setOpacity(1); textParameter.setDisable(false);
        textParameter.setPromptText("enter country ...");
        soundInMenu.playClick();
    }

    // ------------------ Parameter ---------------
    public void noneParaClick(ActionEvent actionEvent) {
        soundInMenu.playClick();
        if (parameterState.equals("country")){
            textParameter.setPromptText("enter query ...");
            parameterState = "query";
        }
        else if (parameterState.equals("query")){
            textParameter.setPromptText("enter source ...");
            parameterState = "source";
        }
        else if (parameterState.equals("source")){
            if (userInput.get(0) == Endpoint.TOP_HEADLINES){
                // article liste mit endpoint top headlines erstellen ---------------------------
                displayNews();
                for (int i = 0; i < userInput.size(); i++){
                    System.out.println(userInput.get(i));
                }
                textParameter.setPromptText("enter country ...");
                parameterState = "country";
                blendOutParameter();
            }
            else {
                textParameter.setPromptText("enter language ...");
                parameterState = "language";
            }
        }
        else if (parameterState.equals("language")){
            parameterState = "country";
            textParameter.setPromptText("enter country ...");
            textParameter.clear();
            groupParameter.requestFocus();
            blendOutParameter();
            groupSortBy.setDisable(false); groupSortBy.setOpacity(1);
        }
    }

    public void textParameterInput(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER) && parameterState.equals("country")){
            soundInMenu.playClick();
            String entry = textParameter.getText().toUpperCase(Locale.ROOT).replace(" ", "_");
            boolean exists = false;
            Country [] countries = Country.values();
            for (Country values : countries){
                if (values.name().equals(entry)) {
                    exists = true;
                    break;
                };
            }
            if (exists) {
                userInput.add(Country.valueOf(entry));
                textParameter.clear();
                groupParameter.requestFocus();
                parameterState = "query";
                textParameter.setPromptText("enter query ...");
            }
            else {
                textParameter.clear();
                groupParameter.requestFocus();
                textParameter.setPromptText("not a valid country");
            }
        }
        else if (keyEvent.getCode().equals(KeyCode.ENTER) && parameterState.equals("query")){
            String entry = "query: " + textParameter.getText();
            userInput.add(entry);
            parameterState = "source";
            textParameter.setPromptText("enter source ...");
            textParameter.clear();
            groupParameter.requestFocus();
        }
        else if (keyEvent.getCode().equals(KeyCode.ENTER) && parameterState.equals("source")){
            String entry = "source: " + textParameter.getText();
            userInput.add(entry);
            if (userInput.get(0) == Endpoint.TOP_HEADLINES){
                parameterState = "country";
                textParameter.setPromptText("enter country ...");
                blendOutParameter();
                // article liste mit endpoint top headlines erstellen ---------------------------
                displayNews();
                for (int i = 0; i < userInput.size(); i++){
                    System.out.println(userInput.get(i));
                }
            }
            else {
                parameterState = "language";
                textParameter.setPromptText("enter language ...");
            }
            textParameter.clear();
            groupParameter.requestFocus();
        }
        else if (keyEvent.getCode().equals(KeyCode.ENTER) && parameterState.equals("language")){
            String entry = textParameter.getText().toUpperCase(Locale.ROOT);
            boolean exists = false;
            Language [] languages = Language.values();
            for (Language values : languages){
                if (values.name().equals(entry)) {
                    exists = true;
                    break;
                };
            }
            if (exists){
                userInput.add(Language.valueOf(entry));
                textParameter.clear();
                groupParameter.requestFocus();
                parameterState = "country";
                textParameter.setPromptText("enter country ...");
                blendOutParameter();
                groupSortBy.setDisable(false); groupSortBy.setOpacity(1);
            }
            else {
                textParameter.clear();
                groupParameter.requestFocus();
                textParameter.setPromptText("not a valid language");
            }
        }
    }

    public void blendOutParameter(){
        paneNonePara.setOpacity(1); paneNonePara.setDisable(false);
        paneNoneParaHover.setOpacity(0); paneNoneParaHover.setDisable(true);
        groupParaNone.setOpacity(0); groupParaNone.setDisable(true);
        textParameter.setOpacity(0); textParameter.setDisable(true);
    }

    // ------------------ SortBy ---------------
    public void popularityClick(ActionEvent actionEvent) {
        userInput.add(SortBy.POPULARITY);
        panePopularity.setOpacity(1); panePopularityHover.setOpacity(0);
        closeSortBy();
    }

    public void publishedAtClick(ActionEvent actionEvent) {
        userInput.add(SortBy.PUBLISHED_AT);
        panePublishedAt.setOpacity(1); panePublishedAtHover.setOpacity(0);
        closeSortBy();
    }

    public void relevancyClick(ActionEvent actionEvent) {
        userInput.add(SortBy.RELEVANCY);
        paneRelevancy.setOpacity(1); paneRelevancyHover.setOpacity(0);
        closeSortBy();
    }

    public void closeSortBy(){
        soundInMenu.playClick();
        groupSortBy.setDisable(true); groupSortBy.setOpacity(0);
        displayNews();
        for (int i = 0; i < userInput.size(); i++){
            System.out.println(userInput.get(i));
        }
    }

    public void startPurr(MouseEvent mouseEvent) {
        soundInMenu.playPurr();
    }

    public void stopPurr(MouseEvent mouseEvent) {
        soundInMenu.stopPurr();
    }
}
