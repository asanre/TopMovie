package com.example.asanre.topmovies.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * due to the dimension of the project this model is used both for the database
 * and the network response. In a different scenario each domain would have his own model
 */
@Entity(tableName = "movie", indices = {@Index(value = "page")})
public class MovieEntity {

    @PrimaryKey()
    private int id;
    private String title;
    @SerializedName("poster_path")
    private String posterUrl;
    private String overview;
    @SerializedName("vote_average")
    private double rating;
    private int page;

    public MovieEntity(int id, String title, String posterUrl, String overview, double rating,
                       int page) {

        this.id = id;
        this.title = title;
        this.posterUrl = posterUrl;
        this.overview = overview;
        this.rating = rating;
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

    public String getPosterUrl() {

        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {

        this.posterUrl = posterUrl;
    }

    public String getOverview() {

        return overview;
    }

    public void setOverview(String overview) {

        this.overview = overview;
    }

    public double getRating() {

        return rating;
    }

    public void setRating(double rating) {

        this.rating = rating;
    }

    public int getPage() {

        return page;
    }

    public void setPage(int page) {

        this.page = page;
    }
}
