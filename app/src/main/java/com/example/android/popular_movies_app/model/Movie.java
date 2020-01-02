package com.example.android.popular_movies_app.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Movie implements Serializable {


    public static final String POSTER_PATH = "poster_path";
    public static final String TITLE = "title";
    public static final String OVERVIEW = "overview";
    public static final String VOTE_AVERAGE = "vote_average";
    public static final String RELEASE_DATE = "release_date";
    public static final String RATING = "rating";
    public static final String ID = "id";
    public static final String REVIEWS = "content";

    @SerializedName(POSTER_PATH)
    private String poserPath;

    @SerializedName(TITLE)
    private String title;

    @SerializedName(OVERVIEW)
    private String overview;

    @SerializedName(VOTE_AVERAGE)
    private Double average;

    @SerializedName(RELEASE_DATE)
    private String date;

    @SerializedName(RATING)
    private String rating;

    @SerializedName(ID)
    private int iD;

    @SerializedName(REVIEWS)
    private String reviews;

    public Movie() {
    }

    public static String getPosterPath() {
        return POSTER_PATH;
    }

    public static String getTITLE() {
        return TITLE;
    }

    public static String getOVERVIEW() {
        return OVERVIEW;
    }

    public static String getReleaseDate() {
        return RELEASE_DATE;
    }

    public static String getRATING() {
        return RATING;
    }

    public static String getID() {
        return ID;
    }

    public static String getREVIEWS() {
        return REVIEWS;
    }

    public String getPoserPath() {
        return poserPath;
    }

    public void setPoserPath(String poserPath) {
        this.poserPath = poserPath;
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

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }
}
