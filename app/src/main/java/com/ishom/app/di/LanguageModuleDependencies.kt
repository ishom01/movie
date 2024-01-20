package com.ishom.app.di

import com.ishom.movie.data.source.local.LanguageLocalDataSource
import com.ishom.movie.data.source.local.LanguageLocalDataSourceImpl
import com.ishom.movie.data.source.repository.LanguageRepositoryImpl
import com.ishom.movie.domain.LanguageUseCase
import com.ishom.movie.domain.LanguageUseCaseImpl
import com.ishom.movie.domain.repository.LanguageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface LanguageModuleDependencies {
    fun languageUseCase(): LanguageUseCase
}