package com.example.asanre.topmovies.ui.movieList.presenter;

import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.domain.useCase.GetTopMovies;
import com.example.asanre.topmovies.domain.useCase.RefreshData;
import com.example.asanre.topmovies.ui.movieList.view.MovieListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MovieListView.class, GetTopMovies.class, RefreshData.class, MovieListPresenter.class})
public class MovieListPresenterTest {

    @Mock
    private MovieListView view;

    private MovieListPresenter spy;

    @Before
    public void setup() throws Exception {

        spy = PowerMockito.spy(new MovieListPresenter(view));
    }

    @Test
    public void given_userOnRefreshClicked_when_onFetchResult_then_refreshData() throws Exception {

        List<IMovie> movies = new ArrayList<>();
        spy.fetchOnDemandResult(movies);

        verify(view).refreshData(movies);
    }

    @Test
    public void given_fetchTopMovieSuccess_then_updateListData() throws Exception {

        List<IMovie> movies = new ArrayList<>();
        spy.onGetTopMovieSuccess(movies);

        verify(view).setAdapterData(movies);
    }

    @Test
    public void getTopMoviesError() throws Exception {

        String error = "server error";
        spy.handlerError(error);

        verify(view).hideLoading();
        verify(view).showErrorMessage(error);
    }

}