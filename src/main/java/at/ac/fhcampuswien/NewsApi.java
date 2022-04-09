package at.ac.fhcampuswien;

import com.google.gson.Gson;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class NewsApi {
    /*
    contains
    - logic for sending requests and reception of responses
    - url-builder (eg endpoint plus queryparameter)
    - q has always to be filled!!
    - okhttp and gson library

     */
    /*
    Create Enum classes for request-Parameter
     - category (top Headlines, (sources))
     - country (top Headlines, (sources))
     - endpoint (everything, top Headlines, (sources))
     - language (everything, (sources))
     - sortby (everything)
     */
    //request parameters for endpoint everything https://newsapi.org/docs/endpoints/everything
    //request parameters for endpoint top Headlines https://newsapi.org/docs/endpoints/top-headlines
    //(Endpoint sources is subset of Top Headlines,  Response Object has different member variables, request parameters for endpoint sources https://newsapi.org/docs/endpoints/sources)
    private String endpointEverything;
    private String endpointTopHeadlines;
    private String q;
    private static String API_KEY = "078504f64e1c4b6996e5a1b8e25798f7";



    //https://raw.githubusercontent.com/square/okhttp/master/samples/guide/src/main/java/okhttp3/guide/GetExample.java
    //https://square.github.io/okhttp/
    public String getNews(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {

            return response.body().string();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }


    public void deserializeResponse(String response) {
        Gson gson = new Gson();
        NewsResponse newsResponse = gson.fromJson(response, NewsResponse.class);
        if (newsResponse.getArticles() != null) {
            for (Article article : newsResponse.getArticles()) {
                System.out.println(article.toString());
            }
        }
        else{
            System.out.println(newsResponse.getMessage());
        }

    }
    public static void main (String[]args){
        NewsApi example = new NewsApi();
        example.deserializeResponse(example.getNews("https://newsapi.org/v2/top-headlines?country=at&apiKey=078504f64e1c4b6996e5a1b8e25798f7"));
    }
    }