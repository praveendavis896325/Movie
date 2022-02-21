package com.praveen.omdb.movies.di

import androidx.recyclerview.widget.GridLayoutManager
import com.praveen.omdb.movies.presentation.MovieAdapter
import com.praveen.omdb.movies.presentation.MoviesFragment
import com.praveen.omdb.utils.commons.GridSpacingItemDecoration
import dagger.Module
import dagger.Provides

@Module
class MoviesFragmentModule {

    @Provides
    internal fun provideGridLayoutManager(fragment: MoviesFragment): GridLayoutManager {
        return GridLayoutManager(fragment.requireContext(), 2)
    }

    @Provides
    internal fun provideGridSpacingItemDecoration(): GridSpacingItemDecoration {
        return GridSpacingItemDecoration(2, 5, true)
    }

    @Provides
    internal fun provideMovieAdapter(): MovieAdapter {
        return MovieAdapter()
    }

}