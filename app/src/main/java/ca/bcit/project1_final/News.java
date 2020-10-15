package ca.bcit.project1_final;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class News {




    @SerializedName("source")
    @Expose

    private String source;
    public String getSource() {
        return source;
    }

    @SerializedName("author")
    @Expose

    private String author;
    public  String getAuthor() {
        return author;
    }

    @SerializedName("title")
    @Expose

    private String title;
    public String getTitle() {
        return title;
    }

    @SerializedName("description")
    @Expose
    private String description;
    public String getDescription() {
        return description;
    }

    @SerializedName("url")
    @Expose
    private String url;
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @SerializedName("urlToImage")
    @Expose
    private String urlToImage;
    public String getUrlToImage() {
        return urlToImage;
    }
    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    public String getPublishedAt() {
        return publishedAt;
    }
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @SerializedName("content")
    @Expose
    private String content;
    public String getContent() {
        return content;
    }


    protected News(String _author, String _title, String _description, String _url, String _urlToImage, String _publishedAt, String _content) {
        author = _author;
        title = _title;
        description = _description;
        url = _url;
        urlToImage = _urlToImage;
        publishedAt = _publishedAt;
        content = _content;
    }



    public static ArrayList<News> newsDetails = new ArrayList<News>();
//    public static News[] newsDetailsList = new News[];


}
