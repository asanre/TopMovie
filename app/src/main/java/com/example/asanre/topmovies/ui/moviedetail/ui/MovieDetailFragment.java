package com.example.asanre.topmovies.ui.moviedetail.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asanre.topmovies.R;
import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.ui.base.BaseFragment;
import com.example.asanre.topmovies.ui.utils.GlideHelper;

import butterknife.BindView;

public class MovieDetailFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_overview)
    TextView tvOverView;
    @BindView(R.id.iv_hero)
    ImageView ivPoster;

    private static String BUNDLE_KEY_MOVIE = "BUNDLE_KEY_MOVIE";

    public static Fragment newInstance(@NonNull IMovie movie) {

        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_KEY_MOVIE, (Parcelable) movie);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {

        return R.layout.fragment_movie_detail;
    }

    @Override
    protected void prepareView(View view) {

        Bundle arguments = getArguments();

        if (arguments != null) {
            IMovie movie = arguments.getParcelable(BUNDLE_KEY_MOVIE);
            if (movie != null) {
                tvTitle.setText(movie.getTitle());
                tvOverView.setText(movie.getOverView());
                setImage(ivPoster, movie.getImageUrl());
            }
        }
    }

    private void setImage(ImageView imageView, String path) {

        Glide.with(getActivity())
                .load(GlideHelper.getHeroImage(path))
                .apply(new RequestOptions().error(R.drawable.ic_image_error)
                        .placeholder(R.drawable.ic_image_holder)
                        .centerCrop())
                .into(imageView);
    }
}
