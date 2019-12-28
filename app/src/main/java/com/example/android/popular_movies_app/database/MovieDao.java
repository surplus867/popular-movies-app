package com.example.android.popular_movies_app.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import java.util.List;

import retrofit2.http.Query;
@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    LiveData<List<MovieEntry>> loadAllMovies();

    @Insert
    void insertMovie(MovieEntry movieEntry);


    @Delete
    void deleteMovie(MovieEntry movieEntry);


    @Query("SELECT * FROM movie WHERE movie_id = :movieId")
    LiveData<MovieEntry> loadMovieByMovieId(int movieId);


}
