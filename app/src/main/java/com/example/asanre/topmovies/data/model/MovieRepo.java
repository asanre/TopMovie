package com.example.asanre.topmovies.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieRepo {

    private int page;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("results")
    private List<MovieEntity> movies;

    public MovieRepo(List<MovieEntity> movies) {

        this.movies = movies;
    }

    public List<MovieEntity> getMovies() {

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
