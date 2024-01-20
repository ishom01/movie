package com.ishom.movie.domain

import com.ishom.movie.domain.model.Language
import kotlinx.coroutines.flow.Flow

interface LanguageUseCase {

    fun setLanguage(lang: Language)
    fun all(): Flow<List<Language>>
}