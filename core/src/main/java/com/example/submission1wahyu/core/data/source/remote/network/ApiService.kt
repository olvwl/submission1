package com.example.submission1wahyu.core.data.source.remote.network

import com.example.submission1wahyu.core.data.source.remote.network.NetwokInfo.API_KEY
import com.example.submission1wahyu.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovieData(
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("page") page: Int = 1
    ): ListMovieResponse
}