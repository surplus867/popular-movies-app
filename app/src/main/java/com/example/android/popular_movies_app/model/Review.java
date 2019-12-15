package com.example.android.popular_movies_app.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable {

    public static final String AUTHOR = "author";
    public static final String CONTENT = "content";
    public static final String URL = "url";
    public static final String ID = "id";

    @SerializedName(AUTHOR)
    private String author;

    @SerializedName(CONTENT)
    private String content;

    @SerializedName(URL)
    private String url;

    @SerializedName(ID)
    private String iD;

    public Review() {

    }

    public static String getAUTHOR() {
        return AUTHOR;
    }

    public static String getCONTENT() {
        return CONTENT;
    }

    public static String getURL() {
        return URL;
    }

    public static String getID() {
        return ID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }
}