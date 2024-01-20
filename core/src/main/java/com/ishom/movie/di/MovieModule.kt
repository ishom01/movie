package com.ishom.movie.di

import com.ishom.movie.data.source.remote.MovieRemoteDataSource
import com.ishom.movie.data.source.remote.MovieRemoteDataSourceImpl
import com.ishom.movie.domain.repository.MovieRepository
import com.ishom.movie.data.source.repository.MovieRepositoryImpl
import com.ishom.movie.domain.MovieUseCase
import com.ishom.movie.domain.MovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MovieModule {

    @Binds
    fun provideMovieRemoteDataSource(remote: MovieRemoteDataSourceImpl): MovieRemoteDataSource

    @Binds
    fun provideMovieRepository(repo: MovieRepositoryImpl): MovieRepository

    @Binds
    fun provideMovieUseCase(useCase: MovieUseCaseImpl): MovieUseCase
}