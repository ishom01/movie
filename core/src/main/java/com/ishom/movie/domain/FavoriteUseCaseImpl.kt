package com.ishom.movie.domain

import com.ishom.movie.data.source.Resource
import com.ishom.movie.domain.model.Movie
import com.ishom.movie.domain.repository.WatchlistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteUseCaseImpl @Inject constructor(
    private val repository: WatchlistRepository
): FavoriteUseCase {
    override fun all(): Flow<Resource<List<Movie>>> = repository.all()
}