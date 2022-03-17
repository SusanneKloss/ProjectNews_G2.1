package at.ac.fhcampuswien;

import java.util.ArrayList;
import java.util.List;


public class AppController {

    private ArrayList<Article> articles;

    public AppController(){
    }

    public void setArticles(ArrayList<Article> articles) {

        //checking of passed parameter - Article objects with author and title (2 Strings) are counted
        int count = 0;
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            if (article.getTitle().length() > 0 && article.getAuthor().length() > 0) {
                count++;
            }
        }

        //list must not be empty and each Article object in the list must have author and title
        if (articles.size() != 0 && count == articles.size()) {
            this.articles = articles;
        }
    }


    public int getArticleCount(){
        if(this.articles == null){
            return 0;}
        else return articles.size();
    }
    public ArrayList<Article> getTopHeadlinesAustria(){
        return null;
    }
    public ArrayList<Article> getAllNewsBitcoin() {
        return null;
    }
    protected ArrayList<Article> filterList(String query, ArrayList<Article> articles){
        return null;
    }

    private static ArrayList<Article> generateMockList(){
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
    }

    public static ArrayList<Article> getMockList(){
        return generateMockList();
    }

}
