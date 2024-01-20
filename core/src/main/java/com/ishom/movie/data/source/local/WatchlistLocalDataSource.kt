package com.ishom.movie.data.source.local

import com.ishom.movie.data.source.local.entity.WatchlistEntity
import kotlinx.coroutines.flow.Flow

interface WatchlistLocalDataSource {
    fun all(): Flow<List<WatchlistEntity>>
    suspend fun insert(entity: WatchlistEntity)
    suspend fun remove(id: Int)
    fun isFavorite(id: Int): Flow<Boolean>
}