package com.praveen.omdb.movies.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "imdbID")
    val imdbID: String,
    @ColumnInfo(name = "title")
    val Title: String?,
    @ColumnInfo(name = "Poster")
    val Poster: String?,
    @ColumnInfo(name = "Type")
    val Type: String?,
    @ColumnInfo(name = "Year")
    val Year: String?
) : Parcelable