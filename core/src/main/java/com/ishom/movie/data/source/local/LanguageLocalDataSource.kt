package com.ishom.movie.data.source.local

import com.ishom.movie.domain.model.Language
import kotlinx.coroutines.flow.Flow


interface LanguageLocalDataSource {
    fun setLanguage(code: String)
    fun all(): Flow<List<Language>>
}