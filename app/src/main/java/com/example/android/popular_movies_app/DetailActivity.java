package com.example.android.popular_movies_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.android.popular_movies_app.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String ARG_Movie = "DETAIL_MOVIE";

    @BindView(R.id.iv_detail_movie_poster) ImageView mMoviePoster;
    @BindView(R.id.titleTextView) TextView mMovieTitle;
    @BindView(R.id.rateTextView)TextView mMovieRating;
    @BindView(R.id.releaseDateTextView) TextView mReleaseDate;
    @BindView(R.id.tv_plot_synopsis) TextView mMoviePlot;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        if(intent !=null) {
            Movie movie = (Movie) intent.getSerializableExtra(ARG_Movie);

        if(movie !=null) {

            mMovieTitle.setText("title: " + movie.getTitle());
            mMovieRating.setText("rate: " + movie.getRate());
            mReleaseDate.setText("release " + movie.getDate());
            mMoviePlot.setText("overview " + movie.getOverview());
            Glide.with(this).load(movie.getMoviePoster()).into(mMoviePoster);
        }
        }
    }
}