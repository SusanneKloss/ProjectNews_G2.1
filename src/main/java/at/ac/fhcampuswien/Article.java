package at.ac.fhcampuswien;

public class Article {
    private String author;
    private String title;

    //SOURCE??
    private StringBuilder source;
    private String id;
    private String name;

    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public Article(String author, String title){
        this.author = author;
        this.title = title;

    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getUrl(){
        return url;
    }

    public String getUrlToImage(){
        return urlToImage;
    }

    public String getPublishedAt(){
        return publishedAt;
    }

    public String getContent(){
        return content;
    }

    //SOURCE??
    public StringBuilder getSource(){
        return source;
    }

    @Override
    public String toString() {
        return author + " - " + title + System.lineSeparator();

    }
}
