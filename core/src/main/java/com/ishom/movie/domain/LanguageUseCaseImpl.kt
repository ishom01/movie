package com.ishom.movie.domain

import com.ishom.movie.domain.model.Language
import com.ishom.movie.domain.repository.LanguageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LanguageUseCaseImpl @Inject constructor(
    private val repo: LanguageRepository
): LanguageUseCase {
    override fun setLanguage(lang: Language) {
        repo.selectedLanguage(lang)
    }

    override fun all(): Flow<List<Language>> = repo.all()
}