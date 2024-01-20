package com.ishom.movie.data.source.repository

import com.ishom.movie.data.source.local.LanguageLocalDataSource
import com.ishom.movie.domain.model.Language
import com.ishom.movie.domain.repository.LanguageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor(
    private val local: LanguageLocalDataSource
): LanguageRepository {
    override fun selectedLanguage(language: Language) = local.setLanguage(language.code)

    override fun all(): Flow<List<Language>> = local.all()
}