package at.ac.fhcampuswien;

import com.google.gson.Gson;
import com.sun.javafx.fxml.builder.URLBuilder;
import okhttp3.*;

import java.net.URI;

import java.io.IOException;
import java.util.ArrayList;
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
     endpoint (everything, top Headlines, (sources)),
     category (top Headlines, (sources)), country (top Headlines, (sources)),
     language (everything, (sources)), sortby (everything)
     */
    //request parameters for endpoint everything https://newsapi.org/docs/endpoints/everything
    //request parameters for endpoint top Headlines https://newsapi.org/docs/endpoints/top-headlines
    //(Endpoint sources is subset of Top Headlines,  Response Object has different member variables, request parameters for endpoint sources https://newsapi.org/docs/endpoints/sources)

    private String q;
    private static String API_KEY = "078504f64e1c4b6996e5a1b8e25798f7";
    private String endpoint;
    private String qKeyword;
    private String country;
    private String language;
    private String category;
    private String sources;
    private String domains;
    private String sortBy;
    private String pageSize;
    private String page;
    private String status;
    private String code;
    private String message;

    public String getEndpoint(){
        return endpoint;
    }

    public String getqKeyword() {
        return qKeyword;
    }

    public String getPageSize(){
        return pageSize;
    }

    public String getPage(){
        return page;
    }

    public String getCountry(){
        return country;
    }

    public String getLanguage(){
        return language;
    }

    public String getCategory() {
        return category;
    }

    public String getSources() {
        return sources;
    }

    public String getDomains() {
        return domains;
    }

    public String getSortBy() {
        return sortBy;
    }

    //constructor for endpoint Top-Headlines including all possible parameters
    public NewsApi(String endpoint, String country, String category, String sources, String qKeyword){
        this.endpoint = endpoint;
        this.country = country;
        this.category = category;
        this.sources = sources;
        this.qKeyword = qKeyword;
        this.pageSize = pageSize;
        this.page = page;
    }
    //constructor for endpoint Everything for a selection of the possible parameters
    public NewsApi(String endpoint, String qKeyword, String sources, String domains, String language, String sortBy){
        this.endpoint = endpoint;
        this.qKeyword = qKeyword;
        this.sources = sources;
        this.domains = domains;
        this.language = language;
        this.sortBy = sortBy;
    }

    public NewsApi(){
    }

    public String createUrl(){

    //https://square.github.io/okhttp/4.x/okhttp/okhttp3/-http-url/
    //https://square.github.io/okhttp/3.x/okhttp/okhttp3/HttpUrl.Builder.html
    //-- alternative? -- https://www.gwtproject.org/javadoc/latest/com/google/gwt/http/client/UrlBuilder.html --

        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("https");
        builder.host("newsapi.org");
        builder.addPathSegment("v2");

        //String endpoint = getEndpoint();
        builder.addPathSegment(getEndpoint());

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

        builder.addQueryParameter("apiKey", API_KEY);
        HttpUrl url = builder
            .build();

        System.out.println(url);

        return url.toString();

    }

    static NewsResponse newsResponse;

    //https://raw.githubusercontent.com/square/okhttp/master/samples/guide/src/main/java/okhttp3/guide/GetExample.java
    //https://square.github.io/okhttp/
    public static NewsResponse getNews(String url) {
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();

        Request request = new Request.Builder()
                .url(url)
                .build();

        /*try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            System.out.println(e.getMessage());         //--- !!!DOES NOT WORK!!!! ---
        }
        return "";*/
        try (Response response = client.newCall(request).execute()) {
            return gson.fromJson(response.body().string(), NewsResponse.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new NewsResponse("error", "", e.getMessage());
        }

    }

    //https://www.techiediaries.com/java/java-11-httpclient-gson-send-http-get-parse-json-example/
    /*public ArrayList<Article> deserializeResponse(String response) {
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
        return newsResponse.getArticles();

    }*/
    /*public static void main (String[]args){
        //createUrl();
        NewsApi example = new NewsApi();
        //String url = example.createUrl();
        example.deserializeResponse(example.getNews(example.createUrl()));
    }*/
    }