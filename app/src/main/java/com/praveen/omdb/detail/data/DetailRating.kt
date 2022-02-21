package com.praveen.omdb.detail.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailRating(
    val Source: String,
    val Value: String
) : Parcelable