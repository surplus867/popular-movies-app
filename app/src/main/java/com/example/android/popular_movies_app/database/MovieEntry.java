package com.example.android.popular_movies_app.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class MovieEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "movie_id")
    private int movieId;

    @ColumnInfo(name = "original_title")
    private String originalTitle;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "vote_average")
    private Double voteAverage;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "overview")
    private String overview;

    @Ignore
    public MovieEntry(int movieId, String originalTitle, String posterPath, double voteAverage,
                      String releaseDate, String overview) {
        this.movieId = movieId;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }
    public MovieEntry(int id, int movieId, String originalTitle, String posterPath,
                      double voteAverage, String releaseDate, String overview){
        this.id = id;
        this.movieId = movieId;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}