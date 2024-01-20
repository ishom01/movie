package com.ishom.app.presentation.ui.template.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ErrorMessage(errorMessage: String) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = errorMessage,
        color = Color.Red
    )
}