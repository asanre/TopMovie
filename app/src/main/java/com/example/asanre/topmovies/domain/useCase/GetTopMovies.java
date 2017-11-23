package com.example.asanre.topmovies.domain.useCase;

import com.example.asanre.topmovies.data.network.callbacks.ServiceCallback;
import com.example.asanre.topmovies.domain.Provider;
import com.example.asanre.topmovies.domain.model.IMovie;

import java.util.List;

public class GetTopMovies implements UserCase<List<IMovie>, MovieParams> {

    @Override
    public void execute(ServiceCallback<List<IMovie>> callback, MovieParams params) {

        Provider.getTopMovies(callback, params);
    }
}
