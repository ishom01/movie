package com.ishom.language.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ishom.app.di.LanguageModuleDependencies
import com.ishom.language.di.DaggerLanguageComponent
import com.ishom.language.di.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class LanguageActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: LanguageViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerLanguageComponent.builder()
            .context(this)
            .appDependencies(EntryPointAccessors.fromApplication(
                this,
                LanguageModuleDependencies::class.java,
            ))
            .build()
            .inject(this)

        setContent {
            LanguageScreen(
                state = viewModel.languageState.collectAsStateWithLifecycle(),
                onBackPressed = {
                    finish()
                },
                onSelectedLanguage = {
                    viewModel.setLanguage(it)
                }
            )
        }
    }
}