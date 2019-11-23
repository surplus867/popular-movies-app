package com.example.android.popular_movies_app.retrofits;

import com.example.android.popular_movies_app.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") int page,
                                          @Query("api_key")String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") int page,
                                           @Query("api_key")String apiKey);
}
