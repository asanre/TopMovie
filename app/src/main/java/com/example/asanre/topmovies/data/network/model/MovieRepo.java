package com.example.asanre.topmovies.data.network.model;

import com.google.gson.annotations.SerializedName;

public class MovieRepo {

    private int page;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("results")
    private MovieEntity[] movies;

    public MovieEntity[] getMovies() {

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
