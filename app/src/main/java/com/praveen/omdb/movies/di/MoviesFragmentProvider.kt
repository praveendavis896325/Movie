package com.praveen.omdb.movies.di

import com.praveen.omdb.movies.presentation.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesFragmentProvider {

    @ContributesAndroidInjector(modules =[(MoviesFragmentModule::class),(MovieSourceModule::class),])
    internal abstract fun provideMainFragmentFactory(): MoviesFragment

}