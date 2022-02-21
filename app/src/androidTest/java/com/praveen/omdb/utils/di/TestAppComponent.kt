package com.praveen.omdb.utils.di

import android.app.Application
import com.praveen.omdb.OmdbMovieApp
import com.praveen.omdb.utils.di.builder.ActivityBuilder
import com.praveen.omdb.utils.di.component.AppComponent
import com.praveen.omdb.utils.di.module.AppModule
import com.praveen.omdb.utils.di.module.RepoModule
import com.praveen.omdb.utils.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.DaggerApplication
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    MockDbModule::class,
    NetworkModule::class,
    RepoModule::class,
    ActivityBuilder::class,
    MockUrlModule::class
]
)
interface TestAppComponent : AppComponent {

    override fun inject(app: OmdbMovieApp)

    override fun inject(instance: DaggerApplication)

    fun getMockWebServer(): MockWebServer

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent
    }

}