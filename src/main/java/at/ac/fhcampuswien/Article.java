package at.ac.fhcampuswien;

public class Article {
    private String author;
    private String title;

    public Article(String author, String title){
        this.author = author;
        this.title = title;

    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return author + " - " + title + System.lineSeparator();

    }
}
