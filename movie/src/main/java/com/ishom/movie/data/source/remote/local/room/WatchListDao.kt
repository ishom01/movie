package com.ishom.movie.data.source.remote.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ishom.movie.data.source.remote.local.entity.WatchlistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchListDao {

    @Query("DELETE FROM watchList WHERE id = :id")
    fun remove(id: Int)

    @Query("SELECT * FROM watchList")
    fun all(): Flow<List<WatchlistEntity>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: WatchlistEntity)

    @Query("SELECT * FROM watchlist where id = :id ORDER BY id ASC LIMIT 1")
    fun findBy(id: Int): WatchlistEntity?
}