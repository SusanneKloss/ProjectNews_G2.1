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
    String url;


    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public ArrayList<Article> getArticle(){
        return this.articles;
    }
    //NEVER USED!!
    public int getArticleCount(){
        if(this.articles == null){
            return 0;}
        else return articles.size();

    }

    //Note: sources parameter must not be combined with category and/or country parameters
    public ArrayList<Article> getTopHeadlinesAustria()  throws NewsApiException{

        url = NewsAPI.createUrl("", endpoint = Endpoint.TOP_HEADLINES, country = Country.AUSTRIA, language = Language.GERMAN);

        NewsResponse response = NewsAPI.getNews(url);
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
        }*/

        /*if (response.getArticles() == null){
            return new ArrayList<>();
        }*/

    }


    public ArrayList<Article> getAllNewsBitcoin() throws NewsApiException{

        url = NewsAPI.createUrl("bitcoin", endpoint = Endpoint.EVERYTHING); // ohne query - NullPointerException - Menu -> dort gecatcht 90ff
        /*catch (NewsApiException e) {
            System.out.println("custom exception getAllNewsBItcoin_url");
        }*/


        NewsResponse response = NewsAPI.getNews(url);
        return response.getArticles();

        /*catch (NewsApiException newsApiException) {
            newsApiException.printStackTrace();
            System.out.println("custom exception getAllNewsBitcoin");
            return new ArrayList<>();
        }*/


        /*if (response.getArticles() == null){
            return new ArrayList<>();
        }*/
    }

    protected ArrayList<Article> filterList(String query) {
        try {
            url = NewsAPI.createUrl(query,endpoint = Endpoint.EVERYTHING);
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

    }
}
