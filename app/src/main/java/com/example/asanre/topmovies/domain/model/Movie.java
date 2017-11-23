package com.example.asanre.topmovies.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements IMovie, Parcelable {

    private int id;
    private String title;
    private String poster_path;
    private String overview;
    private double vote_average;

    public Movie(int id, String title, String poster_path, String overview, double vote_average) {

        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.vote_average = vote_average;
    }

    @Override
    public int getId() {

        return id;
    }

    @Override
    public String getTitle() {

        return title;
    }

    @Override
    public String getOverView() {

        return overview;
    }

    @Override
    public String getImageUrl() {

        return poster_path;
    }

    @Override
    public double getRating() {

        return vote_average;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setPoster_path(String poster_path) {

        this.poster_path = poster_path;
    }

    public void setOverview(String overview) {

        this.overview = overview;
    }

    public void setVote_average(double vote_average) {

        this.vote_average = vote_average;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.poster_path);
        dest.writeString(this.overview);
        dest.writeDouble(this.vote_average);
    }

    public Movie() {

    }

    protected Movie(Parcel in) {

        this.id = in.readInt();
        this.title = in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
        this.vote_average = in.readDouble();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {

            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {

            return new Movie[size];
        }
    };
}
