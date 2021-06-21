package com.example.submission1wahyu.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("overview")
    val description: String,

    @field:SerializedName("release_date")
    val date: String,

    @field:SerializedName("vote_average")
    val rating: String,

    @field:SerializedName("poster_path")
    val poster: String
)