package com.example.asanre.topmovies.domain.useCase;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public interface BaseObserver<T> extends SingleObserver<T> {

    @Override
    default void onSubscribe(Disposable d) {

    }

    @Override
    void onSuccess(T t);

    @Override
    void onError(Throwable e);
}
