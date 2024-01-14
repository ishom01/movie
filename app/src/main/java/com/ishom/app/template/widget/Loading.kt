package com.ishom.app.template.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Loading() {
    Box(modifier = Modifier
        .fillMaxSize()
        .widthIn(min = 320.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}