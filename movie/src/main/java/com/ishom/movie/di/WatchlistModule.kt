package com.ishom.movie.di

import com.ishom.movie.data.source.remote.local.WatchlistLocalDataSource
import com.ishom.movie.data.source.remote.local.WatchlistLocalDataSourceImpl
import com.ishom.movie.data.source.repository.watchlist.WatchlistRepositoryImpl
import com.ishom.movie.domain.repository.WatchlistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface WatchlistModule {
    @Binds
    fun provideWatchlistLocalDataStore(
        local: WatchlistLocalDataSourceImpl
    ): WatchlistLocalDataSource

    @Binds
    fun provideWatchListRepository(repository: WatchlistRepositoryImpl): WatchlistRepository
}