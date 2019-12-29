package com.example.android.popular_movies_app.ViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.popular_movies_app.database.MovieDatabase;

public class MovieDetailsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MovieDatabase mDb;
    private final int mMovieId;

    public MovieDetailsViewModelFactory(MovieDatabase movieDatabase, int moveId) {
        mDb = movieDatabase;
        mMovieId = moveId;
    }
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T)new MovieDetailsViewModel(mDb, mMovieId);
    }
}
