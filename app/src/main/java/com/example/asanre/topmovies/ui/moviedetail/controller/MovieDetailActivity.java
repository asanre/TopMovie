package com.example.asanre.topmovies.ui.moviedetail.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asanre.topmovies.R;
import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.ui.moviedetail.adapter.SwipePagerAdapter;
import com.example.asanre.topmovies.ui.moviedetail.presenter.MovieDetailPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnPageChange;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {

    private static String BUNDLE_MOVIE_DETAIL = "BUNDLE_MOVIE_DETAIL";

    @BindView(R.id.pb_loading)
    ProgressBar loading;
    @BindView(R.id.pager)
    ViewPager viewPager;

    private SwipePagerAdapter adapter;
    private MovieDetailPresenter presenter;

    public static Intent createIntent(Context context, IMovie Movie) {

        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(BUNDLE_MOVIE_DETAIL, (Parcelable) Movie);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            finish();
            return;
        }
        ButterKnife.bind(this);

        IMovie movie = bundle.getParcelable(BUNDLE_MOVIE_DETAIL);
        if (movie != null) {
            presenter = new MovieDetailPresenter(this, movie);
            presenter.init();
            initAdapter(movie);
        }
    }

    @Override
    public void showLoading() {

        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        loading.setVisibility(View.GONE);
    }

    @Override
    public boolean isViewAlive() {

        return !isFinishing();
    }

    @Override
    public void setAdapterData(List<IMovie> movies) {

        adapter.addAll(movies);
    }

    @Override
    public void goNextPage() {

        int nextPosition = viewPager.getCurrentItem() + 1;

        if (presenter.shouldShowNextPage(viewPager.getCurrentItem(), adapter.getCount())) {
            viewPager.setCurrentItem(nextPosition);
        }
    }

    @Override
    public void showErrorMessage(String errorMessage) {

        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @OnPageChange(value = R.id.pager, callback = OnPageChange.Callback.PAGE_SCROLL_STATE_CHANGED)
    void onPageScrollStateChanged(int state) {

        if (presenter.isLastPage(state, adapter.getCount(), viewPager.getCurrentItem())) {
            presenter.getMoreSimilarMovies();
        }
    }

    private void initAdapter(IMovie movie) {

        adapter = new SwipePagerAdapter(getSupportFragmentManager(), movie);
        viewPager.setAdapter(adapter);
    }
}
