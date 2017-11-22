package com.example.asanre.topmovies.data;

import com.example.asanre.topmovies.data.network.ApiManager;
import com.example.asanre.topmovies.data.network.callbacks.ServiceCallback;
import com.example.asanre.topmovies.data.network.model.MovieRepo;

public class MovieRepository {

    private int defaultPageNumber = 1;
    private static MovieRepository sInstance;
    private ApiManager apiManager;

    public MovieRepository() {

        apiManager = ApiManager.getInstance();
    }

    public static MovieRepository getInstance() {

        if (sInstance == null) {
            synchronized (ApiManager.class) {
                if (sInstance == null) {
                    sInstance = new MovieRepository();
                }
            }
        }
        return sInstance;
    }

    public void getMovies(ServiceCallback<MovieRepo> callback) {

        apiManager.getTopMovies(callback, defaultPageNumber);
    }
}
