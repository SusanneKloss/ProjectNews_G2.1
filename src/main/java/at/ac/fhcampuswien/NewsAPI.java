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

    public static String buildURL(String query, Enum ... e){
        StringBuilder url = new StringBuilder();
        url.append("https://newsapi.org/v2/");
        if (e[0] == Endpoint.topheadlines){
            url.append("top-headlines?q=" + query + "&");
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
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return gson.fromJson(response.body().string(), NewsResponse.class);
        }
    }
}
