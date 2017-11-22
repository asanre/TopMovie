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

import com.example.asanre.topmovies.R;
import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.ui.moviedetail.adapter.SwipePagerAdapter;
import com.example.asanre.topmovies.ui.moviedetail.presenter.MovieDetailPresenter;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {

    private static String BUNDLE_MOVIE_DETAIL = "BUNDLE_MOVIE_DETAIL";
    private SwipePagerAdapter adapter;
    private ProgressBar loading;
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

        IMovie movie = bundle.getParcelable(BUNDLE_MOVIE_DETAIL);
        if (movie != null) {
            loading = findViewById(R.id.pb_loading);
            presenter = new MovieDetailPresenter(this);
            initAdapter(movie);
        }
    }

    @Override
    public void showLoading() {

        if (loading != null) {
            loading.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {

        if (loading != null) {
            loading.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean isViewAlive() {

        return !isFinishing();
    }

    private void initAdapter(IMovie movie) {

        adapter = new SwipePagerAdapter(getSupportFragmentManager(), movie);
        ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
    }
}
