package com.example.asanre.topmovies.ui.movieList.view;

import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.ui.base.BaseView;

import java.util.List;

public interface MovieListView extends BaseView {

    void setAdapterData(List<IMovie> movies);

    void notifyFinishLoading();
}
