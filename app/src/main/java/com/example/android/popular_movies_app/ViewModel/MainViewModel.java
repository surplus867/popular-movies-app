package com.example.android.popular_movies_app.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.popular_movies_app.database.MovieDatabase;
import com.example.android.popular_movies_app.database.MovieEntry;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    // Constant for logging
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<MovieEntry>> mMovieEntry;

    public MainViewModel(Application application) {
        super(application);
        MovieDatabase movieDatabase = MovieDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        mMovieEntry = movieDatabase.movieDao().loadAllMovies();
    }
    public LiveData<List<MovieEntry>> getMovieEntry() {
        return mMovieEntry;
    }
}