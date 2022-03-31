package at.ac.fhcampuswien;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class NewsAPI {

    public String buildURL(){
        return null;
    }

    public String deserializeToString(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String stringy;

        Request request = new Request.Builder()
                .url("https://newsapi.org/v2/everything?q=game&from=2022-02-28&sortBy=publishedAt&apiKey=078504f64e1c4b6996e5a1b8e25798f7")
                .build();

        try (Response response = client.newCall(request).execute()) {
            stringy = response.body().string();



        }
        return stringy;
    }
}
