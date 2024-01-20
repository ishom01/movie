package com.ishom.movie.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watchList")
data class WatchlistEntity(
    @PrimaryKey
    var id: Int,
    var name: String,
    var path: String,
    var overview: String
)
