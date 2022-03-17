package at.ac.fhcampuswien;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppControllerTest {
    private static AppController ac;
    ArrayList<Article> actual;
    Article one =  new Article("Karl Marx", "Das Kapital");

    @BeforeAll
    static void init(){
        ac = new AppController();
    }

    @BeforeEach
    void setup(){
        actual = new ArrayList<>();
    }

    @Test
    @DisplayName("Article List not empty")
    public void testSetArticles_Scenario1() {

        //action
        actual = AppController.getMockList();

        //assertion
        assertTrue(actual.size() > 0);
    }

    @Test
    @DisplayName("List of Articles with an author")
    public void testSetArticles_Scenario2(){
        //arrangement
        actual = AppController.getMockList();
        int expectedCount = actual.size();

        //action
        int actualCount = 0;
        for (Article article : actual) {
            if (article.getAuthor().length() > 0) {
                actualCount++;
            }
        }

        //assertion
        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("List of Articles with a title")
    public void testSetArticles_Scenario3(){
        //arrangement
        actual = AppController.getMockList();
        int expectedCount = actual.size();

        //action
        int actualCount = 0;
        for (int i = 0; i < actual.size(); i++) {
            Article article = actual.get(i);
            if (article.getTitle().length() > 0) {
                actualCount++;
            }
        }

        //assertion
        assertEquals(expectedCount, actualCount);
    }

    //--------------Testing getArticleCount-------------

    @Test
    @DisplayName("articleCount correct")
    public void testGetArticleCount_scenario1(){

        ArrayList<Article> mock2 = new ArrayList<>();
        Article one =  new Article("Karl Marx", "Das Kapital");
        mock2.add(one);
        Article two = new Article("Peter Molyneux", "Why i am a god");
        mock2.add(two);
        Article three = new Article("Angela Merkel", "Wie ich die Raute erfand");
        mock2.add(three);
        Article four = new Article("Donald Trump", "My orange hair");
        mock2.add(four);


        ac.setArticles(mock2);
        int expectedCount = 4;
        int actualCount = ac.getArticleCount();

        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("articleCount is null")
    public void testGetArticleCount_scenario2(){

        int expectedCount = 0;
        int actualCount = ac.getArticleCount();

        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("articleCount is 0")
    public void testGetArticleCount_scenario3(){

        ac.setArticles(actual);
        int expectedCount = 0;
        int actualCount = ac.getArticleCount();

        assertEquals(expectedCount, actualCount);
    }


    @Test
    @DisplayName("articleCount is int")
    public void testGetArticleCount_scenario4(){

        assertTrue(Integer.class.isInstance(ac.getArticleCount()));
    }







}
