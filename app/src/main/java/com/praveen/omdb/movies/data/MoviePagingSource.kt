package com.praveen.omdb.movies.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.praveen.omdb.utils.network.ResultType
import javax.inject.Inject

class MoviePagingSource @Inject constructor(private val repo: MovieRepo) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = (repo.getPopularMovies(nextPage) as ResultType.Success).data
            LoadResult.Page(
                data = movieListResponse.Search,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage?.plus(1) ?: null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }
}