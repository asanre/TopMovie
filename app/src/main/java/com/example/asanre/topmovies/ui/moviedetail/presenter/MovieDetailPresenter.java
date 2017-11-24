package com.example.asanre.topmovies.ui.moviedetail.presenter;

import com.example.asanre.topmovies.data.network.callbacks.ServiceCallback;
import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.domain.useCase.GetSimilarMovies;
import com.example.asanre.topmovies.domain.useCase.MovieParams;
import com.example.asanre.topmovies.ui.base.BasePresenter;
import com.example.asanre.topmovies.ui.base.BaseView;
import com.example.asanre.topmovies.ui.moviedetail.controller.MovieDetailView;

import java.util.List;

public class MovieDetailPresenter extends BasePresenter {

    private final MovieDetailView view;
    private GetSimilarMovies getSimilarMovies;
    private int currentPage;
    private IMovie movie;

    public MovieDetailPresenter(MovieDetailView view, IMovie movie) {

        this.view = view;
        currentPage = 1;
        this.movie = movie;
        getSimilarMovies = new GetSimilarMovies();
    }

    @Override
    public BaseView getView() {

        return view;
    }

    public void init() {

        fetchSimilarMovies(new MovieParams(movie.getId(), currentPage));
    }

    public void getMoreSimilarMovies() {

        fetchSimilarMovies(new MovieParams(movie.getId(), ++currentPage));
    }

    private void fetchSimilarMovies(MovieParams params) {

        if (isLoadDataOnDemand()) {
            view.showLoading();
        }

        getSimilarMovies.execute(new ServiceCallback<List<IMovie>>() {

            @Override
            public void onServiceResult(List<IMovie> movies) {

                if (isViewAlive()) {
                    if (!movies.isEmpty()) {
                        view.setAdapterData(movies);
                        if (isLoadDataOnDemand()) {
                            view.goNextPage();
                        }
                    }

                    if (isLoadDataOnDemand()) {
                        view.hideLoading();
                    }
                }
            }

            @Override
            public void onError(int errorCode, String errorMessage) {

                if (isViewAlive()) {
                    view.hideLoading();
                    view.showErrorMessage(errorMessage);
                }
            }
        }, params);
    }

    public boolean isLastPage(int state, int count, int currentItem) {

        int lastIdx = count - 1;
        return currentItem == lastIdx && state == 1;
    }

    private boolean isLoadDataOnDemand() {

        return currentPage > 1;
    }

    public boolean shouldShowNextPage(int currentItem, int count) {

        int nextPosition = currentItem + 1;
        int lastIdx = count - 1;
        return nextPosition < lastIdx;
    }
}
