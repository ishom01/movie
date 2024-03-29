package com.ishom.movie.di

import com.ishom.movie.domain.FavoriteUseCase
import com.ishom.movie.domain.FavoriteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    @Binds
    fun provideFavoriteUseCase(useCase: FavoriteUseCaseImpl): FavoriteUseCase
}