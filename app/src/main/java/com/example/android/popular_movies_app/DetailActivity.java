package com.example.android.popular_movies_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.popular_movies_app.ViewModel.MainViewModel;
import com.example.android.popular_movies_app.adapter.ReviewAdapter;
import com.example.android.popular_movies_app.adapter.TrailerAdapter;
import com.example.android.popular_movies_app.database.MovieDatabase;
import com.example.android.popular_movies_app.database.MovieEntry;
import com.example.android.popular_movies_app.model.Movie;
import com.example.android.popular_movies_app.model.Review;
import com.example.android.popular_movies_app.model.ReviewResponse;
import com.example.android.popular_movies_app.model.Trailer;
import com.example.android.popular_movies_app.model.TrailerResponse;
import com.example.android.popular_movies_app.retrofits.MovieApi;
import com.example.android.popular_movies_app.retrofits.RestClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    public static final String ARG_Movie = "DETAIL_MOVIE";
    private static final String API_KEY = "";

    @BindView(R.id.posterImageView)
    ImageView mMoviePoster;
    @BindView(R.id.titleTextView)
    TextView mMovieTitle;
    @BindView(R.id.ratingTextView)
    TextView mVoteAverage;
    @BindView(R.id.releaseDateTextView)
    TextView mReleaseDate;
    @BindView(R.id.tv_plot_synopsis)
    TextView mMoviePlot;
    @BindView(R.id.favorite_button)
    FloatingActionButton mFavorites;
    @BindView(R.id.movie_activity_trailer_label)
    TextView mMovieTrailerLabel;
    @BindView(R.id.detail_reviews)
    TextView mReviewsLabel;

    @BindView(R.id.rv_movie_trailers)
    RecyclerView mTrailerRecyclerView;

    private TrailerAdapter mTrailerAdapter;
    private ReviewAdapter mReviewAdapter;
    private List<Trailer> mMovieTrailers;
    private List<Review> mMovieReviews;
    private MovieDatabase mDb;
    private MovieEntry mMovieEntry;
    private Movie mMovie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("MovieDetail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTrailerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTrailerRecyclerView.setNestedScrollingEnabled(false);


        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            Movie movie = (Movie) intent.getSerializableExtra(ARG_Movie);
            mDb = MovieDatabase.getInstance(getApplicationContext());


            if (movie!= null) {

                mMovieTitle.setText(mMovie.getTitle());
                mReleaseDate.setText(mMovie.getDate());
                mMoviePlot.setText(mMovie.getOverview());
                String poster = "http://image.tmdb.org/t/p/w500";
                Glide.with(this).load(poster + mMovie.
                        getMoviePoster()).into(mMoviePoster);
                String userRatingText = mMovie.getVoteAverage() + "/10";
                mVoteAverage.setText(userRatingText);

                FloatingActionButton FAB = findViewById(R.id.favorite_button);
                FAB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isChecked) {
                            Snackbar.make(view, "Already saved in favorites", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }else{

                        }

            });

            }


        private void getTrailers (int movieId){
                RestClient restClient = new RestClient();
                MovieApi movieApi = RestClient.getMovieApi().create(MovieApi.class);
                Call<TrailerResponse> call = movieApi.getTrailers(movieId, API_KEY);

                call.enqueue(new Callback<TrailerResponse>() {
                    @Override
                    public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                        mMovieTrailers = response.body().getTrailerList();
                        //populateTrailers(mMovieTrailers);
                    }

                    @Override
                    public void onFailure(Call<TrailerResponse> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                        Toast.makeText(DetailActivity.this, R.string.no_results_found, Toast.LENGTH_SHORT).show();

                    }
                });
            }

            private void getReviews( int movieId) {
                MovieApi movieApi = RestClient.getMovieApi().create(MovieApi.class);
                Call<ReviewResponse> call = movieApi.getReviews(movieId, API_KEY);

                call.enqueue(new Callback<ReviewResponse>() {
                    @Override
                    public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                        assert response.body() != null;
                        mMovieReviews = response.body().getReviews();
                        //populateReviews(mMovieReviews);

                    }

                    @Override
                    public void onFailure(Call<ReviewResponse> call, Throwable t) {
                        Toast.makeText(DetailActivity.this, R.string.no_results_found, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }

    private void initViews() {
        mMovieTrailers = new ArrayList<>();
        mTrailerAdapter = new TrailerAdapter(this, mMovieTrailers);

    }


    @Override
        protected void onSaveInstanceState (Bundle outState){
            super.onSaveInstanceState(outState);
            outState.putSerializable("movie", mMovie);
            if (isOnline()) {
                outState.putSerializable("movie_reviews", mMovieReviews);
                outState.putSerializable("movie_trailers", mMovieTrailers);
            }

        }
    }
}


/*

    private void getTrailers(int movieId) {
        MovieApi movieApi = RestClient.getMovieApi().create(MovieApi.class);
        Call<TrailerResponse> call = movieApi.getTrailers(movieId, API_KEY);

        call.enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                mMovieTrailers = response.body().getTrailerList();
                populateTrailers(mMovieTrailers);
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
                Toast.makeText(DetailActivity.this, R.string.no_results_found, Toast.LENGTH_SHORT).show();

            }
        });
    }

    }

    private void getReviews() {
        MovieApi movieApi = RestClient.getMovieApi().create(MovieApi.class);
        Call<ReviewResponse> call = movieApi.getReviews(movieId, API_KEY);

        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                assert response.body() != null;
                mMovieReviews = response.body().getReviews();
                populateReviews(mMovieReviews);

            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                Toast.makeText(DetailActivity.this, R.string.no_results_found, Toast.LENGTH_SHORT).show();

            }
        });

    }
    }*/

