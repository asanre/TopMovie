package com.example.asanre.topmovies.data.network.restclient;

import com.example.asanre.topmovies.data.model.MovieRepo;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("top_rated")
    Single<MovieRepo> getTopRateMovies(@QueryMap Map<String, String> options);

    @GET("{id}/similar")
    Single<MovieRepo> getRelatedMovies(@Path("id") int id, @QueryMap Map<String, String> options);
}
