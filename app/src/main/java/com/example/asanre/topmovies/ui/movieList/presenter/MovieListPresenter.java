package com.example.asanre.topmovies.ui.movieList.presenter;

import com.example.asanre.topmovies.data.network.callbacks.ServiceCallback;
import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.domain.useCase.BaseObserver;
import com.example.asanre.topmovies.domain.useCase.GetTopMovies;
import com.example.asanre.topmovies.domain.useCase.MovieParams;
import com.example.asanre.topmovies.domain.useCase.RefreshData;
import com.example.asanre.topmovies.ui.base.BasePresenter;
import com.example.asanre.topmovies.ui.base.BaseView;
import com.example.asanre.topmovies.ui.movieList.view.MovieListView;

import java.util.List;

public class MovieListPresenter extends BasePresenter {

    private final MovieListView view;
    private GetTopMovies getTopMovies;
    private RefreshData refreshData;
    private int currentPage;
    private boolean isLoadingOnDemand;

    public MovieListPresenter(MovieListView view) {

        this.view = view;
        getTopMovies = new GetTopMovies();
        refreshData = new RefreshData();
        currentPage = 1;
        isLoadingOnDemand = false;
    }

    /**
     * fetch top movies page 1
     */
    public void init() {

        fetchMovies(currentPage);
    }

    @Override
    public BaseView getView() {

        return view;
    }

    /**
     * fetch more movies increasing page
     * number according to pagination
     */
    public void fetchMoreMovies() {

        isLoadingOnDemand = true;
        fetchMovies(++currentPage);
    }

    /**
     * refresh list data
     */
    public void fetchOnDemand() {

        refreshData.execute(new ServiceCallback<List<IMovie>>() {

            @Override
            public void onServiceResult(List<IMovie> movies) {

                if (isViewAlive()) {
                    fetchOnDemandResult(movies);
                }
            }

            @Override
            public void onError(int errorCode, String errorMessage) {

                if (isViewAlive()) {
                    handlerError(errorMessage);
                }
            }
        });
    }

    private void fetchMovies(int page) {

        view.showLoading();
        getTopMovies.execute(new BaseObserver<List<IMovie>>() {

            @Override
            public void onSuccess(List<IMovie> iMovies) {

                if (isViewAlive()) {
                    onGetTopMovieSuccess(iMovies);
                }
            }

            @Override
            public void onError(Throwable error) {

                if (isViewAlive()) {
                    handlerError(error.getMessage());
                }
            }
        }, new MovieParams(page));

    }

    void fetchOnDemandResult(List<IMovie> movies) {

        view.refreshData(movies);
    }

    void onGetTopMovieSuccess(List<IMovie> response) {

        view.setAdapterData(response);
        view.hideLoading();

        if (isLoadingOnDemand) {
            view.notifyFinishLoading();
        }
    }

    void handlerError(String errorMessage) {

        view.hideLoading();
        view.showErrorMessage(errorMessage);
    }
}
