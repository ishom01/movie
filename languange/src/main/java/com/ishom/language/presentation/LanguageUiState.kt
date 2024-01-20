package com.ishom.language.presentation

import com.ishom.app.presentation.UiState
import com.ishom.movie.domain.model.Language

data class LanguageUiState(
    val languages: UiState<List<Language>> = UiState.default(),
    val isSuccessChange: Boolean = false
)