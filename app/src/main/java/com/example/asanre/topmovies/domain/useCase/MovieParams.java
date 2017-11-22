package com.example.asanre.topmovies.domain.useCase;

public class MovieParams {

    private int movieId;
    private int page;

    public MovieParams(int page) {

        this.page = page;
    }

    public MovieParams(int movieId, int page) {

        this.movieId = movieId;
        this.page = page;
    }

    public int getMovieId() {

        return movieId;
    }

    public int getPage() {

        return page;
    }
}
