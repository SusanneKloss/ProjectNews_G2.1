package at.ac.fhcampuswien.controllers;

import at.ac.fhcampuswien.downloader.Downloader;
import at.ac.fhcampuswien.models.Article;
import at.ac.fhcampuswien.models.NewsAPI;
import at.ac.fhcampuswien.models.NewsResponse;
import at.ac.fhcampuswien.models.enums.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.Comparator;
//Singleton-Pattern
public class AppController {
    //private static field for storing singleton instance
    private static AppController controller = null;
    //private constructor
    private AppController(){}
    //declaration of public static creation method for getting the instance
    public static AppController getInstance(){
        if(controller == null) {
            controller = new AppController();
        }
        return controller;
    }
    private static NewsAPI newsAPI = NewsAPI.getInstance();
    private static String url;

    public static ArrayList<Article> generateRequestParameter(ArrayList<Object> userInput) throws NewsApiException {
        String query = "";
        String source = "";
        Endpoint endpoint = null; Language language = null; SortBy sortBy = null; Country country = null; Category category = null;
        for (Object x : userInput){
            if (x instanceof String && ((String) x).startsWith("query")){
                query = (String) x;
                query = query.replace("query: ", "");
            }
            else if (x instanceof String && ((String) x).startsWith("source")){
                source = (String) x;
                source = source.replace("source: ", "");
            }
            else if (x instanceof Endpoint){
                String value = x.toString();
                endpoint = Endpoint.valueOf(value);
            }
            else if (x instanceof Language){
                String value = x.toString();
                language = Language.valueOf(value);
            }
            else if (x instanceof SortBy){
                String value = x.toString();
                sortBy = SortBy.valueOf(value);
            }
            else if (x instanceof Country){
                String value = x.toString();
                country = Country.valueOf(value);
            }
            else if (x instanceof Category){
                String value = x.toString();
                category = Category.valueOf(value);
            }
        }

        url = NewsAPI.createUrl(query, source, endpoint, language, sortBy, country, category);

        NewsResponse response = NewsAPI.getNews(url);
        return response.getArticles();
    }

    // returns number of downloaded article urls
    public int downloadURLs(Downloader downloader, ArrayList<Article> outputList) throws NewsApiException, ExecutionException, InterruptedException {
        if(outputList == null)
            throw new NewsApiException();

        List<String> urls = outputList.stream()
                .map(Article::getUrl)
                .collect(Collectors.toList());

        // TODO extract urls from articles with java stream

        return downloader.process(urls);
    }


    // ------------------------------- STREAMS !!!

    public static ArrayList<Article> StreamTitle15(ArrayList<Article> outputList) {

        //NewsResponse response = NewsAPI.getNews(url);
        List<Article> streamOfArt = outputList.stream()
                .filter(e -> e != null)
                .filter(e -> e.getTitle().length() < 15)
                .collect(Collectors.toList());//.forEach(e->System.out.println(e.getTitle()));

        return new ArrayList<Article>(streamOfArt);
    }

    public static ArrayList<Article> StreamDescription(ArrayList<Article> outputList){

        //NewsResponse response = NewsAPI.getNews(url);
        Comparator<Article> compByLength = (aName, bName) -> aName.getDescription().length() - bName.getDescription().length();

        List<Article> streamOfDesc = outputList.stream().filter(e -> e.getDescription() != null)
                .sorted(compByLength.thenComparing(Article::getDescription))
                //    .sorted(Comparator.comparing(Article::getDescription))
                .collect(Collectors.toList());
        return new ArrayList<Article>(streamOfDesc);
    }

    //How many articles are from New York Times
    public static long countNYT(ArrayList<Article> outputList) {

        long count = outputList.stream()
                .filter(e -> e.getSource().getName().equals("BBC News"))
                .count();

        System.out.println(count);
        return count;
    }

    //Find author with the longest name
    public static String longestAuthorName(ArrayList<Article> outputList) throws NewsApiException{

        Comparator<Article> compByLength = (aName, bName) -> aName.getAuthor().length() - bName.getAuthor().length();

        Article article = outputList.stream().filter(e -> e.getAuthor() != null)
                .max(Comparator.comparing(e -> e.getAuthor().length()))
                .orElse(null);

        if (article == null) {
            throw new NewsApiException();
        } else {
            return article.getAuthor();
        }
    }

    //source: https://stackoverflow.com/questions/43616422/find-the-most-common-attribute-value-from-a-list-of-objects-using-stream
    public static String mostArticleSource(ArrayList<Article> outputList) {

        String mostCommonSource = outputList.stream()
                // filter some person without a tag out
                .filter(articles -> Objects.nonNull(articles.getAuthor()))
                // summarize tags
                .collect(Collectors.groupingBy(Article::getAuthor, Collectors.counting()))
                // fetch the max entry
                .entrySet().stream().max(Map.Entry.comparingByValue())
                // map to tag
                .map(Map.Entry::getKey).orElse(null);

        return mostCommonSource;
    }
}
