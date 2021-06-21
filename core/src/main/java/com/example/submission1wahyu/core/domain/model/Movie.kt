package com.example.submission1wahyu.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val rating: String,
    val poster: String,
    val favorite: Boolean
) : Parcelable