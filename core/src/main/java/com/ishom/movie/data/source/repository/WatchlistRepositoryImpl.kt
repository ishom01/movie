package com.ishom.movie.data.source.repository

import com.ishom.movie.domain.model.Movie
import com.ishom.movie.domain.model.MovieDetail
import com.ishom.movie.data.source.Resource
import com.ishom.movie.data.source.local.WatchlistLocalDataSource
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
        localDataSource.insert(DataMapper.parseDetailToEntity(movie))
    }

    override suspend fun remove(id: Int) = withContext(Dispatchers.IO) {
        localDataSource.remove(id)
    }

    override fun all(): Flow<Resource<List<Movie>>> = flow{
        localDataSource.all().collect {
            emit(Resource.Success(DataMapper.parseListEntitiesToListDomains(it)))
        }
    }.flowOn(Dispatchers.IO)
}