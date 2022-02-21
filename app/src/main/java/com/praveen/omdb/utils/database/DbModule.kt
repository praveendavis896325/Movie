package com.praveen.omdb.utils.database

import android.content.Context
import androidx.room.Room
import com.praveen.omdb.utils.commons.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DbModule{

    @Provides
    @Singleton
    @Named(AppConstants.DB_NAME_KEY)
    internal fun provideMovieDb(context: Context): MovieDb {
        return Room.databaseBuilder(context, MovieDb::class.java, AppConstants.DB_NAME).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    internal fun provideMovieDao(context: Context): MovieDao {
        return provideMovieDb(context).movieDao()
    }
}