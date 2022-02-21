package com.praveen.omdb.movies.presentation

import androidx.paging.PagingData
import com.praveen.omdb.movies.data.Movie

sealed class MoviesViewState {
    class FetchingMoviesError(val errorMessage: String?) : MoviesViewState()
    class FetchingMoviesSuccess(val movies: PagingData<Movie>) : MoviesViewState()
}
