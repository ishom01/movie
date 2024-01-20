package com.ishom.language.presentation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ishom.app.presentation.MainActivity
import com.ishom.app.presentation.UiState
import com.ishom.app.presentation.ui.template.style.Fonts
import com.ishom.app.presentation.ui.template.widget.MainBar
import com.ishom.app.presentation.ui.template.widget.MainBarType
import com.ishom.core.R
import com.ishom.movie.domain.model.Language


@Composable
fun LanguageScreen(
    state: State<LanguageUiState>,
    onBackPressed: (() -> Unit)? = null,
    onSelectedLanguage: ((lang: Language) -> Unit)? = null,
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            MainBar(
                type = MainBarType.BackOnly,
                title = stringResource(id = R.string.language),
                buttonModifier = Modifier
                    .size(18.dp)
                    .padding(0.dp),
                onBackPressed = onBackPressed
            )
        },
        content = {
            if (state.value.isSuccessChange) {
                LaunchedEffect(Unit) {
                    Intent(context, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }.also { intent -> context.startActivity(intent) }
                }
            }
            LazyColumn(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp,
                    top = it.calculateTopPadding())
            ) {
                state.value.languages.data?.forEach { language ->
                    item {
                        Row(
                            modifier = Modifier
                                .height(32.dp)
                                .clickable {
                                    onSelectedLanguage?.invoke(language)
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.weight(1f),
                                text = language.name,
                                style = Fonts.Typography.body1
                            )
                            if (language.isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = stringResource(id = R.string.back),
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
