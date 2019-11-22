package com.example.android.popular_movies_app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.titleTextView) TextView mMovieTite;
    @BindView(R.id.iv_detail_movie_poster) ImageView mMoviePoster;
    @BindView(R.id.ratingLabel) TextView mRatingLabel;
    @BindView(R.id.rateTextView)TextView mMovieRating;
    @BindView(R.id.releaseDateLabel) TextView mReleaseDateLabel;
    @BindView(R.id.releaseDateTextView) TextView mReleaseDate;
    @BindView(R.id.overviewTextView) TextView mMovieOverview;
    @BindView(R.id.tv_plot_synopsis) TextView mMoviePlot;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


    }
}
