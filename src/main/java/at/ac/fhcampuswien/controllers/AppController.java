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

    public int getArticleCount(){
        if(this.articles == null){
            return 0;}
        else return articles.size();
    }

    //Note: sources parameter must not be combined with category and/or country parameters
    public ArrayList<Article> getTopHeadlinesAustria()  {
        try {
            url = NewsAPI.createUrl(""); //JSONSyntaxException_custom
            //url = NewsAPI.createUrl("", endpoint = Endpoint.TOP_HEADLINES, country = Country.AUSTRIA, language = Language.TURKISH);

        } catch (NewsApiException newsApiException) {
            newsApiException.printStackTrace();
            System.out.println("custom exception getTopHeadlinesAustria_url");
        }

        try {
            NewsResponse response = NewsAPI.getNews(url);  //throw aus NewsApi getNews()
            return response.getArticles();

        }  catch(NewsApiException newsApiException) {
           newsApiException.printStackTrace();
            System.out.println("custom exception getTopHeadlinesAustria_response");
            return new ArrayList<>();
        }


        /*if (response.getArticles() == null){
            return new ArrayList<>();
        }*/

    }


    public ArrayList<Article> getAllNewsBitcoin()  {
        try {
            url = NewsAPI.createUrl("bitcoin", endpoint = Endpoint.EVERYTHING);

        } catch (NewsApiException e) {
            System.out.println("custom exception getAllNewsBItcoin_url");

        }
        NewsResponse response = null;

        try {
            response = NewsAPI.getNews(url);
            return response.getArticles();

        } catch (NewsApiException e) {
            e.printStackTrace();
            System.out.println("custom exception getAllNewsBitcoin");
            return new ArrayList<>();
        }

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
