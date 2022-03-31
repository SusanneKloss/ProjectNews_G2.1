package at.ac.fhcampuswien;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;

public class NewsResponse {


    private String status;
    private int totalResults;
    private ArrayList<Article> articles;

    private String getStatus(String input){
        JsonObject json = JsonParser.parseString(input).getAsJsonObject();
        JsonElement status = json.get("status");
        return "status: " +status.getAsString();
    }

    private int getTotalResults(String input){
        totalResults = 0;
        JsonObject json = JsonParser.parseString(input).getAsJsonObject();
        for (JsonElement article : json.getAsJsonArray("articles")){
            totalResults ++;
        }
        return totalResults;
    }

    private ArrayList<Article> getArticles(String input){
        ArrayList<Article> importedArticles = new ArrayList<>();
        JsonObject json = JsonParser.parseString(input).getAsJsonObject();

        for (JsonElement article : json.getAsJsonArray("articles")) {
            Article art = new Article(article.getAsJsonObject().get("author").toString(), article.getAsJsonObject().get("title").toString());
            importedArticles.add(art);
        }
        return importedArticles;
    }
}
