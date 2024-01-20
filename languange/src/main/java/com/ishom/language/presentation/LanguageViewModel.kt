package com.ishom.language.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ishom.app.presentation.UiState
import com.ishom.movie.domain.LanguageUseCase
import com.ishom.movie.domain.model.Language
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class LanguageViewModel(private val useCase: LanguageUseCase): ViewModel() {

    val languageState = MutableStateFlow(LanguageUiState())

    init {
        getData()
    }

    private fun getData() {
        useCase.all().onEach { languages ->
            languageState.update {
                it.copy(languages = UiState.default<List<Language>>().copy(data = languages))
            }
        }.launchIn(viewModelScope)
    }

    fun setLanguage(language: Language) {
        useCase.setLanguage(language)
        getData()
        languageState.update {
            it.copy(isSuccessChange = true)
        }
    }
}