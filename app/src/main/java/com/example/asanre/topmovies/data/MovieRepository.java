package com.example.asanre.topmovies.data;

import android.content.Context;

import com.example.asanre.topmovies.data.database.MovieDB;
import com.example.asanre.topmovies.data.database.MovieDao;
import com.example.asanre.topmovies.data.model.MovieEntity;
import com.example.asanre.topmovies.data.model.MovieRepo;
import com.example.asanre.topmovies.data.network.ApiManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class MovieRepository implements Repository {

    private final MovieDao movieDao;
    private final int defaultPageNumber = 1;
    private static MovieRepository sInstance;
    private ApiManager apiManager;

    private MovieRepository(Context context) {

        apiManager = ApiManager.getInstance();
        movieDao = MovieDB.getInstance(context).getMovieDao();
    }

    public static MovieRepository getInstance(Context context) {

        if (sInstance == null) {
            synchronized (ApiManager.class) {
                if (sInstance == null) {
                    sInstance = new MovieRepository(context);
                }
            }
        }
        return sInstance;
    }

    /**
     * getMovies retrieve movies from cloud but if there is a problem
     * return movies from DB
     *
     * @param page int
     * @return
     */
    @Override
    public Single<List<MovieEntity>> getMovies(int page) {

        return Single.zip(fetchAndSaveMovies(page).onErrorReturnItem(new ArrayList<>()),
                movieDao.getMoviesByPage(page), (moviesFromCloud, moviesFromDB) -> {

                    if (moviesFromCloud.isEmpty()) {
                        return moviesFromDB;
                    }

                    return moviesFromCloud;
                });

    }

    @Override
    public Single<List<MovieEntity>> getSimilarMovies(int movieId, int page) {

        return apiManager.getSimilarMovies(movieId, page).map(MovieRepo::getMovies);
    }

    /**
     * this clear the database, fetch from cloud the first page
     * of movies and after emitting save the response
     *
     * @return a fresh list of movies
     */
    @Override
    public Single<List<MovieEntity>> fetchOnDemand() {

        return apiManager.getTopMovies(defaultPageNumber).doAfterSuccess(movieRepo -> {
            clearDB();
            saveMovies(movieRepo);
        }).map(MovieRepo::getMovies);
    }

    private Single<List<MovieEntity>> fetchAndSaveMovies(int page) {

        return apiManager.getTopMovies(page)
                .doAfterSuccess(this::saveMovies)
                .map(MovieRepo::getMovies);
    }

    private void saveMovies(MovieRepo response) {

        movieDao.save(getMoviesWithPageIndex(response));
    }

    private MovieEntity[] getMoviesWithPageIndex(MovieRepo repo) {

        List<MovieEntity> movies = repo.getMovies();
        for (MovieEntity movie : movies) {
            movie.setPage(repo.getPage());
        }

        return movies.toArray(new MovieEntity[movies.size()]);
    }

    private void clearDB() {

        movieDao.clear();
    }
}
