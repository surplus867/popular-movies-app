package com.example.android.popular_movies_app.retrofits;

import com.example.android.popular_movies_app.model.MoviesResponse;
import com.example.android.popular_movies_app.model.ReviewResponse;
import com.example.android.popular_movies_app.model.TrailerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    String API_KEY = "";


    @GET("/3/discover/movie?sort_by=popularity.desc&api_key=" + API_KEY)
    void getPopularMovies(Callback<MoviesResponse> callback);

    @GET("/3/discover/movie?sort_by=vote_average.desc&api_key=" + API_KEY)
    void getTopRatedMovies(Callback<MoviesResponse> callback);

    @GET("/3/movie/{id}/reviews?api_key=" + API_KEY)
    void getReviews(@Path("id") int id, Callback<ReviewResponse> reviewResponseCallback);

    @GET("/3/movie/{id}/videos?api_key=" + API_KEY)
    void getTrailers(@Path("id") int id, Callback<TrailerResponse> trailerResponseCallback);

    /*@GET("movie/{sort}")
    Call<MoviesResponse> getMovies(@Path("sort")String sort,@Query("api_key")String apiKey);

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<TrailerResponse> getTrailers(@Path("id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Call<ReviewResponse> getReviews(@Path("id") int movieId, @Query("api_key") String apiKey);*/


}
