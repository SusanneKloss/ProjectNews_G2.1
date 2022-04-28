package at.ac.fhcampuswien;

import com.google.gson.Gson;
import com.sun.javafx.fxml.builder.URLBuilder;
import okhttp3.*;

import java.net.URI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class NewsAPI {

    private static String API_KEY = "078504f64e1c4b6996e5a1b8e25798f7";

    public static String createUrl(String query, Enum ... s){

        //https://square.github.io/okhttp/4.x/okhttp/okhttp3/-http-url/
        //https://square.github.io/okhttp/3.x/okhttp/okhttp3/HttpUrl.Builder.html
        //-- alternative? -- https://www.gwtproject.org/javadoc/latest/com/google/gwt/http/client/UrlBuilder.html --

        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("https");
        builder.host("newsapi.org");
        builder.addPathSegment("v2");

        //String endpoint = s[0].getEndpoint();
        //First Enum needs to be an Endpoint

        for (Enum x : s) {
            if (x instanceof Endpoint){builder.addPathSegment(((Endpoint) x).getLabel());}
            if (x instanceof Category){builder.addQueryParameter("category", ((Category) x).getLabel());}
            if (x instanceof Language){builder.addQueryParameter("language", ((Language) x).getLabel());}
            if (x instanceof Country){builder.addQueryParameter("country", ((Country) x).getLabel());}
            if (x instanceof SortBy){builder.addQueryParameter("SortBy", ((SortBy) x).getLabel());}
        }

        if (query.length() > 0){
            builder.addQueryParameter("q", query);
        }

        builder.addQueryParameter("apiKey", API_KEY);
        HttpUrl url = builder
                .build();

        System.out.println(url);

        return url.toString();

        /**
        String qKeyword = getqKeyword();
        if(qKeyword != ""){
            builder.addQueryParameter("q", getqKeyword());
        }
        String domains = getDomains();
        if(domains != ""){
            builder.addQueryParameter("domain", getDomains());
        }
        String language = getLanguage();
        if(language != ""){
            builder.addQueryParameter("language", getLanguage());
        }
        String country = getCountry();
        if(country != ""){
            builder.addQueryParameter("country", getCountry());
        }
        String category = getCategory();
        if(category != ""){
            builder.addQueryParameter("category", getCategory());
        }
        String sources = getSources();
        if(sources != ""){
            builder.addQueryParameter("sources", getSources());
        }
        String sortBy = getSortBy();
        if(sortBy != ""){
            builder.addQueryParameter("sortBy", getSortBy());
        }
        /*String pageSize = Integer.toString(100);
        if(pageSize != Integer.toString(100)){
            builder.addQueryParameter("pageSize", getPageSize());
        }
        String page = Integer.toString(1);
        if(pageSize != Integer.toString(1)){
            builder.addQueryParameter("page", getPage());
        }*/
    }

    //https://raw.githubusercontent.com/square/okhttp/master/samples/guide/src/main/java/okhttp3/guide/GetExample.java
    //https://square.github.io/okhttp/
    public static NewsResponse getNews(String url) {
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return gson.fromJson(response.body().string(), NewsResponse.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new NewsResponse("error", "", e.getMessage());
        }
    }
}