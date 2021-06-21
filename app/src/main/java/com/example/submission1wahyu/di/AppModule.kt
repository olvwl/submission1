package com.example.submission1wahyu.di

import com.example.submission1wahyu.core.domain.usecase.MovieInteractor
import com.example.submission1wahyu.core.domain.usecase.MovieUseCase
import com.example.submission1wahyu.detail.DetailViewModel
import com.example.submission1wahyu.home.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}