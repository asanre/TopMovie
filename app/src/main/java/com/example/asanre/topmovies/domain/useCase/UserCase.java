package com.example.asanre.topmovies.domain.useCase;

public interface UserCase<RESPONSE_DATA, REQUEST_DATA> {

    void execute(BaseObserver<RESPONSE_DATA> observer, REQUEST_DATA params);
}
