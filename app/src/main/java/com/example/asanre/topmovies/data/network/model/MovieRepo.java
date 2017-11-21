package com.example.asanre.topmovies.data.network.model;

import android.graphics.Movie;

import com.google.gson.annotations.SerializedName;

public class MovieRepo {

    private int page;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("results")
    private Movie[] movies;

    public Movie[] getMovies() {

        return movies;
    }

    public int getPage() {

        return page;
    }

    public int getTotalResults() {

        return totalResults;
    }

    public int getTotalPages() {

        return totalPages;
    }
}
