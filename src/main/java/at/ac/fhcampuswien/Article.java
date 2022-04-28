package at.ac.fhcampuswien;

public class Article {
    private String author;
    private String title;

    private NewsSource source;

    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public Article(String author, String title){
        this.author = author;
        this.title = title;
    }

    public Article(String author, String title, NewsSource source, String description, String url,
                   String urlToImage, String publishedAt, String content){

        this.author = author;
        this.title = title;
        this.source = source;
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

    public NewsSource getSource() {return source; }

    public String getDescription() {return description;}

    public String getUrl() {return url;}

    public String urlToImage() {return urlToImage;}

    public String publishedAT() {return publishedAt;}

    public String getContent() {return content;}

    @Override
    public String toString() {
        return author + " - " + title + System.lineSeparator() + System.lineSeparator() +
                description + " - " + System.lineSeparator() + url + System.lineSeparator() +
                urlToImage + " - " + System.lineSeparator() + System.lineSeparator() + publishedAt + System.lineSeparator() + System.lineSeparator() +
                content + System.lineSeparator();

    }


}
