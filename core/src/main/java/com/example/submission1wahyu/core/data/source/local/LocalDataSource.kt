package com.example.submission1wahyu.core.data.source.local

import com.example.submission1wahyu.core.data.source.local.entity.MovieEntity
import com.example.submission1wahyu.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance
                    ?: LocalDataSource(movieDao)
            }
    }

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavMovie(): Flow<List<MovieEntity>> = movieDao.getFavMovie()

    suspend fun insertMovie(listMovie: List<MovieEntity>) = movieDao.insertMovie(listMovie)

    fun setFavMovie(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        movieDao.updateFavMovie(movie)
    }
}