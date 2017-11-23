package com.example.asanre.topmovies.ui.movieList.presenter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.asanre.topmovies.data.network.callbacks.ServiceCallback;
import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.domain.useCase.GetTopMovies;
import com.example.asanre.topmovies.domain.useCase.MovieParams;
import com.example.asanre.topmovies.ui.base.BasePresenter;
import com.example.asanre.topmovies.ui.base.BaseView;
import com.example.asanre.topmovies.ui.movieList.view.MovieListView;

import java.util.List;

public class MovieListPresenter extends BasePresenter {

    private final MovieListView view;
    private GetTopMovies getTopMovies;
    private int currentPage;
    private boolean isLoadingOnDemand;

    public MovieListPresenter(MovieListView view) {

        this.view = view;
        getTopMovies = new GetTopMovies();
        currentPage = 1;
        isLoadingOnDemand = false;
    }

    public void init() {

        fetchMovies(currentPage);
    }

    @Override
    public BaseView getView() {

        return view;
    }

    public void fetchMoreMovies() {

        isLoadingOnDemand = true;
        fetchMovies(++currentPage);
    }

    public boolean pageEndlessDetect(RecyclerView recyclerView) {

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
            return pastVisibleItems + visibleItemCount >= totalItemCount - visibleItemCount;
        } else {
            return false;
        }
    }

    private void fetchMovies(int page) {

        view.showLoading();
        getTopMovies.execute(new ServiceCallback<List<IMovie>>() {

            @Override
            public void onServiceResult(List<IMovie> response) {

                if (isViewAlive()) {
                    view.setAdapterData(response);
                    view.hideLoading();

                    if (isLoadingOnDemand) {
                        view.notifyFinishLoading();
                    }
                }
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                // TODO: 22/11/17 show error
            }
        }, new MovieParams(page));
    }
}
