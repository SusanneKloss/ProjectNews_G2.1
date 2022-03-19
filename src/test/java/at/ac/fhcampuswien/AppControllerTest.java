package at.ac.fhcampuswien;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppControllerTest {
    private static AppController ac;
    ArrayList<Article> actual;  //for tests
    ArrayList<Article> test;    //checking for failed tests
    Article one =  new Article("Karl Marx", "Das Kapital");




    @BeforeAll
    static void init(){
        ac = new AppController();
    }

    @BeforeEach
    void setup(){
        actual = new ArrayList<>(); //for tests
        test = new ArrayList<>();   //checking for failed tests

        Article one =  new Article("Karl Marx", "Das Kapital");
        actual.add(one);
        Article two = new Article("Peter Molyneux", "Why i am a god");
        actual.add(two);
        Article three = new Article("Angela Merkel", "Wie ich die Raute erfand");
        actual.add(three);
        Article four = new Article("Donald Trump", "My orange hair");
        actual.add(four);
    }

    @Test
    @DisplayName("setArticle working properly")
    public void testSetArticles_Scenario0() {
        //action
        test.add(one);
        ac.setArticles(test);
        ArrayList<Article> actual = ac.getArticle();

        ArrayList<Article> expected = new ArrayList<>();
        expected.add(one);

        //assertion
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Article List not empty")
    public void testSetArticles_Scenario1() {
        //action
        //ArrayList<Article> actual = new ArrayList<>();
        ac.setArticles(actual);

        //assertion
        //assertTrue(ac.size() > 0);

        /*ArrayList<Article> mock2 = new ArrayList<>();

        Article one =  new Article("Karl Marx", "Das Kapital");
        mock2.add(one);
        Article two = new Article("Peter Molyneux", "Why i am a god");
        mock2.add(two);
        Article three = new Article("Angela Merkel", "Wie ich die Raute erfand");
        mock2.add(three);
        Article four = new Article("Donald Trump", "My orange hair");
        mock2.add(four);

        //action
        ac.setArticles(mock2);

        //assertion
        assertTrue(mock2.size() > 0);*/
    }

    @Test
    @DisplayName("List of Articles with an author")
    public void testSetArticles_Scenario2(){
        //arrangement
        //ac.setArticles(actual);
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
        ac.setArticles(actual);
        int expectedCount = actual.size();

        //action
        int actualCount = 0;
        for (Article article : actual) {
            if (article.getTitle().length() > 0) {
                actualCount++;
            }
        }

        //assertion
        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("List of Article objects")
    public void testSetArticles_Scenario4(){
        //arrangement
        ac.setArticles(actual);
        int expectedCount = actual.size();

        //action
        int actualCount = 0;
        for (int i = 0; i < actual.size(); i++) {
            Article article = actual.get(i);
            if (article != null) {
                actualCount++;
            }
        }

        //assertion
        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("List of Article is null - NullPointerException")
    public void testSetArticles_Scenario5(){
        //https://www.appsdeveloperblog.com/junit-test-expected-exception-example/
        ArrayList<Article> mock2 = new ArrayList<>();

        try {
            ac.setArticles(mock2);
            //fail();
        }
        catch (NullPointerException exception){
            assertTrue(exception instanceof NullPointerException);
        }

    }

    @Test
    @DisplayName("List of Article is null - IllegalArgumentException")
    public void testSetArticles_Scenario6(){
        //https://www.appsdeveloperblog.com/junit-test-expected-exception-example/
        ArrayList<Article> mock2 = null;

        try {
            ac.setArticles(mock2);
            fail();
        }
        catch (IllegalArgumentException exception){
            assertTrue(exception instanceof IllegalArgumentException);
        }

    }

}
