package com.example.android.popular_movies_app.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MoviesResponse implements Serializable {
    public static final String TAG = MoviesResponse.class.getSimpleName();

    public static final String PROPERTY_RESULT_COUNT = "total_results";
    public static final String PROPERTY_RESULTS = "results";

    @SerializedName(PROPERTY_RESULT_COUNT)
    private int resultCount;

    @SerializedName(PROPERTY_RESULTS)
    private List<Movie> movies;

    public MoviesResponse() {

    }

    public List<Movie> getMovies() {
        return movies;

    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }
}