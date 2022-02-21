package com.praveen.omdb.movies.domain

import androidx.paging.PagingSource
import com.praveen.omdb.movies.data.Movie
import com.praveen.omdb.movies.data.MovieFilterSource
import com.praveen.omdb.movies.data.MoviePagingSource
import javax.inject.Inject

class MovieSourceFactory @Inject constructor() {

    @Inject
    lateinit var movieFilterSource: MovieFilterSource

    @Inject
    lateinit var moviePagingSource: MoviePagingSource

    fun getSource( filterText: String): PagingSource<Int, Movie> {
        return if (filterText.length > 2) movieFilterSource.apply { this.filterText = filterText }
        else moviePagingSource
    }
}
