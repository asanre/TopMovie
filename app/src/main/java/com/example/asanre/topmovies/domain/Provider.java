package com.example.asanre.topmovies.domain;

import android.content.Context;

import com.example.asanre.topmovies.data.MovieRepository;
import com.example.asanre.topmovies.data.model.MovieEntity;
import com.example.asanre.topmovies.data.model.MovieRepo;
import com.example.asanre.topmovies.data.network.callbacks.ServiceCallback;
import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.domain.model.Movie;
import com.example.asanre.topmovies.domain.useCase.MovieParams;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class Provider {

    private static MovieRepository movieRepository;

    public static void init(Context context) {

        movieRepository = MovieRepository.getInstance(context);
    }

    public static Single<List<IMovie>> getTopMovies(MovieParams params) {

        return movieRepository.getMovies(params.getPage())
                .map(movieEntities -> mapEntityToMovie(movieEntities));
    }

    public static void getSimilarMovies(final ServiceCallback<List<IMovie>> callback,
                                        MovieParams params) {

        movieRepository.getSimilarMovies(new ServiceCallback<MovieRepo>() {

            @Override
            public void onServiceResult(MovieRepo response) {

                //                callback.onServiceResult(mapEntityToMovie(response.getMovies()));
            }

            @Override
            public void onError(int errorCode, String errorMessage) {

                callback.onError(errorCode, errorMessage);
            }
        }, params.getMovieId(), params.getPage());
    }

    public static void fetchOnDemand(ServiceCallback<List<IMovie>> callback) {

        movieRepository.fetchOnDemand(new ServiceCallback<MovieRepo>() {

            @Override
            public void onServiceResult(MovieRepo response) {

                //                callback.onServiceResult(mapEntityToMovie(response.getMovies()));
            }

            @Override
            public void onError(int errorCode, String errorMessage) {

                callback.onError(errorCode, errorMessage);
            }
        });
    }

    private static List<IMovie> mapEntityToMovie(List<MovieEntity> movies) {

        ModelMapper mapper = new ModelMapper();
        List<IMovie> list = new ArrayList<>();
        for (MovieEntity entity : movies) {
            list.add(mapper.map(entity, Movie.class));
        }

        return list;
    }
}
