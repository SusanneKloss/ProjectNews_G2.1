import java.util.List;

public class AppController {

    privat List<Article> articles;
    private int articleCounter;

    public AppController(){
        articleCounter++;
    }

    public void setArticles(List<Article> articles){
    }
    public int getArticleCount(){
        return articleCounter;
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
