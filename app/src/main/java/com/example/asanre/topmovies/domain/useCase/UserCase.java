package com.example.asanre.topmovies.domain.useCase;

import com.example.asanre.topmovies.data.network.callbacks.ServiceCallback;

public interface UserCase<RESPONSE_DATA, REQUEST_DATA> {

    void execute(ServiceCallback<RESPONSE_DATA> callback, REQUEST_DATA params);
}
