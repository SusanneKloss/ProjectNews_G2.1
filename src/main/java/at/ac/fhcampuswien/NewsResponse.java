package at.ac.fhcampuswien;

import java.util.ArrayList;

public class NewsResponse {
    private String status;
    private int totalResults;
    private ArrayList<Article> articles;

    public String getStatus(){
        return status;
    }

    public int getTotalResults(){
        return totalResults;
    }

    public ArrayList<Article> getArticles(){
        return articles;
    }
}
