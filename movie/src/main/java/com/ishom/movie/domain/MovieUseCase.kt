package com.ishom.movie.domain

import com.ishom.movie.domain.model.Movie
import com.ishom.movie.domain.model.MovieDetail
import com.ishom.movie.data.source.repository.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getNowPlaying(page: Int? = null): Flow<Resource<List<Movie>>>
    fun getPopular(page: Int? = null): Flow<Resource<List<Movie>>>
    fun getUpcoming(page: Int? = null): Flow<Resource<List<Movie>>>
    fun getSearchMovie(query: String, page: Int? = null): Flow<Resource<List<Movie>>>
    fun getDetail(id: Int): Flow<Resource<MovieDetail>>
    fun getWatchList(): Flow<Resource<List<Movie>>>
    fun isFavorite(id: Int): Flow<Boolean>
    suspend fun renameFavorite(id: Int)
    suspend fun addFavorite(movieDetail: MovieDetail)
}