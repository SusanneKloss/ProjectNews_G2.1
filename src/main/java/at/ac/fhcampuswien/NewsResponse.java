package at.ac.fhcampuswien;

import java.util.ArrayList;

public class NewsResponse {
    private String status;
    private int totalResults;
    private ArrayList<Article> articles;

    private String code;
    private String message;

    public NewsResponse(String status, int totalResults, ArrayList<Article> articles){
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }
    //Response object for errors - https://newsapi.org/docs/errors
    public NewsResponse(String status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getStatus(){
        return status;
    }

    public int getTotalResults(){
        return totalResults;
    }

    public ArrayList<Article> getArticles(){
        return articles;
    }

    public String getMessage(){
        return message;
    }
}
