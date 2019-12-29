package com.example.android.popular_movies_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.popular_movies_app.ViewModel.MainViewModel;
import com.example.android.popular_movies_app.adapter.MovieAdapter;
import com.example.android.popular_movies_app.database.MovieEntry;
import com.example.android.popular_movies_app.model.Movie;
import com.example.android.popular_movies_app.model.MoviesResponse;
import com.example.android.popular_movies_app.retrofits.MovieApi;
import com.example.android.popular_movies_app.retrofits.RestClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String API_KEY = "";
    public static final String KEY_MOVIES_RESPONSE = "KEY_MOVIES_RESPONSE";
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_error_message)
    TextView mErrorMessage;
    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;

    private GridLayoutManager mLayoutManager;

    private MainViewModel mainViewModel;

    private MovieAdapter mAdapter;

    private List<Movie> mMovies = new ArrayList<>();

    private MoviesResponse mMoviesResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        // Check if we have data to display (after rotation)
        if (savedInstanceState != null) {
            mMoviesResponse = (MoviesResponse) savedInstanceState.getSerializable(KEY_MOVIES_RESPONSE);
            mMovies = mMoviesResponse.getMovies();
        } else {
            handleResults();
        }

        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new GridLayoutManager(this, numberOfColumns());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MovieAdapter(this, mMovies);
        mRecyclerView.setAdapter(mAdapter);

        observeFavoriteMovies();

}

    private void handleResults() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
        mErrorMessage.setVisibility(View.GONE);

        Retrofit retrofit = RestClient.getMovieApi();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<MoviesResponse> call = movieApi.getPopularMovies("api_key....");
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                MoviesResponse moviesResponse = response.body();
                List<Movie> movies = null;
                if (moviesResponse != null) {
                    movies = moviesResponse.getMovies();
                }
                mLoadingIndicator.setVisibility(View.GONE);
                mAdapter.updateData(movies);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.no_results_found, Toast.LENGTH_SHORT).show();
                mLoadingIndicator.setVisibility(View.GONE);
                mErrorMessage.setVisibility(View.VISIBLE);
            }
        });

    }

    private void handleResults2() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
        mErrorMessage.setVisibility(View.GONE);

        Retrofit retrofit = RestClient.getMovieApi();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<MoviesResponse> call = movieApi.getTopRatedMovies("api_key.....");
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                MoviesResponse moviesResponse = response.body();
                List<Movie> movies = null;
                if (moviesResponse != null) {
                    movies = moviesResponse.getMovies();
                }
                mLoadingIndicator.setVisibility(View.GONE);
                mAdapter.updateData(movies);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.no_results_found, Toast.LENGTH_SHORT).show();
                mLoadingIndicator.setVisibility(View.GONE);
                mErrorMessage.setVisibility(View.VISIBLE);
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d(TAG, "Preferences updated");
        checkSortOrder();
    }

    private void checkSortOrder() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sortOrder = preferences.getString(this.getString(R.string.pref_sort_order_key),
                this.getString(R.string.pref_most_popular)
        );
        if (sortOrder.equals(this.getString(R.string.pref_most_popular))) {
            Log.d(TAG, "Sorting by most popular");
            handleResults();
        } else if (sortOrder.equals(this.getString(R.string.favorites))) {
            Log.d(TAG, "Sorting by favorite");
            handleResults2();
        } else{
            Log.d(TAG, "Sorting by vote average");
            handleResults();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMovies.isEmpty()) {
            checkSortOrder();
        } else {
            checkSortOrder();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_MOVIES_RESPONSE, mMoviesResponse);
    }

    private void observeFavoriteMovies(){
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getFavoriteMovies().observe(this, new Observer<List<MovieEntry>>() {
            @Override
            public void onChanged(List<MovieEntry> movieEntries) {
                List<Movie> mMovies = new ArrayList<>();
                for (MovieEntry entry : movieEntries) {
                    Movie movie = new Movie();
                    movie.setiD(entry.getMovieId());
                    movie.setOverview(entry.getOverview());
                    movie.setTitle(entry.getOriginalTitle());
                    movie.setMoviePoster(entry.getPosterPath());
                    movie.setRating(entry.getOverview());

                    mMovies.add(movie);
                }
                mAdapter.updateData(mMovies);

            }
        });
    }

    //here you can dynamically calculate the number of columns and the layout will adapt to the screen size and orientation
    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // You can Change this divider to adjust the size of the item
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int nColumns = width/ widthDivider;
        if(nColumns < 2) return 2; //to keep the grid aspect
        return nColumns;

    }

    }