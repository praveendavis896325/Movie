package com.praveen.omdb.detail.data

import com.praveen.omdb.utils.commons.AppConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDetailsApi {


    companion object {

        const val GET_MOVIE_DETAILS: String = ("?plot=full")
    }

    @GET(GET_MOVIE_DETAILS)
    suspend fun getMovieDetails(@Query("t") t: String, @Query(AppConstants.API_KEY_QUERY) apiKey: String): Response<DetailResponse>
}