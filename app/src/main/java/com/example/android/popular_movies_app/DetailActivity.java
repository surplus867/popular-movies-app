package com.example.android.popular_movies_app;

import android.content.Intent;
import android.net.DnsResolver;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.popular_movies_app.adapter.ReviewAdapter;
import com.example.android.popular_movies_app.adapter.TrailerAdapter;
import com.example.android.popular_movies_app.model.Movie;
import com.example.android.popular_movies_app.model.Review;
import com.example.android.popular_movies_app.model.ReviewResponse;
import com.example.android.popular_movies_app.model.Trailer;
import com.example.android.popular_movies_app.retrofits.RestClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;

public class DetailActivity extends AppCompatActivity {

    public static final String ARG_Movie = "DETAIL_MOVIE";

    @BindView(R.id.posterImageView) ImageView mMoviePoster;
    @BindView(R.id.titleTextView) TextView mMovieTitle;
    @BindView(R.id.ratingTextView)TextView mVoteAverage;
    @BindView(R.id.releaseDateTextView) TextView mReleaseDate;
    @BindView(R.id.tv_plot_synopsis) TextView mMoviePlot;
    @BindView(R.id.movie_activity_favorite) FloatingActionButton mFavorites;
    @BindView(R.id.movie_activity_trailer_label) TextView mMovieTrailerLabel;
    @BindView(R.id.movie_activity_trailer_label) TextView mReviewsLabel;

    @BindView(R.id.rv_movie_trailers) RecyclerView mTrailerRecyclerView;

    private TrailerAdapter mTrailerAdapter;
    private ReviewAdapter mReviewAdapter;
    private List<Review> mReviews = new ArrayList<Review>();
    private List<Trailer> mTrailers = new ArrayList<Trailer>();
    private Movie mMovie;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("MovieDetail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        mTrailerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTrailerRecyclerView.setNestedScrollingEnabled(false);


        Intent intent = getIntent();
        if (intent !=null) {
            Movie movie = (Movie) intent.getSerializableExtra(ARG_Movie);

          if(movie != null) {


                mMovieTitle.setText(movie.getTitle());
                mVoteAverage.setText("" + movie.getAverage() + "/10");
                mReleaseDate.setText(movie.getDate());
                mMoviePlot.setText(movie.getOverview());
                Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movie.
                        getMoviePoster()).into(mMoviePoster);

            }


                }

            }
}