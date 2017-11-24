package com.example.asanre.topmovies.ui.movieList.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.asanre.topmovies.R;
import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.ui.base.BaseFragment;
import com.example.asanre.topmovies.ui.movieList.adapter.MovieListAdapter;
import com.example.asanre.topmovies.ui.movieList.presenter.MovieListPresenter;
import com.example.asanre.topmovies.ui.moviedetail.controller.MovieDetailActivity;
import com.example.asanre.topmovies.ui.utils.RecyclerUtils;

import java.util.List;

import butterknife.BindView;

public class MovieListFragment extends BaseFragment
        implements MovieListView, MovieListAdapter.AdapterOnItemClickListener {

    @BindView(R.id.cl_container)
    CoordinatorLayout clContainer;
    @BindView(R.id.rlv_movies)
    RecyclerView recyclerView;
    @BindView(R.id.pb_loading)
    ProgressBar progressBar;

    private MovieListPresenter presenter;
    private MovieListAdapter adapter;
    private boolean isLoading = false;

    @Override
    protected int getFragmentLayout() {

        return R.layout.fragment_movie_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected void prepareView(View rootView) {

        adapter = new MovieListAdapter(getActivity(), this);

        setupRecycler(adapter);
        presenter = new MovieListPresenter(this);
        presenter.init();
        setRecyclerScrollListener();
    }

    @Override
    public void showLoading() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String errorMessage) {

        Snackbar.make(clContainer, errorMessage, Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void onItemClick(IMovie movie) {

        startMovieDetailIntent(movie);
    }

    @Override
    public void setAdapterData(List<IMovie> movies) {

        adapter.addAll(movies);
    }

    @Override
    public void notifyFinishLoading() {

        isLoading = false;
    }

    @Override
    public void refreshData(List<IMovie> movies) {

        adapter.refreshList(movies);
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.reload:
                presenter.fetchOnDemand();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startMovieDetailIntent(IMovie movie) {

        startActivity(MovieDetailActivity.createIntent(getActivity(), movie));
    }

    private void setupRecycler(MovieListAdapter adapter) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adapter);
    }

    private void setRecyclerScrollListener() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);
                if (isLoading) {
                    return;
                }

                if (RecyclerUtils.pageEndlessDetect(recyclerView)) {
                    isLoading = true;
                    presenter.fetchMoreMovies();
                }
            }
        });
    }
}
