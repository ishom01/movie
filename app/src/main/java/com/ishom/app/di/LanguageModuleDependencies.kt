package com.ishom.app.di

import com.ishom.movie.domain.LanguageUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface LanguageModuleDependencies {
    fun languageUseCase(): LanguageUseCase
}