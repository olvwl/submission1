package com.example.submission1wahyu.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class MovieEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "overview")
    var description: String,

    @ColumnInfo(name = "release_date")
    var date: String,

    @ColumnInfo(name = "vote_average")
    var rating: String,

    @ColumnInfo(name = "poster_path")
    var poster: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false

): Parcelable