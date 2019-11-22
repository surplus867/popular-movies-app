package com.example.android.popular_movies_app.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    public static final String TITLE = "title";
    public static final String POSTER_PATH = "poster_path";
    public static final String RELEASE_DATE = "release_date";
    public static final String RATE = "Rate";
    public static final String ID = "id";
    public static final String OVERVIEW = "overview";


    @SerializedName(POSTER_PATH)
    private String moviePoster;

    @SerializedName(TITLE)
    private String title;

    @SerializedName(OVERVIEW)
    private String overview;

    @SerializedName(RELEASE_DATE)
    private String date;

    @SerializedName(ID)
    private String id;

    @SerializedName(RATE)
    private String rate;

    public static String getTITLE() {
        return TITLE;
    }

    public static String getPosterPath() {
        return POSTER_PATH;
    }

    public static String getReleaseDate() {
        return RELEASE_DATE;
    }

    public static String getRATE() {
        return RATE;
    }

    public static String getID() {
        return ID;
    }

    public static String getOVERVIEW() {
        return OVERVIEW;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Movie(){

    }
}