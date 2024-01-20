package com.ishom.language.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ishom.language.presentation.LanguageViewModel
import com.ishom.movie.domain.LanguageUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val useCase: LanguageUseCase):
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(LanguageViewModel::class.java) -> {
                LanguageViewModel(useCase) as T
            }
            else -> throw  Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}