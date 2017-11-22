package com.example.asanre.topmovies.data.network.callbacks;

public interface ServiceCallback<T> {

    void onServiceResult(T response);

    void onError(int errorCode, String errorMessage);
}
