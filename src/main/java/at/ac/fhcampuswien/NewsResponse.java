package at.ac.fhcampuswien;

import java.util.ArrayList;

public class NewsResponse {

    private String status;
    private int totalResults;
    private ArrayList<Article> articles;

    public NewsResponse(String status, int totalResults, ArrayList<Article> articles){
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return this.status;
    }

    public int getTotalResults() {
        return this.totalResults;
    }

    public ArrayList<Article> getArticles() {
        return this.articles;
    }
}
