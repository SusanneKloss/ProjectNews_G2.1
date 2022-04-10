package at.ac.fhcampuswien;

import com.google.gson.Gson;
import com.sun.javafx.fxml.builder.URLBuilder;
import okhttp3.*;

import java.net.URI;

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


    public String createUrl(){

    //https://square.github.io/okhttp/4.x/okhttp/okhttp3/-http-url/
    //https://square.github.io/okhttp/3.x/okhttp/okhttp3/HttpUrl.Builder.html
    HttpUrl url = new HttpUrl.Builder()
            .scheme("https")
            .host("newsapi.org")
            .addPathSegment("v2")
            .addPathSegment(Endpoint.TOP_HEADLINES.label)
            .addQueryParameter("country", Country.ARGENTINA.label)
            .addQueryParameter("apiKey", API_KEY)
            .build();

        System.out.println(url);

        return url.toString();
    }

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

    //https://www.techiediaries.com/java/java-11-httpclient-gson-send-http-get-parse-json-example/
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
        //createUrl();
        NewsApi example = new NewsApi();
        //String url = example.createUrl();
        example.deserializeResponse(example.getNews(example.createUrl()));
    }
    }