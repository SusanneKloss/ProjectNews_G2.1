package at.ac.fhcampuswien.controllers;

import at.ac.fhcampuswien.models.Article;
import at.ac.fhcampuswien.models.NewsAPI;
import at.ac.fhcampuswien.models.NewsResponse;
import at.ac.fhcampuswien.models.enums.*;

import java.io.IOException;
import java.util.ArrayList;

public class AppController {

    private ArrayList<Article> articles;
    Endpoint endpoint;
    Category category;
    Country country;
    Language language;
    SortBy sortby;
    static String url;

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public ArrayList<Article> getArticle(){
        return this.articles;
    }

    public int getArticleCount(){
        if(this.articles == null){
            return 0;}
        else return articles.size();
    }

    public static ArrayList<Article> generateRequestParameter(ArrayList<Object> userInput) throws NewsApiException{
        String query = "";
        String source = "";
        Endpoint endpoint = null; Language language = null; SortBy sortBy = null; Country country = null; Category category = null;
        for (Object x : userInput){
            if (x instanceof String && ((String) x).startsWith("query")){
                query = (String) x;
                query = query.replace("query: ", "");
            }
            else if (x instanceof String && ((String) x).startsWith("source")){
                source = (String) x;
                source = source.replace("source: ", "");
            }
            else if (x instanceof Endpoint){
                String value = x.toString();
                endpoint = Endpoint.valueOf(value);
            }
            else if (x instanceof Language){
                String value = x.toString();
                language = Language.valueOf(value);
            }
            else if (x instanceof SortBy){
                String value = x.toString();
                sortBy = SortBy.valueOf(value);
            }
            else if (x instanceof Country){
                String value = x.toString();
                country = Country.valueOf(value);
            }
            else if (x instanceof Category){
                String value = x.toString();
                category = Category.valueOf(value);
            }
        }
        url = NewsAPI.createUrl(query, source, endpoint, language, sortBy, country, category);
        NewsResponse response = NewsAPI.getNews(url);

        if (response.getArticles() == null){
            return new  ArrayList<>();
        }
        return response.getArticles();
    }

    /**
    //Note: sources parameter must not be combined with category and/or country parameters
    public ArrayList<Article> getTopHeadlinesAustria() throws NewsApiException{

        url = NewsAPI.createUrl("", endpoint = Endpoint.TOP_HEADLINES, Country.AUSTRIA);

        //1.url ="":  url = NewsAPI.createUrl("");
        // wirft JSONSyntaxException_custom - das kann aber nicht passieren, Endpoint ist auf jeden Fall da

        //2. no query: url = NewsAPI.createUrl("", Endpoint.TOP_HEADLINES);
        // OutputList ist null, Meldung NewsAPI: Required parameters are missing. Please set any of the following parameters and try again: sources, q, language, country, category.

        //3. kein Suchergebnis: url = NewsAPI.createUrl("Katze", Endpoint.TOP_HEADLINES, Language.GERMAN);
        //OutputList ist leer

        NewsResponse response = NewsAPI.getNews(url);
        System.out.println("NewsApi: " + response.getMessage());
        return response.getArticles();

        /*try {
            //url = NewsAPI.createUrl(""); //nötig für .createUrl("") JSONSyntaxException_custom - das kann aber nicht passieren
           url = NewsAPI.createUrl("", endpoint = Endpoint.TOP_HEADLINES, country = Country.AUSTRIA, language = Language.GERMAN);
        } catch (NewsApiException newsApiException) {
            System.out.println("custom exception_url getTopHeadlinesAustria");
            newsApiException.printStackTrace();
        }

        try {
            NewsResponse response = NewsAPI.getNews(url);  //throw aus NewsApi getNews()
            return response.getArticles();

        }  catch(NewsApiException newsApiException) {
            System.out.println("custom exception_response getTopHeadlinesAustria");
            newsApiException.printStackTrace();
            return new ArrayList<>();
    }

    if (response.getArticles() == null){
        return new ArrayList<>();
    }

    public ArrayList<Article> getAllNewsBitcoin() throws NewsApiException{

        url = NewsAPI.createUrl("bitcoin", endpoint = Endpoint.EVERYTHING); // ohne query - NullPointerException - Menu -> dort gecatcht 90ffge
        NewsResponse response = NewsAPI.getNews(url);
        System.out.println("NewsApi: " + response.getMessage());
        return response.getArticles();

    }

    /*protected ArrayList<Article> filterList(String query)  throws NewsApiException{
        try {
            url = NewsAPI.createUrl(query,endpoint = Endpoint.EVERYTHING); //benötigt throws NewsApiException in createUrl
        } catch (NewsApiException e) {
            e.printStackTrace();
        }
        NewsResponse response = null;
        try {
            response = NewsAPI.getNews(url);
            return response.getArticles();

        } catch (NewsApiException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    } **/
}
