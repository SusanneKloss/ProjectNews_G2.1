package at.ac.fhcampuswien.ui;

import at.ac.fhcampuswien.controllers.AppController;
import at.ac.fhcampuswien.controllers.NewsApiException;
import at.ac.fhcampuswien.models.Article;
import at.ac.fhcampuswien.models.enums.*;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
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
import javafx.util.Duration;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Locale;

public class Menu {

    public TableColumn<Article, String> authorColumn = new TableColumn<>("");
    public TableColumn<Article, String> titleColumn = new TableColumn<>("");
    public TableView table = new TableView();
    public ScrollPane focusPane;

    public TextArea focusText;
    public TextField textParameter, textCount, textAPIKey, textErrorMessage;

    public ImageView paneRelevancyHover, paneRelevancy, panePublishedAtHover, panePublishedAt;
    public ImageView panePopularityHover, panePopularity;
    public ImageView paneNoneParaHover, paneNonePara, paneNoneCatHover, paneNoneCat, paneTechnologyHover, paneTechnology;
    public ImageView paneSportsHover, paneSports, paneScienceHover, paneScience, paneHealthHover, paneHealth, paneGeneralHover;
    public ImageView paneGeneral, paneEntertainmentHover, paneEntertainment, paneBusinessHover, paneBusiness, paneTopHeadlineHover;
    public ImageView paneTopHeadline, paneEverythingHover, paneEverything, paneGetNews, paneGetNewsHover, paneSourceMostAHover;
    public ImageView paneSourceMostA, paneSortDescriptionHover, paneSortDescription, paneLongestNameHover, paneLongestName;
    public ImageView paneHeadline15Hover, paneHeadline15, paneCountNYTHover, paneCountNYT, paneFilter, paneFilterHover;
    public ImageView paneCount, paneCountHover, paneKey, paneKeyHover, paneExit, paneExitHover;
    public ImageView paneExitErrorMessageHover, paneExitErrorMessage, paneCloseArticleHover, paneCloseArticle, paneExportHover, paneExport;

    public Button buttonRelevancy, buttonPublishedAt, buttonPopularity, buttonNonePara, buttonNoneCat;
    public Button buttonTechnology, buttonSports, buttonScience, buttonHealth, buttonGeneral, buttonEntertainment, buttonBusiness;
    public Button buttonTopHeadline, buttonEverything, buttonGetNews, buttonSourceMostA, buttonSortDescription, buttonLongestName;
    public Button buttonHeadline15, buttonCountNYT, buttonFilter, buttonCount, buttonKey, buttonExit, buttonCloseArticle;
    public Button buttonExitErrorMessage, purrButton, buttonExport;

    public AnchorPane root;
    public Group groupSortBy, groupCategory, groupEndpoint, groupFilter, groupCountDisplay, groupParameter, groupGetNews;
    public Group groupFilterOptions, groupCount, groupKey, groupExit, groupAllButtons, APIKeyGroup, groupParaNone;
    public Group groupErrorMessage, groupCloseArticle, groupExport;

    private ArrayList<Group> allGroups = new ArrayList<>();
    private AppController controller = new AppController();
    private ArrayList<Article> outputList = new ArrayList<>();
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

    //Get News

    public void verifyParameters(){
        if((userInput.get(0) == Endpoint.EVERYTHING && userInput.get(1) instanceof String) ||
                (userInput.get(0) == Endpoint.TOP_HEADLINES && userInput.size() > 1)){
            displayNews();
        }
        else {
            GUIMessage("Not enough search parameters!");
        }
    }

    public void displayNews(){

        try {
            outputList = AppController.generateRequestParameter(userInput);
        }
        catch (NewsApiException newsApiException) {       //final catch from NewsApi class (no WLAN)
            newsApiException.printStackTrace();
            GUIMessage("Check your internet connection!");
            return;
        }

        setupTable();

        if (outputList != null){
            for (Article art : outputList) {
                table.getItems().add(art);
            }
            if (outputList.size() == 0 && userInput.get(1)instanceof String){
                System.out.println("Liste ist leer");
                GUIMessage("No matching articles!");
            }
        }
        else {
            try {
                throw new NewsApiException("OutputList is null"); // wrong API Key, not propagated from NewsApi or AppController
            } catch (NewsApiException e) {
                e.printStackTrace();
                GUIMessage("No response - check your API Key");
            }
        }
    }

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
            closeGetNews(); closeCount(); closeKey(); closeFilter();
            groupErrorMessage.setOpacity(0); groupErrorMessage.setDisable(true);
            textErrorMessage.clear();
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
        paneCloseArticle.setOpacity(1); paneCloseArticleHover.setOpacity(0);
        groupErrorMessage.setOpacity(0); groupErrorMessage.setDisable(true);
        paneExitErrorMessage.setOpacity(1); paneExitErrorMessageHover.setOpacity(0);
        textErrorMessage.clear();
        soundInMenu.playClick();
    }

    // export selected article
    public void exportArticle(ActionEvent actionEvent) throws NewsApiException{
        soundInMenu.playClick();

        String url = focusArticle.getUrl();

        File dir = new File("/Users/lenagross/IdeaProjects/ProjectNews_G2.1");
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        System.out.println(files.length);

        String articleCount = "Article" + (files.length + 1) + ".txt";

        try (InputStream in = new URL(url)      //try with ressources
                .openStream()) {

            // download and save
            Files.copy(in, Paths.get(articleCount), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ex) {          //wrapped IOException
            ex.printStackTrace();
            GUIMessage("Download not possible!");
            throw new NewsApiException("Download not possible!");
        }
    }

    public void GUIMessage(String message){
        soundInMenu.playClick();
        groupErrorMessage.setOpacity(1); groupErrorMessage.setDisable(false);
        textErrorMessage.setText(message);
    }

    public void exitGUIMessage(ActionEvent actionEvent) {
        soundInMenu.playClick();
        groupErrorMessage.setOpacity(0); groupErrorMessage.setDisable(true);
        paneExitErrorMessage.setOpacity(1); paneExitErrorMessageHover.setOpacity(0);
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
            closeGetNews(); closeCount(); closeKey(); exitGUIMessage(actionEvent); textErrorMessage.clear();
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
                GUIMessage("No matching articles!");
            }
        }
        else {
            try {
                throw new NewsApiException("OutputList is null"); // wrong API Key
            } catch (NewsApiException e) {
                e.printStackTrace();
                GUIMessage("No response - check your API Key");
            }
        }
    }

    public void countNYTClick(ActionEvent actionEvent) {
        soundInMenu.playClick();
        paneCountNYT.setOpacity(1); paneCountNYTHover.setOpacity(0);
        closeFilter();
        String count = Long.toString(AppController.countNYT(outputList));
        GUIMessage(count);
    }

    public void headline15Click(ActionEvent actionEvent) {
        soundInMenu.playClick();
        paneHeadline15.setOpacity(1); paneHeadline15Hover.setOpacity(0);
        closeFilter();
        outputList = AppController.StreamTitle15(outputList);
        setupTable();
        applyFilter();
    }

    public void longestNameClick(ActionEvent actionEvent) {
        soundInMenu.playClick();
        paneLongestName.setOpacity(1); paneLongestNameHover.setOpacity(0);
        closeFilter();
        GUIMessage(AppController.longestAuthorName(outputList));
    }

    public void sortDescriptionClick(ActionEvent actionEvent) {
        soundInMenu.playClick();
        paneSortDescription.setOpacity(1); paneSortDescriptionHover.setOpacity(0);
        closeFilter();
        outputList = AppController.StreamDescription(outputList);
        setupTable();
        applyFilter();
    }

    public void sourceMostAClick(ActionEvent actionEvent) {
        soundInMenu.playClick();
        paneSourceMostA.setOpacity(1); paneSourceMostAHover.setOpacity(0);
        closeFilter();
        GUIMessage(AppController.mostArticleSource(outputList));
    }

    // ----------------------------------------------------------------------------

    //get article count
    public void countClick(ActionEvent actionEvent) {
        if (groupCountDisplay.isDisable()){
            closeGetNews(); closeFilter(); closeKey(); exitGUIMessage(actionEvent); textErrorMessage.clear();
            groupCountDisplay.setDisable(false);
            groupCountDisplay.setOpacity(1);

            if (outputList != null) {
                textCount.setText(Integer.toString(outputList.size()) + " Articles");
            }
            else {
                try {
                    throw new NewsApiException("No Articles, OutputList is null");
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
            closeGetNews(); closeFilter(); closeCount(); exitGUIMessage(actionEvent); textErrorMessage.clear();
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
            closeFilter(); closeCount(); closeKey(); exitGUIMessage(actionEvent); textErrorMessage.clear();
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
                verifyParameters();
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

    public void textParameterInput(KeyEvent keyEvent)  {
        if (keyEvent.getCode().equals(KeyCode.ENTER) && parameterState.equals("country")){
            soundInMenu.playClick();
            String entry = textParameter.getText().toUpperCase(Locale.ROOT).replace(" ", "_");
            boolean exists = false;

            Country [] countries = Country.values();
            for (Country values : countries){
                if (values.name().equals(entry)) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                userInput.add(Country.valueOf(entry));
                textParameter.clear();
                groupParameter.requestFocus();
                parameterState = "query";
                textParameter.setPromptText("enter query ...");
            }
            else {
                try {
                    throw new NewsApiException("Enum country");
                } catch(NewsApiException newsApiException){
                    textParameter.clear();
                    groupParameter.requestFocus();
                    textParameter.setPromptText("not a valid country");
                }
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
                verifyParameters();
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
                }
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
                try{
                    throw new NewsApiException("Enum language");
                } catch(NewsApiException newsApiException){
                    textParameter.clear();
                    groupParameter.requestFocus();
                    textParameter.setPromptText("not a valid language");
                }
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
        verifyParameters();
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
