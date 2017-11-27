package com.example.asanre.topmovies.data;

import android.content.Context;

import com.example.asanre.topmovies.data.database.MovieDB;
import com.example.asanre.topmovies.data.database.MovieDao;
import com.example.asanre.topmovies.data.model.MovieEntity;
import com.example.asanre.topmovies.data.model.MovieRepo;
import com.example.asanre.topmovies.data.network.ApiManager;

import java.util.List;

import io.reactivex.Single;

public class MovieRepository {

    private final MovieDao movieDao;
    private int defaultPageNumber = 1;
    private static MovieRepository sInstance;
    private ApiManager apiManager;
    private AppExecutors executors;

    private MovieRepository(Context context) {

        apiManager = ApiManager.getInstance();
        movieDao = MovieDB.getInstance(context).getMovieDao();
        executors = AppExecutors.getInstance();
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

    public Single<List<MovieEntity>> getMovies(int page) {

        return fetchMovies(page);
        //       executors.diskIO().execute(() -> {
        //
        //            MovieEntity[] movies = movieDao.getMoviesByPage(page);
        //            if (movies.length == 0) {
        //                fetchMovies(singleObserver, page);
        //            } else {
        //                callback.onServiceResult(new MovieRepo(movies));
        //            }
        //        });
    }

    public Single<List<MovieEntity>> getSimilarMovies(int movieId, int page) {

        return apiManager.getSimilarMovies(movieId, page).map(MovieRepo::getMovies);
    }

    public Single<List<MovieEntity>> fetchOnDemand() {

        //        clearDB();
        return fetchMovies(defaultPageNumber);
    }

    private Single<List<MovieEntity>> fetchMovies(int page) {

        return apiManager.getTopMovies(page).map(movieRepo -> {

            //            saveMovies(movieRepo);
            return movieRepo.getMovies();
        });
    }

    private void saveMovies(MovieRepo response) {

        //        movieDao.save(getMoviesWithPageIndex(response));
    }

    //    private MovieEntity[] getMoviesWithPageIndex(MovieRepo repo) {
    //
    //        for (MovieEntity movie : repo.getMovies()) {
    //            movie.setPage(repo.getPage());
    //        }
    //
    //        return repo.getMovies();
    //    }

    private void clearDB() {

        executors.diskIO().execute(movieDao::clear);
    }
}
