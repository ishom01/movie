package com.ishom.movie.data.source.remote

import com.ishom.movie.data.source.remote.api.ApiInterface
import com.ishom.movie.data.source.remote.response.MovieDetailResponse
import com.ishom.movie.data.source.remote.response.MovieResponse
import com.ishom.movie.data.source.remote.response.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

class MovieRemoteDataSourceImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    @Named("api-key") private val apiKey: String,
): MovieRemoteDataSource {
    override suspend fun getNowPlayingMovies(page: Int?): Flow<Response<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiInterface.getNowPlayingMovies(
                    apiKey = apiKey,
                    page = page ?: 1
                ).execute()
                emit(Response.Success(response.body()?.results ?: listOf()))
            } catch (e: Exception) {
                emit(Response.Error(e.message ?: ""))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getPopularMovies(page: Int?): Flow<Response<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiInterface.getPopularMovies(
                    apiKey = apiKey,
                    page = page ?: 1
                ).execute()
                emit(Response.Success(response.body()?.results ?: listOf()))
            } catch (e: Exception) {
                emit(Response.Error(e.message ?: ""))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUpcomingMovies(page: Int?): Flow<Response<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiInterface.getUpcomingMovies(
                    apiKey = apiKey,
                    page = page ?: 1
                ).execute()
                emit(Response.Success(response.body()?.results ?: listOf()))
            } catch (e: Exception) {
                emit(Response.Error(e.message ?: ""))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getSearchMovies(
        query: String,
        page: Int?
    ): Flow<Response<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiInterface.getSearchMovies(
                    apiKey = apiKey,
                    searchKey = query,
                    page = page ?: 1
                ).execute()
                emit(Response.Success(response.body()?.results ?: listOf()))
            } catch (e: Exception) {
                emit(Response.Error(e.message ?: ""))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDetailMovie(id: Int): Flow<Response<MovieDetailResponse>> {
        return flow {
            try {
                val response = apiInterface.getDetailMovie(
                    apiKey = apiKey,
                    id = id
                ).execute()
                response.body()?.let {
                    emit(Response.Success(it))
                } ?: run {
                    emit(Response.Empty)
                }
            } catch (e: Exception) {
                emit(Response.Error(e.message ?: ""))
            }
        }.flowOn(Dispatchers.IO)
    }


}