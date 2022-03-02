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

    private ArrayList<Article> generateMockList(){
        Article one =  new Article("Karl Marx", "Das Kapital");
        articles.add(one);
        Article two = new Article("Peter Molyneux", "Why i am a god");
        articles.add(two);
        Article three = new Article("Angela Merkel", "Wie ich die Raute erfand");
        articles.add(three);
        Article four = new Article("Donald Trump", "My orange hair");
        articles.add(four);
        Article five = new Article("Carl Barks", "Dagobert - Sein Leben, Seine Milliarden");
        articles.add(five);
        Article six = new Article("Elon Musk", "Bitcoin and Cryptocurrency");
        articles.add(six);
        Article seven = new Article("Satoshi Nakamoto", "How i invented blockchain and bitcoin");
        articles.add(seven);
        return articles;
    }

}
