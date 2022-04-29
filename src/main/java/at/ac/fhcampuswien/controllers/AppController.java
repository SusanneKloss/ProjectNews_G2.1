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
    public ArrayList<Article> getTopHeadlinesAustria() {
        url = NewsAPI.createUrl("", endpoint = Endpoint.TOP_HEADLINES, country = Country.AUSTRIA);
        NewsResponse response = NewsAPI.getNews(url);
        System.out.println(url);

        if (response.getArticles() == null){
            return new ArrayList<>();
        }
        return response.getArticles();
    }

    public ArrayList<Article> getAllNewsBitcoin() {
        url = NewsAPI.createUrl("bitcoin", endpoint = Endpoint.EVERYTHING);
        NewsResponse response = NewsAPI.getNews(url);

        System.out.println(url);

        if (response.getArticles() == null){
            return new ArrayList<>();
        }
        return response.getArticles();
    }
    protected ArrayList<Article> filterList(String query) {
        url = NewsAPI.createUrl(query,endpoint = Endpoint.EVERYTHING);
        NewsResponse response = NewsAPI.getNews(url);

        return response.getArticles();
    }
}
