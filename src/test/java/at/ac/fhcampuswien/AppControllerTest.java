package at.ac.fhcampuswien;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Test
    @DisplayName("Correct Number of Strings(Titles)")
    public void testFilterList_Scenario1(){

        Article one =  new Article("Karl Marx", "Das Kapital");
        actual.add(one);
        Article two = new Article("Peter Molyneux", "Why i am a god");
        actual.add(two);
        Article three = new Article("Angela Merkel", "Wie ich die Raute erfand");
        actual.add(three);
        Article four =  new Article("Karl Marx", "das Kapital");
        actual.add(four);
        actual = ac.filterList("das",actual);

        assertEquals(2,actual.size());

       /* assertTrue(actual.size() > 0);
        assertTrue(actual.contains(one.getTitle()));
        assertEquals(actual.contains(one.getTitle()),actual.remove(one.getAuthor()));*/


    }
    @Test
    @DisplayName("String which is not in the Title")
    public void testFilterList_Scenario2(){

        Article one =  new Article("Karl Marx", "Das Kapital");
        actual.add(one);
        Article two = new Article("Peter Molyneux", "Why i am a god");
        actual.add(two);
        Article three = new Article("Angela Merkel", "Wie ich die Raute erfand");
        actual.add(three);
        Article four =  new Article("Karl Marx", "das Kapital");
        actual.add(four);
        actual = ac.filterList("Woe",actual);

        assertEquals(0,actual.size());

       /* assertTrue(actual.size() > 0);
        assertTrue(actual.contains(one.getTitle()));
        assertEquals(actual.contains(one.getTitle()),actual.remove(one.getAuthor()));*/


    }
    @Test
    @DisplayName("String which is in author and not in title")
    public void testFilterList_Scenario3(){

        Article one =  new Article("Karl Marx", "Das Kapital");
        actual.add(one);
        Article two = new Article("Peter Molyneux", "Why i am a god");
        actual.add(two);
        Article three = new Article("Angela Merkel", "Wie ich die Raute erfand");
        actual.add(three);
        Article four =  new Article("Karl Marx", "das Kapital");
        actual.add(four);
        actual = ac.filterList("Peter",actual);

        assertEquals(0,actual.size());

       /* assertTrue(actual.size() > 0);
        assertTrue(actual.contains(one.getTitle()));
        assertEquals(actual.contains(one.getTitle()),actual.remove(one.getAuthor()));*/


    }
    @Test
    @DisplayName("query is part of a String/Title")
    public void testFilterList_Scenario4(){

        Article one =  new Article("Karl Marx", "Das Kapital");
        actual.add(one);
        Article two = new Article("Peter Molyneux", "Why i am a god");
        actual.add(two);
        Article three = new Article("Angela Merkel", "Wie ich die Raute erfand");
        actual.add(three);
        Article four =  new Article("Karl Marx", "das Kapital");
        actual.add(four);
        actual = ac.filterList("er",actual);

        assertEquals(1,actual.size());

       /* assertTrue(actual.size() > 0);
        assertTrue(actual.contains(one.getTitle()));
        assertEquals(actual.contains(one.getTitle()),actual.remove(one.getAuthor()));*/


    }
}
