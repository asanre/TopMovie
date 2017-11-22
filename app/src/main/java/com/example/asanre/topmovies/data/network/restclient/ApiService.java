package com.example.asanre.topmovies.data.network.restclient;

import com.example.asanre.topmovies.data.network.model.MovieRepo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("top_rated")
    Call<MovieRepo> getTopRateMovies(@QueryMap Map<String, String> options);

    @GET("{id}/similar")
    Call<MovieRepo> getRelatedMovies(@Path("id") int id, @QueryMap Map<String, String> options);
}
