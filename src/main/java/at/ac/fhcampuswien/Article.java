package at.ac.fhcampuswien;

public class Article {
    private String author;
    private String title;

    //SOURCE??
    private NewsSource source;
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
    public Article(NewsSource source, String author, String title, String description, String url, String urlToImage, String publishedAt, String content){
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
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

    @Override
    public String toString() {
        return author + " - " + title + System.lineSeparator();

    }
}
