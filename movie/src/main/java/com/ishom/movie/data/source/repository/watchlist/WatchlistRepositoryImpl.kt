package com.ishom.movie.data.source.repository.watchlist

import com.ishom.movie.domain.model.Movie
import com.ishom.movie.domain.model.MovieDetail
import com.ishom.movie.data.source.repository.Resource
import com.ishom.movie.data.source.remote.local.WatchlistLocalDataSource
import com.ishom.movie.domain.model.DataMapper
import com.ishom.movie.domain.repository.WatchlistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WatchlistRepositoryImpl @Inject constructor(
    private val localDataSource: WatchlistLocalDataSource,
): WatchlistRepository {
    override suspend fun insert(movie: MovieDetail) = withContext(Dispatchers.IO) {
        localDataSource.insert(DataMapper.mapDomainToEntities(movie))
    }

    override suspend fun remove(id: Int) = withContext(Dispatchers.IO) {
        localDataSource.remove(id)
    }

    override fun all(): Flow<Resource<List<Movie>>> = flow{
        localDataSource.all().collect {
            emit(Resource.Success(DataMapper.mapEntitiesToDomain(it)))
        }
    }.flowOn(Dispatchers.IO)

    override fun isFavorite(id: Int): Flow<Boolean> = localDataSource.isFavorite(id)
}