package at.ac.fhcampuswien.controllers;

import at.ac.fhcampuswien.models.Article;
import at.ac.fhcampuswien.models.NewsAPI;
import at.ac.fhcampuswien.models.NewsResponse;
import at.ac.fhcampuswien.models.enums.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    }
    public static ArrayList<Article> StreamTitle15(ArrayList<Article> outputList) {

        //NewsResponse response = NewsAPI.getNews(url);

        List<Article> streamOfArt = outputList.stream()
                .filter(e -> e != null)
                .filter(e -> e.getTitle().length() < 44)
                .collect(Collectors.toList());//.forEach(e->System.out.println(e.getTitle()));

        return new ArrayList<Article>(streamOfArt);

    }
    public static ArrayList<Article> StreamDescription(ArrayList<Article> outputList){

        //NewsResponse response = NewsAPI.getNews(url);




        Comparator<Article> compByLength = (aName, bName) -> aName.getDescription().length() - bName.getDescription().length();



        List<Article> streamOfDesc = outputList.stream()
                .sorted(compByLength.thenComparing(Article::getDescription))
          //    .sorted(Comparator.comparing(Article::getDescription))
                .collect(Collectors.toList());
        return new ArrayList<Article>(streamOfDesc);




    }
}
