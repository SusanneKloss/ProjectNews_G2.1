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
        actual = new ArrayList<>(); //hier nicht ArrayList<Articles> ???
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

    @Test
    @DisplayName("Is this a list of articles?")
    public void testGetTopHeadlineAustria_Scenario1(){
        //arrangement
        actual = ac.getTopHeadlinesAustria();
        boolean trueArticles = true;

        //action
        for (Object art : actual){
            if (!(art instanceof Article)){
                trueArticles = false; break;
            }
        }

        //assertion
        assertTrue(trueArticles);
    }

    @Test
    @DisplayName("Did we get the right one?")
    public void testGetTopHeadlineAustria_Scenario2(){
        //arrangement

        //action

        //assertion
    }

    @Test
    @DisplayName("Does null return an empty list?")
    public void testGetTopHeadlineAustria_Scenario3(){
        //arrangement

        //action

        //assertion
    }
}
