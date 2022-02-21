package com.praveen.omdb.movies.di

import com.praveen.omdb.movies.data.MovieFilterSource
import com.praveen.omdb.movies.data.MoviePagingSource
import com.praveen.omdb.movies.data.MovieRepoImp
import com.praveen.omdb.movies.domain.MovieSourceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MovieSourceModule {
    @Provides
    internal fun provideMoviesSource(): MovieSourceFactory {
        return MovieSourceFactory()
    }

    @Provides
    @Singleton
    internal fun provideMoviesPagingSource(movieRepoImp: MovieRepoImp): MoviePagingSource {
        return MoviePagingSource(movieRepoImp)
    }

    @Provides
    @Singleton
    internal fun provideMoviesFilterSource(movieRepoImp: MovieRepoImp): MovieFilterSource {
        return MovieFilterSource(movieRepoImp)
    }
}