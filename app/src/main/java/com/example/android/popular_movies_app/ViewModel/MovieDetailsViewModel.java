package com.example.android.popular_movies_app.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.popular_movies_app.database.MovieDatabase;
import com.example.android.popular_movies_app.database.MovieEntry;

public class MovieDetailsViewModel extends ViewModel {

    private LiveData<MovieEntry> mMovieEntry;

    public MovieDetailsViewModel(MovieDatabase movieDatabase, int movieId) {
        mMovieEntry = movieDatabase.movieDao().loadMovieByMovieId(movieId);
    }

    public LiveData<MovieEntry> getMovieEntry() {
        return mMovieEntry;
    }
}
