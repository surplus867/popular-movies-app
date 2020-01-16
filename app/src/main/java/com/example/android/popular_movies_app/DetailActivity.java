package com.example.android.popular_movies_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.popular_movies_app.ViewModel.AppExecutors;
import com.example.android.popular_movies_app.adapter.ReviewAdapter;
import com.example.android.popular_movies_app.adapter.TrailerAdapter;
import com.example.android.popular_movies_app.database.MovieDatabase;
import com.example.android.popular_movies_app.database.MovieEntry;
import com.example.android.popular_movies_app.databinding.ActivityDetailBinding;
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

    public static final String EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID";
    private static final String API_KEY = "";
    // Declare an ActivityDetailBinding called mDetailBinding
    private ActivityDetailBinding mDetailBinding;

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
    private boolean isChecked;

    int movie_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup action bar if present
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("MovieDetail");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        }

        mTrailerAdapter = new TrailerAdapter(this, mMovieTrailers);
        mDb = MovieDatabase.getInstance(getApplicationContext());


        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            mMovie = (Movie) intent.getSerializableExtra(EXTRA_MOVIE_ID);


            if (mMovie != null) {

                mMovieTitle.setText(mMovie.getTitle());
                mReleaseDate.setText(mMovie.getDate());
                mMoviePlot.setText(mMovie.getOverview());
                String poster = "https://image.tmdb.org/t/p/w500";
                Glide.with(this).load(poster + mMovie.
                        getPoserPath()).into(mMoviePoster);
                String userRatingText = mMovie.getAverage() + "/10";
                mVoteAverage.setText(userRatingText);
                movie_id = mMovie.getiD();

                FloatingActionButton FAB = findViewById(R.id.favorite_button);
                FAB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isChecked) {
                            saveFavorite();
                            Snackbar.make(view, "Already saved in favorites",
                                    Snackbar.LENGTH_LONG).show();
                        } else {
                            deleteFavorite(movie_id);
                            Snackbar.make(view, "Removed from Favorite",
                                    Snackbar.LENGTH_SHORT).show();

                        }
                    }

                    private void saveFavorite() {
                        mMovieEntry= new MovieEntry(mMovie.getiD(), mMovie.getTitle(), mMovie.getRating(),
                                mMovie.getPoserPath(), mMovie.getOverview());
                        AppExecutors.getInstance().disIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                mDb.movieDao().insertMovie(mMovieEntry);
                            }
                        });
                    }

                    private void deleteFavorite(final int movie_id){
                        AppExecutors.getInstance().disIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                mDb.movieDao().deleteMovie(mMovieEntry);
                            }
                        });
                    }

                });
            }

        }
    }
    private void getTrailers(int movie_id){
        MovieApi movieApi = RestClient.getMovieApi().create(MovieApi.class);
        Call<TrailerResponse> call = movieApi.getTrailers(movie_id, API_KEY);

        call.enqueue(new Callback<TrailerResponse>() {
            @Override
          public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                if (response.body() != null) {
                    mMovieTrailers = response.body().getTrailerList();
                    mTrailerRecyclerView.setAdapter(new TrailerAdapter(getApplicationContext(), mMovieTrailers));
                    mTrailerAdapter.updateData(mMovieTrailers);
                }


            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
                Toast.makeText(DetailActivity.this, R.string.no_results_found, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getReviews(int movieId) {
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


    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putSerializable("movie", mMovie);
        if (isOnline()) {
//            outState.putSerializable("movie_reviews", mMovieReviews);
//            outState.putSerializable("movie_trailers", mMovieTrailers);
        }

    }

    private boolean isOnline() {
        return true;
    }


}