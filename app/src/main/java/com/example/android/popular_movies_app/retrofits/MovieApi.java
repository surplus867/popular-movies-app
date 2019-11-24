package com.example.android.popular_movies_app.retrofits;

import com.example.android.popular_movies_app.model.MoviesResponse;

import retrofit2.Callback;
import retrofit2.http.GET;

public interface MovieApi {

    String API_KEY = "";

    @GET("/3/search/movie?query=sort_by=popularity.desc&api_key=" + API_KEY)
    void getPopularMovieResults(Callback<MoviesResponse> callback);

    @GET("/3/discover/movie?sort_by=vote_average.desc&api_key=" + API_KEY)
    void getTopRatedMovieResults(Callback<MoviesResponse> callback);

}
