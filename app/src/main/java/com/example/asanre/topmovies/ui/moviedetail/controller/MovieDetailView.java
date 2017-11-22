package com.example.asanre.topmovies.ui.moviedetail.controller;

import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.ui.base.BaseView;

import java.util.List;

public interface MovieDetailView extends BaseView {

    void setAdapterData(List<IMovie> movies);
}
