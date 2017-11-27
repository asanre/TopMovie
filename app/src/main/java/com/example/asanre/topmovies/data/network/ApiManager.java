package com.example.asanre.topmovies.data.network;

import com.example.asanre.topmovies.BuildConfig;
import com.example.asanre.topmovies.data.model.MovieRepo;
import com.example.asanre.topmovies.data.network.restclient.RestClient;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;

public class ApiManager {

    private static ApiManager sInstance;

    public static ApiManager getInstance() {

        if (sInstance == null) {
            synchronized (ApiManager.class) {
                if (sInstance == null) {
                    sInstance = new ApiManager();
                }
            }
        }
        return sInstance;
    }

    public Single<MovieRepo> getTopMovies(int page) {

        return RestClient.getClient().getTopRateMovies(getQueriesParams(page));
    }

    public Single<MovieRepo> getSimilarMovies(int movieId, int page) {

        return RestClient.getClient().getRelatedMovies(movieId, getQueriesParams(page));

    }

    private Map<String, String> getQueriesParams(int pageNumber) {

        String page = String.valueOf(pageNumber);
        Map<String, String> data = new HashMap<>();
        data.put("page", page);
        data.put("language", "es");
        data.put("api_key", BuildConfig.API_KEY);

        return data;
    }
}
