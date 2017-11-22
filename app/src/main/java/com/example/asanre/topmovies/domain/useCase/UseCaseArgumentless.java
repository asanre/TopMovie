package com.example.asanre.topmovies.domain.useCase;

import com.example.asanre.topmovies.data.network.callbacks.ServiceCallback;

public interface UseCaseArgumentless<T> {

    void execute(ServiceCallback<T> callback);
}
