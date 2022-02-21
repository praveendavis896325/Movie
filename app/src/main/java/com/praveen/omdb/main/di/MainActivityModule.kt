package com.praveen.omdb.main.di

import androidx.lifecycle.ViewModelProvider
import com.praveen.omdb.movies.data.MovieRepoImp
import com.praveen.omdb.movies.domain.MovieSourceFactory
import com.praveen.omdb.movies.presentation.MoviesViewModel
import com.praveen.omdb.utils.commons.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMoviesViewModel(movieRepoImp: MovieRepoImp, sourceFactory: MovieSourceFactory): MoviesViewModel {
        return MoviesViewModel(movieRepoImp,sourceFactory)
    }

    @Provides
    internal fun provideMoviesViewModelFactory(moviesViewModel: MoviesViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(moviesViewModel)
    }

}