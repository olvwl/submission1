package com.example.submission1wahyu.core.utils

import com.example.submission1wahyu.core.data.source.local.entity.MovieEntity
import com.example.submission1wahyu.core.data.source.remote.response.MovieResponse
import com.example.submission1wahyu.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                description = it.description,
                date = it.date,
                rating = it.rating,
                poster = it.poster,
                favorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                description = it.description,
                date = it.date,
                rating = it.rating,
                poster = it.poster,
                favorite = it.favorite
            )
        }

    fun mapDomainToEntity(input: Movie) =
        MovieEntity(
            id = input.id,
            title = input.title,
            description = input.description,
            date = input.date,
            rating = input.rating,
            poster = input.poster,
            favorite = input.favorite
        )
}