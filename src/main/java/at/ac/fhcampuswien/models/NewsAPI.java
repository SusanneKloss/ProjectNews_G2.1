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
import io.github.cdimascio.dotenv.Dotenv;

public class NewsAPI {




    private static String API_KEY = Dotenv.load().get("API_TOKEN");

    public static String createUrl(String query, String source, Enum endpoint, Enum category, Enum Language,
                                   Enum country, Enum sortBy){

        Url url = new Url.Builder(API_KEY, base, pageSize)
                .addEndpoint(endpoint.getLabel())
                .addCategory(Category.getLabel())
                .addLanguage(Language.getLabel())
                .addCountry(Country.getLabel())
                .addSortBy(SortBy.getLabel())
                .addQuery(query)
                .addSource(source)
                .stream()
                .filter(e -> notNull);

        System.out.println(url);

        return url.toString();
    }

    //https://raw.githubusercontent.com/square/okhttp/master/samples/guide/src/main/java/okhttp3/guide/GetExample.java
    //https://square.github.io/okhttp/
    public static NewsResponse getNews(String url) throws NewsApiException {

        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();

        Request request = new Url.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {               //execute() throws IOException

            try {
                return gson.fromJson(response.body().string(), NewsResponse.class); //string() may invocate NullPointerException
            }
            catch (NullPointerException nullPointerException) {
                throw new NewsApiException("NullPointer_Custom");
            }
            catch (JsonSyntaxException jsonSyntaxException) {
                System.out.println("jsonSyntaxExceptionMessage: " + jsonSyntaxException.getMessage());
                throw new NewsApiException("JSONSyntaxException_Custom"); //would be necessary for: url = NewsAPI.createUrl(""); that can't happen
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage()); //IOException message: "newsapi.org: nodename nor servname provided, or not known"
            throw new NewsApiException("IOException_Custom//kann nicht ausgeführt werden - überprüfen Sie Ihre Internetverbindung");
            //No WLAN, propagated to -> AppController generateRequestParameter() -> Menu displayNews()
        }
    }
}