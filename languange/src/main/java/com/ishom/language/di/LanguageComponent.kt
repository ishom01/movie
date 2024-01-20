package com.ishom.language.di

import android.content.Context
import com.ishom.app.di.LanguageModuleDependencies
import com.ishom.language.presentation.LanguageActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [LanguageModuleDependencies::class])
interface LanguageComponent {
    fun inject(activity: LanguageActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(languageModuleDependencies: LanguageModuleDependencies): Builder
        fun build(): LanguageComponent
    }
}