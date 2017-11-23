package com.example.asanre.topmovies.data.network.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "movie", indices = {@Index(value = "page")})
public class MovieEntity {

    @PrimaryKey()
    private int id;
    private String title;
    private String poster_path;
    private String overview;
    private double vote_average;
    private int page;

    public MovieEntity(int id, String title, String poster_path, String overview,
                       double vote_average, int page) {

        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.vote_average = vote_average;
        this.page = page;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getPoster_path() {

        return poster_path;
    }

    public void setPoster_path(String poster_path) {

        this.poster_path = poster_path;
    }

    public String getOverview() {

        return overview;
    }

    public void setOverview(String overview) {

        this.overview = overview;
    }

    public double getVote_average() {

        return vote_average;
    }

    public void setVote_average(double vote_average) {

        this.vote_average = vote_average;
    }

    public int getPage() {

        return page;
    }

    public void setPage(int page) {

        this.page = page;
    }
}
