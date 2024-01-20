package com.ishom.movie.domain

import com.ishom.movie.data.source.Resource
import com.ishom.movie.domain.model.Movie


interface FavoriteUseCase {
    fun all(): kotlinx.coroutines.flow.Flow<Resource<List<Movie>>>
}