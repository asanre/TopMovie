package com.example.asanre.topmovies.domain;

import android.content.Context;

import com.example.asanre.topmovies.data.MovieRepository;
import com.example.asanre.topmovies.data.model.MovieEntity;
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

        return map(movieRepository.getMovies(params.getPage()));
    }

    public static Single<List<IMovie>> getSimilarMovies(MovieParams params) {

        return map(movieRepository.getSimilarMovies(params.getMovieId(), params.getPage()));
    }

    public static Single<List<IMovie>> fetchOnDemand() {

        return map(movieRepository.fetchOnDemand());
    }

    private static Single<List<IMovie>> map(Single<List<MovieEntity>> source) {

        return source.map(Provider::mapEntityToMovie);
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
