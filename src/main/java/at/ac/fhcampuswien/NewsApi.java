package at.ac.fhcampuswien;

import com.google.gson.Gson;
import com.sun.javafx.fxml.builder.URLBuilder;
import okhttp3.*;

import java.net.URI;

import java.io.IOException;
import java.util.Scanner;

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
    //-- alternative? -- https://www.gwtproject.org/javadoc/latest/com/google/gwt/http/client/UrlBuilder.html --

        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("https");
        builder.host("newsapi.org");
        builder.addPathSegment("v2");

        String endpoint = "";
        System.out.println("Press 1 for News, 2 for Top Headlines");
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        if(i==1) {
            endpoint = Endpoint.EVERYTHING.label;
        }
        if(i==2){
            endpoint = Endpoint.TOP_HEADLINES.label;
        }
        builder.addPathSegment(endpoint);

        /*
        Endpoint everything:
        - q: Keywords or phrases to search for in the article title and body.
        You can use the AND / OR / NOT keywords, and optionally group these with parenthesis. Eg: crypto AND (ethereum OR litecoin) NOT bitcoin.
        */
        String qKeyword = "";
        scan.nextLine();
        while(qKeyword.equals("")) {
            System.out.println("Please enter at least one search keyword!");
            System.out.println("You can combine keywords with AND / OR");
            System.out.println("You can exclude a keyword by adding NOT before it");
            qKeyword = scan.nextLine();
        }
        builder.addQueryParameter("q", qKeyword);

        /*
        Endpoint everything:
        The 2-letter ISO-639-1 code of the language you want to get headlines for. Possible options: ar,de,en,es,fr,he,it,nl,no,pt,ru,se,ud,zh.
        Default: all languages returned.
         */
        String language = "";
        if(!language.equals("")){
            builder.addQueryParameter("language", language);
        }

        builder.addQueryParameter("apiKey", API_KEY);
        HttpUrl url = builder
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