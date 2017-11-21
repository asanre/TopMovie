package com.example.asanre.topmovies.data.network.model;

public class MovieEntity {

    private int id;
    private String title;
    private String poster_path;
    private String overview;
    private double vote_average;

    public MovieEntity(int id, String title, String poster_path, String overview,
                       double vote_average) {

        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.vote_average = vote_average;
    }

    public int getId() {

        return id;
    }

    public String getTitle() {

        return title;
    }

    public String getPoster_path() {

        return poster_path;
    }

    public String getOverview() {

        return overview;
    }

    public double getVote_average() {

        return vote_average;
    }
}
