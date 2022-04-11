package at.ac.fhcampuswien;

import java.util.ArrayList;
import java.util.List;


public class AppController {

    //instead of static list function(s) of NewsApi shall be invoked
    //NewsAPI class shall be invoked by AppController class -> instance of newsAPI needed
    //include Enums in AppController methods - WHERE DOES IT MAKE SENSE?

    private NewsApi newsApi;
    String url;
    String response;
    ArrayList<Article> articles;


    public AppController(){
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;

        /*
        setArticles() was tested before getArticleCount() - as there is the requirement to return 0 if list is null
        setArticles() had to be changed after the tests had been written.
        Failed tests for TestSetArticles Scenario 1-4 resp. Scenario 5 and 6 were tested with the code below
         */

        /*
        corr. Test Scenario 5
        https://www.geeksforgeeks.org/null-pointer-exception-in-java/
        try {
            if(articles == null);
        }
        catch (NullPointerException exception){
            System.out.println("NullPointerException");
        }
        */

        /*
        corr. Test Scenario 6
        //https://www.baeldung.com/java-avoid-null-check , 6.1 Avoiding Null Checks Through Coding Practices
        if(articles == null){
            throw new IllegalArgumentException();
        }
        */

        /*
        corr. Test Scenarios 1-4
        //checking of passed parameter - Article objects with author and title (2 Strings) are counted
        //list must not be empty and each Article object in the list must have author and title
        int count = 0;
        for (int i = 0; i < articles.size(); i++) {

            if (articles.get(i) != null && articles.get(i).getTitle().length() > 0 && articles.get(i).getAuthor().length() > 0) {
                count++;
            }
        }

        if (articles.size() != 0 && count == articles.size()) {
            this.articles = articles;
        }
        */

    }

    public ArrayList<Article> getArticle(){
        return this.articles;
    }

    public int getArticleCount(){
        if(this.articles == null){
            return 0;}
        else return this.articles.size();
    }
    //for endpoint Top-Headlines, all possible parameters included
    public ArrayList<Article> getTopHeadlinesAustria(){
        String endpoint = Endpoint.TOP_HEADLINES.label;
        String country = Country.AUSTRIA.label;
        String category = Category.BUSINESS.label;
        String sources = "";
        String qKeyword = "";
        int pageSize = 20; //== default
        int page = 1;      //== default

        NewsApi newsApi = new NewsApi(endpoint, country, category, sources, qKeyword, 20, 1);

        String url = newsApi.createUrl();
        String response = newsApi.getNews(url);
        ArrayList<Article> getTop = newsApi.deserializeResponse(response);

        if (getTop == null){
            getTop = new ArrayList<>();
        }
        return getTop;
    }
    //for endpoint Everything, choice of possible parameters
    public ArrayList<Article> getAllNewsBitcoin() {
        //return filterList("bitcoin", generateMockList());
        String endpoint = Endpoint.EVERYTHING.label;
        String qKeyword = "bitcoin";
        String sources = "";
        String domains = "";
        String language = Language.FRENCH.label;
        String sortBy = "";
        NewsApi newsApi = new NewsApi(endpoint, qKeyword, sources, domains, language, sortBy);

        String url = newsApi.createUrl();
        String response = newsApi.getNews(url);
        ArrayList<Article> getAllNews = newsApi.deserializeResponse(response);

        if (getAllNews == null){
            getAllNews = new ArrayList<>();
        }
        return getAllNews;
    }
    protected ArrayList<Article> filterList(String query, ArrayList<Article> articles){
        ArrayList<Article> match = new ArrayList<>();

        for(Article a : articles){
            if(a.getTitle().toLowerCase().contains(query.toLowerCase())){
                match.add(a);
            }
        }
        return match;
    }

    /*private static ArrayList<Article> generateMockList(){
        ArrayList<Article> mock = new ArrayList<>();
        Article one =  new Article("Karl Marx", "Das Kapital");
        mock.add(one);
        Article two = new Article("Peter Molyneux", "Why i am a god");
        mock.add(two);
        Article three = new Article("Angela Merkel", "Wie ich die Raute erfand");
        mock.add(three);
        Article four = new Article("Donald Trump", "My orange hair");
        mock.add(four);
        Article five = new Article("Carl Barks", "Dagobert - Sein Leben, Seine Milliarden");
        mock.add(five);
        Article six = new Article("Elon Musk", "Bitcoin and Cryptocurrency");
        mock.add(six);
        Article seven = new Article("Satoshi Nakamoto", "How i invented blockchain and bitcoin");
        mock.add(seven);
        Article eight = new Article("Donald Trump", "Why all austrians live in trees.");
        mock.add(eight);
        return mock;
    }*/

    /*public static ArrayList<Article> getMockList(){
        return generateMockList();
    }*/

}
