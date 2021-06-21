package com.example.submission1wahyu.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submission1wahyu.core.domain.usecase.MovieUseCase

class MovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}