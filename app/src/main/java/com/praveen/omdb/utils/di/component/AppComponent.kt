package com.praveen.omdb.utils.di.component

import android.app.Application
import com.praveen.omdb.OmdbMovieApp
import com.praveen.omdb.utils.database.DbModule
import com.praveen.omdb.utils.di.builder.ActivityBuilder
import com.praveen.omdb.utils.di.module.AppModule
import com.praveen.omdb.utils.di.module.RepoModule
import com.praveen.omdb.utils.network.NetworkModule
import com.praveen.omdb.utils.network.UrlModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (DbModule::class),
    (NetworkModule::class), (UrlModule::class),(RepoModule::class), (ActivityBuilder::class)])

interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: OmdbMovieApp)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}