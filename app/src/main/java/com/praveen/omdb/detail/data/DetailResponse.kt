package com.praveen.omdb.detail.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailResponse(
    var Title: String?  = null,
    var Year: String?  = null,
    var Released: String?  = null,
    var Genre: String?  = null,
    var Director: String?  = null,
    var Writer: String?  = null,
    var Actors: String?  = null,
    var Plot: String?  = null,
    var Language: String?  = null,
    var Country: String?  = null,
    var Awards: String?  = null,
    var Rated: String?  = null,
    var Ratings: List<DetailRating>? = null
) : Parcelable
