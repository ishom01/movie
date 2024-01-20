package com.ishom.movie.domain.repository

import com.ishom.movie.domain.model.Movie
import com.ishom.movie.domain.model.MovieDetail
import com.ishom.movie.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface WatchlistRepository {
    suspend fun insert(movie: MovieDetail)
    suspend fun remove(id: Int)
    fun all(): Flow<Resource<List<Movie>>>
}