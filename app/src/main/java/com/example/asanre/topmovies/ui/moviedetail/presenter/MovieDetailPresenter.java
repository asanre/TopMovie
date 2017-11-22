package com.example.asanre.topmovies.ui.moviedetail.presenter;

import com.example.asanre.topmovies.ui.base.BasePresenter;
import com.example.asanre.topmovies.ui.base.BaseView;
import com.example.asanre.topmovies.ui.moviedetail.controller.MovieDetailView;

public class MovieDetailPresenter extends BasePresenter {

    private final MovieDetailView view;

    public MovieDetailPresenter(MovieDetailView view) {

        this.view = view;

    }

    @Override
    public BaseView getView() {

        return view;
    }
}
