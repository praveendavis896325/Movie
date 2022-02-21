package com.praveen.omdb.utils.di.module

import com.praveen.omdb.detail.data.MovieDetailsApi
import com.praveen.omdb.movies.data.MovieApi
import com.praveen.omdb.movies.data.MovieRepo
import com.praveen.omdb.movies.data.MovieRepoImp
import com.praveen.omdb.utils.database.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    internal fun provideMovieRepository(movieDao: MovieDao,
                                        movieApi: MovieApi,
                                        detailsApi: MovieDetailsApi
    ): MovieRepo {
        return MovieRepoImp(movieDao, movieApi, detailsApi)
    }

}