package com.praveen.omdb.utils.di

import android.content.Context
import androidx.room.Room
import com.praveen.omdb.utils.commons.AppConstants
import com.praveen.omdb.utils.database.MovieDao
import com.praveen.omdb.utils.database.MovieDb
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class MockDbModule {
    @Provides
    @Singleton
    @Named(AppConstants.DB_NAME_KEY)
    internal fun provideMovieDb(context: Context): MovieDb {
        return Room.databaseBuilder(context, MovieDb::class.java, AppConstants.DB_MOCK_NAME).fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    internal fun provideMovieDao(context: Context): MovieDao {
        return provideMovieDb(context).movieDao()
    }
}