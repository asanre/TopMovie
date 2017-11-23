package com.example.asanre.topmovies.domain.useCase;

import com.example.asanre.topmovies.data.network.callbacks.ServiceCallback;
import com.example.asanre.topmovies.domain.Provider;
import com.example.asanre.topmovies.domain.model.IMovie;

import java.util.List;

public class RefreshData implements UserCaseArgumentless<List<IMovie>> {

    @Override
    public void execute(ServiceCallback<List<IMovie>> callback) {

        Provider.fetchOnDemand(callback);
    }
}
