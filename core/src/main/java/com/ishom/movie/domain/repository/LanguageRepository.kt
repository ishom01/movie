package com.ishom.movie.domain.repository

import com.ishom.movie.domain.model.Language
import kotlinx.coroutines.flow.Flow

interface LanguageRepository {
    fun selectedLanguage(language: Language)
    fun all(): Flow<List<Language>>
}