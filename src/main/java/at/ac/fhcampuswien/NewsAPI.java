package at.ac.fhcampuswien;

import at.ac.fhcampuswien.enums.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;

public class NewsAPI {

    /**
    public static NewsResponse getMeows() throws IOException {
        String url = "https://newsapi.org/v2/everything?q=game&from=2022-02-28&sortBy=publishedAt&apiKey=078504f64e1c4b6996e5a1b8e25798f7";

        //to URL hier einfügen

        String input = deserializeToString(url);

        JsonObject json = JsonParser.parseString(input).getAsJsonObject();
        String status =  json.get("status").getAsString();

        int total = json.get("totalResults").getAsInt();

        ArrayList<Article> importedArticles = new ArrayList<>();

        for (JsonElement article : json.getAsJsonArray("articles")) {
            Article art = new Article(article.getAsJsonObject().get("author").toString(), article.getAsJsonObject().get("title").toString());
            importedArticles.add(art);
        }

        NewsResponse result = new NewsResponse(status, total, importedArticles);

        return result;
    }

    public static String deserializeToString(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        String jsonString;

        try (Response response = client.newCall(request).execute()) {
            jsonString = response.body().string();

        }
        return jsonString;
    } **/

    public static String buildURL(String query, Enum ... e){
        StringBuilder url = new StringBuilder();
        url.append("https://newsapi.org/v2/");
        if (e[0] == Endpoint.topheadlines){
            url.append("top-headlines?");
            for (Enum x : e){
                if (x instanceof Country){url.append("country=" + x + "&");}
                if (x instanceof Category){url.append("category=" + x + "&");}
            }
        }
        else {
            url.append("everything?q=" + query + "&");
            for (Enum x : e){
                if (x instanceof Language){url.append("language=" + x + "&");}
                if (x instanceof Sortby){url.append("sortBy=" + x + "&");}
            }
        }
        url.append("apiKey=078504f64e1c4b6996e5a1b8e25798f7");
        return url.toString();
    }

    public static NewsResponse deserializeToString(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();

        Request request = new Request.Builder()
                .url("https://newsapi.org/v2/everything?q=game&from=2022-03-28&sortBy=publishedAt&apiKey=078504f64e1c4b6996e5a1b8e25798f7")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return gson.fromJson(response.body().string(), NewsResponse.class);
        }
    }
}
