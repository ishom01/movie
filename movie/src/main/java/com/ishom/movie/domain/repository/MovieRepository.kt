package com.ishom.movie.domain.repository

import com.ishom.movie.domain.model.Movie
import com.ishom.movie.domain.model.MovieDetail
import com.ishom.movie.data.source.repository.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getNowPlaying(page: Int? = null): Flow<Resource<List<Movie>>>
    fun getPopular(page: Int? = null): Flow<Resource<List<Movie>>>
    fun getUpcomingMovie(page: Int? = null): Flow<Resource<List<Movie>>>
    fun getSearchMovie(query: String, page: Int? = null): Flow<Resource<List<Movie>>>
    fun getDetail(id: Int): Flow<Resource<MovieDetail>>
}