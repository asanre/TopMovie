package com.example.asanre.topmovies.ui.moviedetail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.ui.moviedetail.ui.MovieDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class SwipePagerAdapter extends FragmentPagerAdapter {

    private List<IMovie> movies = new ArrayList<>();

    public SwipePagerAdapter(FragmentManager fm, IMovie movie) {

        super(fm);
        movies.add(movie);
    }

    @Override
    public Fragment getItem(int position) {

        if (position < 0 || position >= getCount()) {
            return null;
        }
        IMovie movie = movies.get(position);
        return MovieDetailFragment.newInstance(movie);
    }

    @Override
    public int getCount() {

        return movies.size();
    }

    public void addAll(List<IMovie> newMovies) {

        if (movies == null) {
            movies = new ArrayList<>();
        }

        movies.addAll(newMovies);
        notifyDataSetChanged();
    }
}
