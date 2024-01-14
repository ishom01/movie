package com.ishom.movie.data.source.remote.local

import com.ishom.movie.data.source.remote.local.entity.WatchlistEntity
import com.ishom.movie.data.source.remote.local.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WatchlistLocalDataSourceImpl @Inject constructor(
    private val db: AppDatabase
): WatchlistLocalDataSource {
    override fun all(): Flow<List<WatchlistEntity>> = db.watchListDao.all()

    override suspend fun insert(entity: WatchlistEntity) = db.watchListDao.insert(entity)

    override suspend fun remove(id: Int) = db.watchListDao.remove(id)

    override fun isFavorite(id: Int): Flow<Boolean> = flow {
        emit(db.watchListDao.findBy(id) != null)
    }.flowOn(Dispatchers.IO)
}