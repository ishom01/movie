package com.ishom.movie.di

import com.ishom.movie.data.source.local.LanguageLocalDataSource
import com.ishom.movie.data.source.local.LanguageLocalDataSourceImpl
import com.ishom.movie.data.source.repository.LanguageRepositoryImpl
import com.ishom.movie.domain.LanguageUseCase
import com.ishom.movie.domain.LanguageUseCaseImpl
import com.ishom.movie.domain.repository.LanguageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LanguageModule {

    @Binds
    fun provideLanguageLocalDataStore(local: LanguageLocalDataSourceImpl): LanguageLocalDataSource

    @Binds
    fun provideLanguageRepository(repo: LanguageRepositoryImpl): LanguageRepository

    @Binds
    fun provideLanguageUseCase(useCase: LanguageUseCaseImpl): LanguageUseCase
}