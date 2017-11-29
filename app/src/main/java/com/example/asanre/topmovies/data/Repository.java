package com.example.asanre.topmovies.data;

import com.example.asanre.topmovies.data.model.MovieEntity;

import java.util.List;

import io.reactivex.Single;

public interface Repository {

    /**
     * @param page int
     * @return list of movies
     */
    Single<List<MovieEntity>> getMovies(int page);

    /**
     * @param movieId int
     * @param page    int
     * @return list of movies
     */
    Single<List<MovieEntity>> getSimilarMovies(int movieId, int page);

    /**
     * @return fresh list of movies
     */
    Single<List<MovieEntity>> fetchOnDemand();
}
