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
    /*public static final String HTML_EXTENSION = ".html";
    public static final String DIRECTORY_DOWNLOAD = "./download/";

    //public abstract int process(List<String> urls);

    public String saveUrl2File(String urlString) {
        InputStream is = null;
        OutputStream os = null;
        String fileName = "";
        //https://newsapi.org/v2/top-headlines?country=at&pageSize=100&apiKey=078504f64e1c4b6996e5a1b8e25798f7
        
        try {
            URL url4download = new URL(urlString);  // Convert string to URL

            is = url4download.openStream();
            fileName = urlString.substring(urlString.lastIndexOf('/') + 1); // extract filename

            if (fileName.isEmpty()) {
                fileName = url4download.getHost() + HTML_EXTENSION; // if no filename could be extracted use the URL host and .html extension
            }

            os = new FileOutputStream(DIRECTORY_DOWNLOAD + fileName);   // write to /download/<filename>

            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) != -1) {   // read byte for byte and write it to file
                os.write(b, 0, length);
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
        } finally {
            try {
                if(is != null)
                    is.close();
                if(os != null)
                    os.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        System.out.println(fileName);
        return fileName;
    }*/


}
