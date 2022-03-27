package at.ac.fhcampuswien;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

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
        Article five = new Article("Elon Musk", "Bitcoin and Cryptocurrency");
        actual.add(five);
        Article six = new Article("Satoshi Nakamoto", "How i invented blockchain and bitcoin");
        actual.add(six);
    }

    //--------------Testing setArticles-------------
    @Test
    @DisplayName("setArticle working properly")
    public void testSetArticles_Scenario0(){
        //action
        ArrayList<Article> list = new ArrayList<>();
        list.add(one);
        ac.setArticles(list);
       ArrayList<Article> actualList = ac.getArticle();

        ArrayList<Article> expected = new ArrayList<>();
        expected.add(one);

        //assertion
        assertEquals(expected, actualList);
    }

    @Test
    @DisplayName("Article List not empty")
    public void testSetArticles_Scenario1(){
        /*
        test checked with ac.setArticles(test), i.e. empty list, and assertTrue (size == 0)
        in setArticles without if(size != 0) -> test ok
         */

        //action
        ac.setArticles(actual);

        //assertion
        assertTrue(ac.getArticle().size() > 0);
    }

    @Test
    @DisplayName("List of Articles with an author")
    public void testSetArticles_Scenario2(){
        //arrangement
        ac.setArticles(actual);
        int expectedCount = 6;

        //action
        int actualCount = 0;
        for (Article article : ac.getArticle()) {
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
        int expectedCount = 6;

        //action
        int actualCount = 0;
        for (Article article : ac.getArticle()) {
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
        int expectedCount = 6;

        //action
        int actualCount = 0;
        for (int i = 0; i < ac.getArticle().size(); i++) {
            Article article = ac.getArticle().get(i);
            if (article != null) {
                actualCount++;
            }
        }

        //assertion
        assertEquals(expectedCount, actualCount);
    }

    //testSetArticles_Scenario 5 and 6 not working anymore after changing setArticles()
    /*@Test
    @DisplayName("List of Article is null - NullPointerException")
    public void testSetArticles_Scenario5(){
        //https://www.appsdeveloperblog.com/junit-test-expected-exception-example/
        ArrayList<Article> mock2 = new ArrayList<>();

        try {
            ac.setArticles(mock2);
            fail();
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

    }*/

    //--------------Testing getFilterList-------------
    @Test
    @DisplayName("Correct Number of Strings(Titles)")
    public void testFilterList_Scenario1(){

        Article seven =  new Article("Karl Marx", "das Kapital");
        actual.add(seven);
        actual = ac.filterList("das",actual);

        assertEquals(2,actual.size());

       /* assertTrue(actual.size() > 0);
        assertTrue(actual.contains(one.getTitle()));
        assertEquals(actual.contains(one.getTitle()),actual.remove(one.getAuthor()));*/

    }
    @Test
    @DisplayName("String which is not in the Title")
    public void testFilterList_Scenario2(){

        actual = ac.filterList("Woe",actual);

        assertEquals(0,actual.size());

       /* assertTrue(actual.size() > 0);
        assertTrue(actual.contains(one.getTitle()));
        assertEquals(actual.contains(one.getTitle()),actual.remove(one.getAuthor()));*/
    }
    @Test
    @DisplayName("String which is in author and not in title")
    public void testFilterList_Scenario3(){

        actual = ac.filterList("Peter",actual);

        assertEquals(0,actual.size());

       /* assertTrue(actual.size() > 0);
        assertTrue(actual.contains(one.getTitle()));
        assertEquals(actual.contains(one.getTitle()),actual.remove(one.getAuthor()));*/

    }
    @Test
    @DisplayName("query is part of a String/Title")
    public void testFilterList_Scenario4(){

        actual = ac.filterList("er",actual);

        assertEquals(1,actual.size());

       /* assertTrue(actual.size() > 0);
        assertTrue(actual.contains(one.getTitle()));
        assertEquals(actual.contains(one.getTitle()),actual.remove(one.getAuthor()));*/

    }

    //-------------Testing getTopHeadlineAustria-------------
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
    @DisplayName("Did we get the right list?")
    public void testGetTopHeadlineAustria_Scenario2(){
        //arrangement
        actual = ac.getTopHeadlinesAustria();

        //action

        //assertion
        assertEquals(8, actual.size());
    }

    @Test
    @DisplayName("Does null return an empty list?")
    public void testGetTopHeadlineAustria_Scenario3(){
        //arrangement
        actual = ac.getTopHeadlinesAustria();

        //action

        //assertion
        assertNotNull(actual);
    }

    //--------------Testing getArticleCount-------------

    @Test
    @DisplayName("articleCount correct")
    public void testGetArticleCount_scenario1(){

        ac.setArticles(actual);
        int expectedCount = 6;
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

        ac.setArticles(test);

        int expectedCount = 0;
        int actualCount = ac.getArticleCount();

        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("articleCount is int")
    public void testGetArticleCount_scenario4(){

        assertTrue(Integer.class.isInstance(ac.getArticleCount()));
    }

    //--------------Testing getAllNewsBitcoin-------------
    @Test
    @DisplayName("bitcoin count")
    public void testGetAllNewsBitcoin(){

        actual = ac.filterList("bitcoin", actual);

        assertEquals(2, actual.size());
    }


}
