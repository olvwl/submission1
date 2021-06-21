package com.example.submission1wahyu.core.domain.usecase

import com.example.submission1wahyu.core.data.Resource
import com.example.submission1wahyu.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavMovie(): Flow<List<Movie>>
    fun setFavMovie(movie: Movie, state: Boolean)
}