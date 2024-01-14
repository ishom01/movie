package com.ishom.movie.data.source.remote

import com.ishom.movie.data.source.remote.response.MovieDetailResponse
import com.ishom.movie.data.source.remote.response.MovieResponse
import com.ishom.movie.data.source.remote.response.Response
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    suspend fun getNowPlayingMovies(page: Int? = null): Flow<Response<List<MovieResponse>>>
    suspend fun getPopularMovies(page: Int? = null): Flow<Response<List<MovieResponse>>>
    suspend fun getUpcomingMovies(page: Int? = null): Flow<Response<List<MovieResponse>>>
    suspend fun getSearchMovies(query: String, page: Int? = null):
            Flow<Response<List<MovieResponse>>>
    suspend fun getDetailMovie(id: Int): Flow<Response<MovieDetailResponse>>
}