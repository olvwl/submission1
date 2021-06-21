package com.example.submission1wahyu.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submission1wahyu.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favMovie = movieUseCase.getFavMovie().asLiveData()
}