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

    public static ArrayList<Article> generateRequestParameter(ArrayList<Object> userInput) {
        String query = "";
        String source = "";
        Endpoint endpoint = null; Language language = null; SortBy sortBy = null; Country country = null; Category category = null;
        for (Object x : userInput){
            if (x instanceof String && ((String) x).startsWith("query")){
                query = (String) x;
            }
            else if (x instanceof String && ((String) x).startsWith("source")){
                source = (String) x;
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
    public ArrayList<Article> getTopHeadlinesAustria() {
        url = NewsAPI.createUrl("", endpoint = Endpoint.TOP_HEADLINES, country = Country.AUSTRIA);
        NewsResponse response = NewsAPI.getNews(url);

        if (response.getArticles() == null){
            return new ArrayList<>();
        }
        return response.getArticles();
    }

    public ArrayList<Article> getAllNewsBitcoin() {
        url = NewsAPI.createUrl("bitcoin", endpoint = Endpoint.EVERYTHING);
        NewsResponse response = NewsAPI.getNews(url);

        if (response.getArticles() == null){
            return new ArrayList<>();
        }
        return response.getArticles();
    }

    protected ArrayList<Article> filterList(String query) {
        url = NewsAPI.createUrl(query,endpoint = Endpoint.EVERYTHING);
        NewsResponse response = NewsAPI.getNews(url);

        return response.getArticles();
    } **/
}
