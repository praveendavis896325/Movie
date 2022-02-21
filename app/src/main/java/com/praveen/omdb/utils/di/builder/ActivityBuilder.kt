package com.praveen.omdb.utils.di.builder

import com.praveen.omdb.detail.di.DetailFragmentProvider
import com.praveen.omdb.main.di.MainActivityModule
import com.praveen.omdb.main.presentation.MainActivity
import com.praveen.omdb.movies.di.MoviesFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (MoviesFragmentProvider::class), (DetailFragmentProvider::class)])
    internal abstract fun bindMainActivity(): MainActivity

}