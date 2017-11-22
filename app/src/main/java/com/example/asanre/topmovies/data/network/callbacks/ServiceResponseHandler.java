package com.example.asanre.topmovies.data.network.callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceResponseHandler<T> implements Callback<T> {

    private final ServiceCallback<T> listener;

    public ServiceResponseHandler(ServiceCallback<T> listener) {

        this.listener = listener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (response != null) {
            if (response.isSuccessful() && response.body() != null) {
                listener.onServiceResult(response.body());
            } else {
                listener.onError(response.code(), "Unknown sever error ");
            }
        } else {
            listener.onError(0, "Invalid data received");
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

        if (!t.getMessage().equals("Canceled")) {
            listener.onError(0, "Error requesting data to the server");
        }
    }
}
