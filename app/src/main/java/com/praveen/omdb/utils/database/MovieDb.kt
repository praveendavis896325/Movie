package com.praveen.omdb.utils.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.praveen.omdb.movies.data.Movie
import javax.inject.Singleton

@Singleton
@Database(entities = [(Movie::class)], version = 1, exportSchema = false)
abstract class MovieDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
