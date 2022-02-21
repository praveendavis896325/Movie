package com.praveen.omdb.utils.database

import androidx.room.*
import com.praveen.omdb.movies.data.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Query("SELECT imdbID FROM movies")
    fun fetchFavouriteMovies(): List<String?>

    @Delete()
    fun removeMovie(movie: Movie)

}