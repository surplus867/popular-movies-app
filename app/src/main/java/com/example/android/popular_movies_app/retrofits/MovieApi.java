package com.example.android.popular_movies_app.retrofits;

import com.example.android.popular_movies_app.model.MoviesResponse;
import com.example.android.popular_movies_app.model.ReviewResponse;
import com.example.android.popular_movies_app.model.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {


    @GET("movie/{sort}")
    Call<MoviesResponse> getMovies(@Path("sort")String sort,@Query("api_key")String apiKey);

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getTrailers(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/reviews")
    Call<ReviewResponse> getReviews(@Path("movie_id") int movieId, @Query("api_key") String apiKey);


}

