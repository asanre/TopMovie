package com.example.asanre.topmovies.ui.moviedetail.presenter;

import com.example.asanre.topmovies.domain.model.IMovie;
import com.example.asanre.topmovies.domain.model.Movie;
import com.example.asanre.topmovies.domain.useCase.GetSimilarMovies;
import com.example.asanre.topmovies.ui.moviedetail.controller.MovieDetailView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MovieDetailPresenter.class, MovieDetailView.class, GetSimilarMovies.class, IMovie.class, Movie.class})
public class MovieDetailPresenterTest {

    @Mock
    private MovieDetailView view;
    @Mock
    private IMovie movie;
    @Mock
    private GetSimilarMovies getSimilarMovies;

    private MovieDetailPresenter spy;

    @Before
    public void setup() throws Exception {

        spy = PowerMockito.spy(new MovieDetailPresenter(view, movie));
    }

    @Test
    public void given_fetchSimilarMoviesSuccess_when_responseIsNotEmptyAndLoadDataIsTrue_Then_setDataToAdapterAndGoNextPage()
            throws Exception {

        List<IMovie> movies = new ArrayList<>();
        movies.add(new Movie());
        Whitebox.setInternalState(spy, "currentPage", 2);
        spy.onGetSimilarMoviesSuccess(movies);

        verify(view).setAdapterData(movies);
        verify(view).goNextPage();
    }

    @Test
    public void given_fetchSimilarMoviesSuccess_when_responseIsNotEmptyAndLoadDataIsFalse_Then_setDataToAdapter()
            throws Exception {

        List<IMovie> movies = new ArrayList<>();
        movies.add(new Movie());
        spy.onGetSimilarMoviesSuccess(movies);

        verify(view).setAdapterData(movies);
        verify(view, times(0)).goNextPage();
    }

    @Test
    public void given_fetchSimilarMoviesError_when_IsSaveToManipulateView_Then_showErrorAndHideLoading()
            throws Exception {

        String error = "server error";
        when(spy.isViewAlive()).thenReturn(true);
        spy.onGetSimilarMoviesError(error);

        verify(view).hideLoading();
        verify(view).showErrorMessage(error);
    }

    @Test
    public void given_delayFetchSimilarMoviesError_when_viewIsDestroy_Then_checkNoInteractionWithView()
            throws Exception {

        String error = "server error";
        when(spy.getView()).thenReturn(null);
        when(spy.isViewAlive()).thenReturn(false);
        spy.onGetSimilarMoviesError(error);

        verifyZeroInteractions(view);
    }

    @Test
    public void given_currentItemIndexAndAdapterSize_then_checkIfTheLastPage() throws Exception {

        boolean isLastPage = spy.isLastPage(1, 8, 7);
        assertTrue(isLastPage);
    }

    @Test
    public void given_currentItemIndexEqualToAdapterSize_then_shouldShowNextPageReturnFalse()
            throws Exception {

        boolean shouldShowNextPage = spy.shouldShowNextPage(8, 9);
        assertFalse(shouldShowNextPage);
    }

    @Test
    public void given_currentItemIndexLessThanAdapterSize_then_shouldShowNextPageReturnTrue()
            throws Exception {

        boolean shouldShowNextPage = spy.shouldShowNextPage(1, 9);
        assertTrue(shouldShowNextPage);
    }

}