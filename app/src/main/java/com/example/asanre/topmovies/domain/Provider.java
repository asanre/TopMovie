package com.example.asanre.topmovies.domain;

import android.content.Context;

import com.example.asanre.topmovies.data.MovieRepository;
import com.example.asanre.topmovies.data.network.callbacks.ServiceCallback;
import com.example.asanre.topmovies.data.network.model.MovieEntity;
import com.example.asanre.topmovies.data.network.model.MovieRepo;
import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.domain.model.Movie;
import com.example.asanre.topmovies.domain.useCase.MovieParams;

import java.util.ArrayList;
import java.util.List;

public class Provider {

    // TODO: 23/11/17 i dont like this way
    private static MovieRepository movieRepository;

    public static void init(Context context) {

        movieRepository = MovieRepository.getInstance(context);
    }

    public static void getTopMovies(final ServiceCallback<List<IMovie>> callback,
                                    MovieParams params) {

        movieRepository.getMovies(new ServiceCallback<MovieRepo>() {

            @Override
            public void onServiceResult(MovieRepo response) {

                callback.onServiceResult(mapEntityToMovie(response.getMovies()));
            }

            @Override
            public void onError(int errorCode, String errorMessage) {

                callback.onError(errorCode, errorMessage);
            }
        }, params.getPage());
    }

    public static void getSimilarMovies(final ServiceCallback<List<IMovie>> callback,
                                        MovieParams params) {

        movieRepository.getSimilarMovies(new ServiceCallback<MovieRepo>() {

            @Override
            public void onServiceResult(MovieRepo response) {

                callback.onServiceResult(mapEntityToMovie(response.getMovies()));
            }

            @Override
            public void onError(int errorCode, String errorMessage) {

                callback.onError(errorCode, errorMessage);
            }
        }, params.getMovieId(), params.getPage());
    }

    private static List<IMovie> mapEntityToMovie(MovieEntity[] movies) {

        List<IMovie> list = new ArrayList<>();
        for (MovieEntity entity : movies) {
            IMovie movie = new Movie(entity.getId(), entity.getTitle(), entity.getPoster_path(),
                    entity.getOverview(), entity.getVote_average());
            list.add(movie);
        }

        return list;
    }
}
