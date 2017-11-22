package com.example.asanre.topmovies.ui.movieList.presenter;

import com.example.asanre.topmovies.data.network.callbacks.ServiceCallback;
import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.domain.useCase.GetTopMovies;
import com.example.asanre.topmovies.ui.base.BasePresenter;
import com.example.asanre.topmovies.ui.base.BaseView;
import com.example.asanre.topmovies.ui.movieList.view.MovieListView;

import java.util.List;

public class MovieListPresenter extends BasePresenter {

    private final MovieListView view;
    private GetTopMovies getTopMovies;

    public MovieListPresenter(MovieListView view) {

        this.view = view;
        getTopMovies = new GetTopMovies();
        fetchMovies();
    }

    @Override
    public BaseView getView() {

        return view;
    }

    private void fetchMovies() {

        view.showLoading();
        getTopMovies.execute(new ServiceCallback<List<IMovie>>() {

            @Override
            public void onServiceResult(List<IMovie> response) {

                if (isViewAlive()) {
                    view.setAdapterData(response);
                    view.hideLoading();
                }
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                // TODO: 22/11/17 show error
            }
        });
    }
}
