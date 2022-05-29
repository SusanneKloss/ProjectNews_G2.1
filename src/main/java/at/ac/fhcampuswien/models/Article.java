package at.ac.fhcampuswien.models;

import at.ac.fhcampuswien.controllers.NewsApiException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Article {
    private String author;
    private String title;

    private NewsSource source;

    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public Article(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Article(String author, String title, NewsSource source, String description, String url,
                   String urlToImage, String publishedAt, String content) {

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

    public NewsSource getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String urlToImage() {
        return urlToImage;
    }

    public String publishedAT() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void articleInfoBuild(String s, StringBuilder articleInfo) {

        if (s != null) {
            articleInfo.append(s).append(System.lineSeparator());
        } else {
            articleInfo.append("").append(System.lineSeparator());
        }
    }

    @Override
    public String toString() {
        StringBuilder articleInfo = new StringBuilder();

        articleInfoBuild(title, articleInfo);
        articleInfo.append(System.lineSeparator()); articleInfo.append(System.lineSeparator());
        articleInfoBuild(description, articleInfo);
        articleInfoBuild(author, articleInfo);
        articleInfo.append(System.lineSeparator());
        articleInfoBuild(url, articleInfo);
        articleInfoBuild(urlToImage, articleInfo);
        articleInfoBuild(publishedAt, articleInfo);
        articleInfo.append(System.lineSeparator());
        articleInfoBuild(content, articleInfo);
        articleInfo.append(System.lineSeparator());
        articleInfoBuild(source.toString(), articleInfo);

        return articleInfo.toString();
    }
}
