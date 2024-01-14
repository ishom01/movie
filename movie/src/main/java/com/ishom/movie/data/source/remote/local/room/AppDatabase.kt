package com.ishom.movie.data.source.remote.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ishom.movie.data.source.remote.local.entity.WatchlistEntity

@Database(
    version = 1,
    entities = [WatchlistEntity::class],
    exportSchema = false,
)
abstract class AppDatabase: RoomDatabase() {

    abstract val watchListDao: WatchListDao
    
    companion object {
        
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movie_database.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}