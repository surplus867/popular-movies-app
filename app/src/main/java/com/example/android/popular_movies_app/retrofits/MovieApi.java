package com.example.android.popular_movies_app.retrofits;

import com.example.android.popular_movies_app.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("movie/{sort}")
    Call<MoviesResponse> getMovies(@Path("sort")String sort,@Query("api_key")String apiKey);

    /*@GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key")String apiKey);
*/

   /* @GET("search/movie?query=sort_by=popularity.desc&api_key=" + API_KEY)
    void getPopularMovieResults(Callback<MoviesResponse> callback);

    @GET("discover/movie?sort_by=vote_average.desc&api_key=" + API_KEY)
    void getTopRatedMovieResults(Callback<MoviesResponse> callback);*/

}
