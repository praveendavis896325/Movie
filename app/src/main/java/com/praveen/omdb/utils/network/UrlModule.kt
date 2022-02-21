package com.praveen.omdb.utils.network

import com.praveen.omdb.BuildConfig
import com.praveen.omdb.utils.commons.AppConstants.Companion.BASE_URL_KEY
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class UrlModule {
    @Provides
    @Singleton
    @Named(BASE_URL_KEY)
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}