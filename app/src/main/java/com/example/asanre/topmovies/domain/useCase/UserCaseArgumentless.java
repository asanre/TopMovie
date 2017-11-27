package com.example.asanre.topmovies.domain.useCase;

public interface UserCaseArgumentless<RESPONSE_DATA> {

    void execute(BaseObserver<RESPONSE_DATA> response);
}
