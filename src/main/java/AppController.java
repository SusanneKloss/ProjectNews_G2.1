import java.util.ArrayList;
import java.util.List;

public class AppController {

    private List<Article> articles;
    private int articleCounter;

    public AppController(){
        articleCounter++;
    }

    public void setArticles(List<Article> articles){

    }
    public int getArticleCount(){
        return articles.size();
    }
    public List<Article> getTopHeadlinesAustria(){
    }
    public List<Article> getAllNewsBitcoin(){
    }
    protected List<Article> filterList(String query, List<Article> articles){
    }
    private List<Article> generateMockList(){
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
    }

}
