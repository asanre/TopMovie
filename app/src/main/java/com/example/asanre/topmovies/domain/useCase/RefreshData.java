package com.example.asanre.topmovies.domain.useCase;

import com.example.asanre.topmovies.domain.Provider;
import com.example.asanre.topmovies.domain.model.IMovie;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RefreshData implements UserCaseArgumentless<List<IMovie>> {

    @Override
    public void execute(BaseObserver<List<IMovie>> observer) {

        Provider.fetchOnDemand()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
