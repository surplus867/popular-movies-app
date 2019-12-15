package com.example.android.popular_movies_app.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {
    public static final String RESULT_COUNT = "total_results";
    public static final String RESULTS = "results";

    @SerializedName(RESULT_COUNT)
    private int resultCount;

    @SerializedName(RESULTS)
    private List<Review> reviews;

    public ReviewResponse() {

    }

    public static String getResultCount() {
        return RESULT_COUNT;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public static String getRESULTS() {
        return RESULTS;
    }
}