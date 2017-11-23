package com.example.asanre.topmovies.domain.useCase;

import com.example.asanre.topmovies.data.network.callbacks.ServiceCallback;

public interface UserCaseArgumentless<RESPONSE_DATA> {

    void execute(ServiceCallback<RESPONSE_DATA> response);
}
