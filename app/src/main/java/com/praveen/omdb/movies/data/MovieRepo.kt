package com.praveen.omdb.movies.data

import com.praveen.omdb.detail.data.DetailResponse
import com.praveen.omdb.utils.network.ResultType


interface MovieRepo {
    suspend fun getPopularMovies(page: Int): ResultType<MovieResponse>
    suspend fun getFilteredPopularMovies(filterText: String): MovieResponse?
    suspend fun fetchMovieDetails(movieTitle: String): ResultType<DetailResponse>?
}