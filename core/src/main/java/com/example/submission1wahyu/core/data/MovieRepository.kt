package com.example.submission1wahyu.core.data

import com.example.submission1wahyu.core.data.source.local.LocalDataSource
import com.example.submission1wahyu.core.data.source.remote.RemoteDataSource
import com.example.submission1wahyu.core.data.source.remote.network.ApiResponse
import com.example.submission1wahyu.core.data.source.remote.response.MovieResponse
import com.example.submission1wahyu.core.domain.model.Movie
import com.example.submission1wahyu.core.domain.repository.IMovieRepository
import com.example.submission1wahyu.core.utils.AppExecutors
import com.example.submission1wahyu.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance
                    ?: MovieRepository(
                        remoteData,
                        localData,
                        appExecutors
                    )
            }
    }

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : com.example.submission1wahyu.core.data.NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> = remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val listMovie = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(listMovie)
            }
        }.asFlow()

    override fun getFavMovie(): Flow<List<Movie>> {
        return localDataSource.getFavMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavMovie(movie: Movie, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavMovie(tourismEntity, state) }
    }
}