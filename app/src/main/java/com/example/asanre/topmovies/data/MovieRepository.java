package com.example.asanre.topmovies.data;

import android.content.Context;

import com.example.asanre.topmovies.data.database.MovieDB;
import com.example.asanre.topmovies.data.database.MovieDao;
import com.example.asanre.topmovies.data.network.ApiManager;
import com.example.asanre.topmovies.data.network.callbacks.ServiceCallback;
import com.example.asanre.topmovies.data.network.model.MovieEntity;
import com.example.asanre.topmovies.data.network.model.MovieRepo;

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

    public void getMovies(final ServiceCallback<MovieRepo> callback, int page) {

        executors.networkIO()
                .execute(() -> apiManager.getTopMovies(new ServiceCallback<MovieRepo>() {

                    @Override
                    public void onServiceResult(final MovieRepo response) {

                        saveMovies(response);
                        callback.onServiceResult(response);
                    }

                    @Override
                    public void onError(int errorCode, String errorMessage) {

                        callback.onError(errorCode, errorMessage);
                    }
                }, page));
    }

    public void getSimilarMovies(ServiceCallback<MovieRepo> callback, int movieId, int page) {

        executors.networkIO().execute(() -> apiManager.getSimilarMovies(callback, movieId, page));
    }

    private void saveMovies(MovieRepo response) {

        executors.diskIO().execute(() -> {

            movieDao.save(getMoviesWithPageIndex(response));
        });
    }

    private MovieEntity[] getMoviesWithPageIndex(MovieRepo repo) {

        for (MovieEntity movie : repo.getMovies()) {
            movie.setPage(repo.getPage());
        }

        return repo.getMovies();
    }
}
