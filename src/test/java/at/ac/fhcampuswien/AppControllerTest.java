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
    ArrayList<Article> actual;  //for tests
    ArrayList<Article> test;    //checking for failed tests
    Article one =  new Article("Karl Marx", "Das Kapital");

    @BeforeAll
    static void init(){
        ac = new AppController();
    }

    @BeforeEach
    void setup(){
        test = new ArrayList<>();
        actual = new ArrayList<>();

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
}
