package com.ishom.movie.domain

import com.ishom.movie.domain.model.Movie
import com.ishom.movie.domain.model.MovieDetail
import com.ishom.movie.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getNowPlaying(page: Int? = null): Flow<Resource<List<Movie>>>
    fun getPopular(page: Int? = null): Flow<Resource<List<Movie>>>
    fun getUpcoming(page: Int? = null): Flow<Resource<List<Movie>>>
    fun getSearchMovie(query: String, page: Int? = null): Flow<Resource<List<Movie>>>
    fun getDetail(id: Int): Flow<Resource<MovieDetail>>

    suspend fun checkFavorite(movieDetail: MovieDetail)
}