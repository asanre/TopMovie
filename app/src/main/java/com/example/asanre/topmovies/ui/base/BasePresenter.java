package com.example.asanre.topmovies.ui.base;

public abstract class BasePresenter {

    public abstract BaseView getView();

    public boolean isViewAlive() {

        return getView() != null && getView().isViewAlive();
    }
}
