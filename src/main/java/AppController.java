import java.util.ArrayList;
import java.util.List;

public class AppController {

    private ArrayList<Article> articles;
    private int articleCounter;

    public AppController(){
        articleCounter++;
    }

    public void setArticles(List<Article> articles){
    }
    public int getArticleCount(){
        return articleCounter;
    }
    public ArrayList<Article> getTopHeadlinesAustria(){
    }
    public ArrayList<Article> getAllNewsBitcoin(){
    }
    protected ArrayList<Article> filterList(String query, ArrayList<Article> articles){
    }
    private ArrayList<Article> generateMockList(){
    }

}
