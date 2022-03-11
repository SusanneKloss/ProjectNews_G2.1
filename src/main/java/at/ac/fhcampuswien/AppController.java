package at.ac.fhcampuswien;

import java.util.ArrayList;
import java.util.List;


public class AppController {

    private ArrayList<Article> articles;

    public AppController(){
    }

    public void setArticles(ArrayList<Article> articles){

    }
    public int getArticleCount(){
        return articles.size();
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
        return mock;
    }

    public static ArrayList<Article> getMockList(){
        return generateMockList();
    }

}
