package com.praveen.omdb.movies.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    var totalResults: String?,
    var Search: ArrayList<Movie>) : Parcelable
