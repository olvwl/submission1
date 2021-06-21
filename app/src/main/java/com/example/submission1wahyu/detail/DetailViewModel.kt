package com.example.submission1wahyu.detail

import androidx.lifecycle.ViewModel
import com.example.submission1wahyu.core.domain.model.Movie
import com.example.submission1wahyu.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) = movieUseCase.setFavMovie(movie, newStatus)
}