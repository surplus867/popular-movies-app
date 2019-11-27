package com.example.android.popular_movies_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.android.popular_movies_app.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String ARG_Movie = "DETAIL_MOVIE";

    @BindView(R.id.posterImageView) ImageView mMoviePoster;
    @BindView(R.id.titleTextView) TextView mMovieTitle;
    @BindView(R.id.ratingTextView)TextView mMovieRating;
    @BindView(R.id.releaseDateTextView) TextView mReleaseDate;
    @BindView(R.id.tv_plot_synopsis) TextView mMoviePlot;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("MovieDetail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        if(intent !=null) {
            Movie movie = (Movie) intent.getSerializableExtra(ARG_Movie);

            if(movie !=null) {

                mMovieTitle.setText(movie.getTitle());
                mMovieRating.setText(movie.getRating());
                mReleaseDate.setText( movie.getDate());
                mMoviePlot.setText(movie.getOverview());
                Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movie.
                        getMoviePoster()).into(mMoviePoster);
            }
        }
    }
}
