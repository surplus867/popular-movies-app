package com.example.android.popular_movies_app;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.popular_movies_app.adapter.MovieAdapter;
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

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "";
    public static final String KEY_MOVIES_RESPONSE = "KEY_MOVIES_RESPONSE";
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_error_message)
    TextView mErrorMessage;
    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;

    String query = "popular";

    private GridLayoutManager mLayoutManager;

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
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_MOVIES_RESPONSE, mMoviesResponse);
    }

    private void handleResults() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
        mErrorMessage.setVisibility(View.GONE);

        Retrofit retrofit = RestClient.getMovieApi();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<MoviesResponse> call = movieApi.getMovies("popular", "api_key..........");
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
                Toast.makeText(MainActivity.this, "No results found", Toast.LENGTH_SHORT).show();
                mLoadingIndicator.setVisibility(View.GONE);
                mErrorMessage.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_popular) {
            query = "popular";
            handleResults();
            return true;
        }

        if (id == R.id.action_top_rated) {
            query = "top_rated";
            handleResults();
            return true;
        }

        if (id == R.id.favorite_movies) {
            query = "favorites";
            handleResults();
            return true;
        }


        return super.onOptionsItemSelected(item);

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