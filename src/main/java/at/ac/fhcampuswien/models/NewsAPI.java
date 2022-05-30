package at.ac.fhcampuswien.models;

import at.ac.fhcampuswien.controllers.NewsApiException;
import at.ac.fhcampuswien.models.enums.*;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.internal.http2.ConnectionShutdownException;

public class NewsAPI {

    private static String API_KEY = Dotenv.load().get("API_TOKEN");


    public static String createUrl(String query, String source, Enum ... s){

        //https://square.github.io/okhttp/4.x/okhttp/okhttp3/-http-url/
        //https://square.github.io/okhttp/3.x/okhttp/okhttp3/HttpUrl.Builder.html
        //-- alternative? -- https://www.gwtproject.org/javadoc/latest/com/google/gwt/http/client/UrlBuilder.html --

        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("https");
        builder.host("newsapi.org");
        builder.addPathSegment("v2");

        //String endpoint = s[0].getEndpoint();
        //First Enum needs to be an Endpoint

        //https://docs.oracle.com/javase/7/docs/api/java/lang/EnumConstantNotPresentException.html
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

        if (source.length() > 0){
            builder.addQueryParameter("sources", source);
        }

        builder.addQueryParameter("pageSize", String.valueOf(100));
        builder.addQueryParameter("apiKey", API_KEY); //exception handling hier bringt hier nichts - bei falschem API Key nur NullPointerException in Menu: 68 etc.

        HttpUrl url = builder
                .build();

        System.out.println(url);

        return url.toString();
    }



    //https://raw.githubusercontent.com/square/okhttp/master/samples/guide/src/main/java/okhttp3/guide/GetExample.java
    //https://square.github.io/okhttp/
    public static NewsResponse getNews(String url) throws NewsApiException {

        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();

        Request request;

            request = new Request.Builder()
                    .url(url)
                    .build();

        try (Response response = client.newCall(request).execute()) {               //execute() throws IOException

            try {
                return gson.fromJson(response.body().string(), NewsResponse.class); //string() may invocate NullPointerException

            } catch (NullPointerException nullPointerException) {
                throw new NewsApiException("NullPointer_Custom");
            } catch (JsonSyntaxException jsonSyntaxException) {
                System.out.println("jsonSyntaxExceptionMessage: " + jsonSyntaxException.getMessage());
                throw new NewsApiException("JSONSyntaxException_Custom"); //would be necessary for: url = NewsAPI.createUrl(""); that can't happen

            }
        } catch (IOException e) {
            System.out.println(e.getMessage()); //IOException message: "newsapi.org: nodename nor servname provided, or not known"
            throw new NewsApiException("IOException_Custom//kann nicht ausgeführt werden - überprüfen Sie Ihre Internetverbindung");
            //No WLAN, propagated to -> AppController generateRequestParameter() -> Menu displayNews()

        }
    }
}