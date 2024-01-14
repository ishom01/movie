package com.ishom.movie.data.source.repository.movie

import com.ishom.movie.domain.model.DataMapper
import com.ishom.movie.domain.model.Movie
import com.ishom.movie.domain.model.MovieDetail
import com.ishom.movie.data.source.remote.MovieRemoteDataSource
import com.ishom.movie.data.source.remote.response.MovieDetailResponse
import com.ishom.movie.data.source.remote.response.MovieResponse
import com.ishom.movie.data.source.remote.response.Response
import com.ishom.movie.data.source.repository.NetworkBoundResource
import com.ishom.movie.data.source.repository.Resource
import com.ishom.movie.domain.repository.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
): MovieRepository {

    override fun getNowPlaying(page: Int?): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<MovieResponse>, List<Movie>>() {
            override suspend fun onParse(data: List<MovieResponse>): List<Movie> =
                DataMapper.mapRemoteToDomain(data)
            override suspend fun onDefault(): List<Movie> = listOf()
            override suspend fun onCall(): Flow<Response<List<MovieResponse>>> =
                remoteDataSource.getNowPlayingMovies(page)
        }.asFlow

    override fun getPopular(page: Int?): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<MovieResponse>, List<Movie>>() {
            override suspend fun onParse(data: List<MovieResponse>): List<Movie> =
                DataMapper.mapRemoteToDomain(data)
            override suspend fun onDefault(): List<Movie> = listOf()
            override suspend fun onCall(): Flow<Response<List<MovieResponse>>> =
                remoteDataSource.getPopularMovies(page)
        }.asFlow

    override fun getUpcomingMovie(page: Int?): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<MovieResponse>, List<Movie>>() {
            override suspend fun onParse(data: List<MovieResponse>): List<Movie> =
                DataMapper.mapRemoteToDomain(data)
            override suspend fun onDefault(): List<Movie> = listOf()
            override suspend fun onCall(): Flow<Response<List<MovieResponse>>> =
                remoteDataSource.getUpcomingMovies(page)
        }.asFlow

    override fun getSearchMovie(query: String, page: Int?): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<MovieResponse>, List<Movie>>() {
            override suspend fun onParse(data: List<MovieResponse>): List<Movie> =
                DataMapper.mapRemoteToDomain(data)
            override suspend fun onDefault(): List<Movie> = listOf()
            override suspend fun onCall(): Flow<Response<List<MovieResponse>>> =
                remoteDataSource.getSearchMovies(query, page)
        }.asFlow

    override fun getDetail(id: Int): Flow<Resource<MovieDetail>> =
        object : NetworkBoundResource<MovieDetailResponse, MovieDetail>() {
            override suspend fun onParse(data: MovieDetailResponse):
                    MovieDetail = DataMapper.mapEntitiesToDomain(data)
            override suspend fun onDefault(): MovieDetail = MovieDetail()
            override suspend fun onCall(): Flow<Response<MovieDetailResponse>> {
                delay(500)
                return remoteDataSource.getDetailMovie(id)
            }

        }.asFlow
}