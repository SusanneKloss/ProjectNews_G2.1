package at.ac.fhcampuswien.models;

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
        StringBuilder articleInfo = new StringBuilder();

        if (author != null) {articleInfo.append(author).append(" - ");}
        else {articleInfo.append("");}

        if (title != null) {articleInfo.append(title).append(System.lineSeparator()).append(System.lineSeparator());}
        else {articleInfo.append("").append(System.lineSeparator()).append(System.lineSeparator());}

        if (description != null){articleInfo.append(description).append(System.lineSeparator());}
        else {articleInfo.append("").append(System.lineSeparator());}

        if (url != null){articleInfo.append(url).append(System.lineSeparator());}
        else {articleInfo.append("").append(System.lineSeparator());}

        if (urlToImage != null){articleInfo.append(urlToImage).append(System.lineSeparator());}
        else {articleInfo.append("").append(System.lineSeparator()).append(System.lineSeparator());}

        if (publishedAt != null){articleInfo.append(publishedAt).append(System.lineSeparator()).append(System.lineSeparator());}
        else {articleInfo.append("").append(System.lineSeparator()).append(System.lineSeparator());}

        if (content != null){articleInfo.append(content).append(System.lineSeparator());}
        else {articleInfo.append("").append(System.lineSeparator());}

        return articleInfo.toString();
    }


}
