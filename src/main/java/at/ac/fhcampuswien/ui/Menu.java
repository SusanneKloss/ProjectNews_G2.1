package at.ac.fhcampuswien.ui;

import at.ac.fhcampuswien.controllers.AppController;
import at.ac.fhcampuswien.controllers.NewsApiException;
import at.ac.fhcampuswien.models.Article;
import at.ac.fhcampuswien.models.NewsAPI;
import at.ac.fhcampuswien.models.NewsResponse;
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

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Menu {

    public TableColumn<Article, String> authorColumn = new TableColumn<>("");
    public TableColumn<Article, String> titleColumn = new TableColumn<>("");
    public TableView table = new TableView();
    public TableView focusTable = new TableView();
    public ScrollPane focusPane;

    public TextArea focusText;
    public Text infoCount;
    public TextField textParameter, textCount, textAPIKey, textErrorMessage;

    public ImageView infoPane, paneGetNewsBlank, paneRelevancyHover, paneRelevancy, panePublishedAtHover, panePublishedAt;
    public ImageView panePopularityHover, panePopularity, paneSource, paneQuery, paneLanguage, paneDomain, paneCountry;
    public ImageView paneNoneParaHover, paneNonePara, paneNoneCatHover, paneNoneCat, paneTechnologyHover, paneTechnology;
    public ImageView paneSportsHover, paneSports, paneScienceHover, paneScience, paneHealthHover, paneHealth, paneGeneralHover;
    public ImageView paneGeneral, paneEntertainmentHover, paneEntertainment, paneBusinessHover, paneBusiness, paneTopHeadlineHover;
    public ImageView paneTopHeadline, paneEverythingHover, paneEverything, paneGetNews, paneGetNewsHover, paneSourceMostAHover;
    public ImageView paneSourceMostA, paneSortDescriptionHover, paneSortDescription, paneLongestNameHover, paneLongestName;
    public ImageView paneHeadline15Hover, paneHeadline15, paneCountNYTHover, paneCountNYT, paneFilter, paneFilterHover;
    public ImageView paneCountBlank, paneCount, paneCountHover, paneKey, paneKeyHover, paneAPIKey, paneExit, paneExitHover;
    public ImageView paneExitErrorMessageHover, paneExitErrorMessage, paneCloseArticleHover, paneCloseArticle, paneExportHover, paneExport;

    public Button focusCloseButton, buttonRelevancy, buttonPublishedAt, buttonPopularity, buttonNonePara, buttonNoneCat;
    public Button buttonTechnology, buttonSports, buttonScience, buttonHealth, buttonGeneral, buttonEntertainment, buttonBusiness;
    public Button buttonTopHeadline, buttonEverything, buttonGetNews, buttonSourceMostA, buttonSortDescription, buttonLongestName;
    public Button buttonHeadline15, buttonCountNYT, buttonFilter, buttonCount, buttonKey, buttonExit, buttonCloseArticle;
    public Button buttonExitErrorMessage, purrButton, buttonExport;

    public AnchorPane undoClicks, root;
    public Group groupSortBy, groupCategory, groupEndpoint, groupFilter, groupCountDisplay, groupParameter, groupGetNews;
    public Group groupFilterOptions, groupCount, groupKey, groupExit, groupAllButtons, APIKeyGroup, groupParaNone;
    public Group groupErrorMessage, groupCloseArticle, groupExport;

    private ArrayList<Group> allGroups = new ArrayList<>();
    private AppController controller = new AppController();
    private ArrayList<Article> outputList = new ArrayList<>();
    private static String INVALID_INPUT_MESSAGE;
    private static String EXIT_MESSAGE;
    private SFX soundInMenu = new SFX();
    private ArrayList<Object> userInput;
    private String parameterState = "country";
    private Article focusArticle;


    //assign columns to table
    private void setupTable(){
        table.getItems().clear();
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    }

    /**
    public void getArticleCount(ActionEvent actionEvent) {
        undoClicks.setOpacity(1);
        undoClicks.setDisable(false);
        infoCount.setOpacity(1);
        infoCount.setDisable(false);
        infoPane.setOpacity(1);
        infoPane.setDisable(false);

        if (outputList != null) {
            infoCount.setText("Number of articles: " + Integer.toString(outputList.size()));

            if (outputList.size() == 0) {
                System.out.println("Number of articles: zero");   //message -> GUI
            }
        } else {
            try {
                throw new NewsApiException("No Articles, OutputList is null"); //GUI 11 Artikel bei List = null ??Me

            } catch (NewsApiException e) {
                e.printStackTrace();
            }
        }
        soundInMenu.playClick();
    } **/


    //Get News

    public void displayNews(){
        try {
            if((userInput.get(0) == Endpoint.EVERYTHING && userInput.get(1) instanceof String) ||
                    (userInput.get(0) == Endpoint.TOP_HEADLINES && userInput.size() > 1)){
                outputList = AppController.generateRequestParameter(userInput);
            }
            else {
                errorMessage("Not enough search parameters!");
            }
        } catch (NewsApiException newsApiException) {       //final catch aus NewsApi class (kein WLAN)
            newsApiException.printStackTrace();             // message -> GUI
            errorMessage("No internet connection!");
        }
        setupTable();

        if (outputList != null){
            for (Article art : outputList) {
                table.getItems().add(art);
            }
            if (outputList.size() == 0){
                System.out.println("Liste ist leer");   //message -> GUI
                errorMessage("No matching articles!");
            }
        }
        else {
            try {
                throw new NewsApiException("OutputList is null"); // wenn API Key falsch ist, no query, not propagated from NewsApi or AppController
            } catch (NewsApiException e) {
                e.printStackTrace();            //message -> GUI
                errorMessage("Received empty list!");
            }
        }

    }

    /**
    public void getTopHeadlinesAustria(ActionEvent actionEvent) {
        try {
            outputList = controller.getTopHeadlinesAustria();

        } catch (NewsApiException newsApiException) {       //final catch aus NewsApi class (kein WLAN)
            newsApiException.printStackTrace();             // message -> GUI
        }
        setupTable();

        // !! neue Custom Exception: zB wenn API Key falsch ist
        //erst hier, da outputList erst in Menu erstellt wird
        if(outputList != null) {
            for (Article art : outputList) {
                table.getItems().add(art);      // Tabelle -> GUI
            }
            if(outputList.size() == 0) {
                System.out.println("Liste ist leer");   //message -> GUI
            }
        }
         else {
            try {
                throw new NewsApiException("TopHeadlines_OutputList is null"); // wenn API Key falsch ist, no query, not propagated from NewsApi or AppController
            } catch (NewsApiException e) {
                e.printStackTrace();            //message -> GUI
            }
        }

        soundInMenu.playClick();
    }

    public void getAllNewsBitcoin(ActionEvent actionEvent) {

        try {
            outputList = controller.getAllNewsBitcoin();

        } catch (NewsApiException newsApiException) {//final catch aus NewsApi class
            System.out.println(newsApiException.getMessage());
            newsApiException.printStackTrace();
        }
        setupTable();

        //bei query = empty oder wenn API Key falsch ist
        // !! neue Custom Exception: erst hier, da outputList erst in Menu erstellt wird
        if(outputList != null) {
            for (Article art : outputList) {
                table.getItems().add(art);
            }
            if(outputList.size() == 0) {
                System.out.println("Liste ist leer");   //message -> GUI

            }
        }
        else {
            try {
                throw new NewsApiException("Bitcoin_OutputList is null"); //bei query = empty oder wenn API Key falsch ist
            } catch (NewsApiException e) {

                e.getMessage();
                e.printStackTrace();
            }
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
            soundInMenu.playClick();
            articleIndex = table.getSelectionModel().getSelectedIndex();

            focusPane.setOpacity(1); focusPane.setDisable(false);
            focusText.setOpacity(1); focusText.setDisable(false);
            buttonCloseArticle.setDisable(false);

            focusArticle = outputList.get(articleIndex);

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
    public void exportArticle(ActionEvent actionEvent) throws IOException {
        soundInMenu.playClick();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(focusArticle.getUrl());
        HttpResponse httpResponse = httpClient.execute(httpget);
        Scanner scan = new Scanner(httpResponse.getEntity().getContent());
        StringBuffer buffer = new StringBuffer();

        while (scan.hasNext()){
            buffer.append(scan.next());
        }

        String article = buffer.toString();
        System.out.println(article);
        PrintWriter write = new PrintWriter("article.txt");
        write.println(article);
    }

    public void errorMessage(String message){
        soundInMenu.playError();
        groupErrorMessage.setOpacity(1); groupErrorMessage.setDisable(false);
        textErrorMessage.setText(message);
    }

    public void exitErrorMessage(ActionEvent actionEvent) {
        soundInMenu.playClick();
        groupErrorMessage.setOpacity(0); groupErrorMessage.setDisable(true);
        textErrorMessage.clear();
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
            closeGetNews(); closeCount(); closeKey(); exitErrorMessage(actionEvent);
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

    public void applyFilter(){
        if (outputList != null){
            for (Article art : outputList) {
                table.getItems().add(art);
            }
            if (outputList.size() == 0){
                System.out.println("Liste ist leer");   //message -> GUI
                errorMessage("No matching articles!");
            }
        }
        else {
            try {
                throw new NewsApiException("OutputList is null"); // wenn API Key falsch ist, no query, not propagated from NewsApi or AppController
            } catch (NewsApiException e) {
                e.printStackTrace();            //message -> GUI
                errorMessage("Received empty list!");
            }
        }
    }

    public void countNYTClick(ActionEvent actionEvent) {
        soundInMenu.playClick();
        paneCountNYT.setOpacity(1); paneCountNYTHover.setOpacity(0);
        closeFilter();
        //long countNyt = AppController.countNYT(outputList);
        //errorMessage(Long.toString(countNyt));
        outputList = AppController.countNYT(outputList);
        setupTable();
        applyFilter();
    }

    public void headline15Click(ActionEvent actionEvent) {
        soundInMenu.playClick();
        paneHeadline15.setOpacity(1); paneHeadline15Hover.setOpacity(0);
        closeFilter();
        //outputList = AppController.shortHeadline(outputList);
        setupTable();
        applyFilter();
    }

    public void longestNameClick(ActionEvent actionEvent) {
        soundInMenu.playClick();
        paneLongestName.setOpacity(1); paneLongestNameHover.setOpacity(0);
        closeFilter();
        //outputList = AppController.longestAuthorName(outputList);
        setupTable();
        applyFilter();
    }

    public void sortDescriptionClick(ActionEvent actionEvent) {
        soundInMenu.playClick();
        paneSortDescription.setOpacity(1); paneSortDescriptionHover.setOpacity(0);
        closeFilter();
        //outputList = AppController.sortByDescription(outputList);
        setupTable();
        applyFilter();
    }

    public void sourceMostAClick(ActionEvent actionEvent) {
        soundInMenu.playClick();
        paneSourceMostA.setOpacity(1); paneSourceMostAHover.setOpacity(0);
        closeFilter();
        //outputList = AppController.mostArticleSource(outputList);
        setupTable();
        applyFilter();
    }

    // ----------------------------------------------------------------------------

    //get article count
    public void countClick(ActionEvent actionEvent) {
        if (groupCountDisplay.isDisable()){
            closeGetNews(); closeFilter(); closeKey(); exitErrorMessage(actionEvent);
            groupCountDisplay.setDisable(false);
            groupCountDisplay.setOpacity(1);

            if (outputList != null) {
                textCount.setText(Integer.toString(outputList.size()) + " Articles");
            }
            else {
                try {
                    throw new NewsApiException("No Articles, OutputList is null"); //GUI 11 Artikel bei List = null ??Me
                } catch (NewsApiException e) {
                    e.printStackTrace();
                    textCount.setText("No articles received");
                }
            }
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
            closeGetNews(); closeFilter(); closeCount(); exitErrorMessage(actionEvent);
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
            closeFilter(); closeCount(); closeKey(); exitErrorMessage(actionEvent);
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
            textParameter.clear();
            textParameter.setPromptText("enter query ...");
            parameterState = "query";
        }
        else if (parameterState.equals("query")){
            textParameter.clear();
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
                textParameter.clear();
                textParameter.setPromptText("enter country ...");
                parameterState = "country";
                blendOutParameter();
            }
            else {
                textParameter.clear();
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
