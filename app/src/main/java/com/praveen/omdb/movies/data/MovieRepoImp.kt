package com.praveen.omdb.movies.data

import com.praveen.omdb.BuildConfig
import com.praveen.omdb.detail.data.DetailResponse
import com.praveen.omdb.detail.data.MovieDetailsApi
import com.praveen.omdb.utils.database.MovieDao
import com.praveen.omdb.utils.network.NetworkRouter
import com.praveen.omdb.utils.network.ResultType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepoImp @Inject constructor(
    private val movieDao: MovieDao,
    private val movieApi: MovieApi,
    private val detailsApi: MovieDetailsApi
) : MovieRepo {

    private var moviesResponse: MovieResponse? =null

    override suspend fun getPopularMovies(page: Int): ResultType<MovieResponse> {
        val response = NetworkRouter.invokeCall { movieApi.getMostPopular(BuildConfig.API_KEY, page) }
        if (response is ResultType.Success) {
            if (moviesResponse == null) moviesResponse = response.data
            else
                moviesResponse?.Search.also {
                    it?.addAll(response.data.Search)
                }?.let {
                    moviesResponse = response.data.copy()
                    moviesResponse!!.Search = it
                }
        }
        return response
    }

    override suspend fun getFilteredPopularMovies(filterText: String): MovieResponse? {

        val result = moviesResponse?.Search?.filter { movie -> movie.Title?.contains(filterText, true) == true  }?.toList()
        result?.let {
            val response = moviesResponse?.copy()
            response?.Search = ArrayList(it)
            return response
        }
        return null
    }

    override suspend fun fetchMovieDetails(movieTitle: String): ResultType<DetailResponse> {
        return NetworkRouter.invokeCall { detailsApi.getMovieDetails(movieTitle, BuildConfig.API_KEY) }
    }



}