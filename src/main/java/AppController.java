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
    }

}
