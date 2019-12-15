package com.example.android.popular_movies_app.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TrailerResponse implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("results")
    private List<Trailer> trailerList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Trailer> getTrailerList() {
        return trailerList;
    }

    public void setTrailerList(List<Trailer> trailerList) {
        this.trailerList = trailerList;
    }
}
