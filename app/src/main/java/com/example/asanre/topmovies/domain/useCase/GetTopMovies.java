package com.example.asanre.topmovies.domain.useCase;

import com.example.asanre.topmovies.domain.Provider;
import com.example.asanre.topmovies.domain.model.IMovie;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GetTopMovies implements UserCase<List<IMovie>, MovieParams> {

    @Override
    public void execute(BaseObserver<List<IMovie>> observer, MovieParams params) {

        Provider.getTopMovies(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
