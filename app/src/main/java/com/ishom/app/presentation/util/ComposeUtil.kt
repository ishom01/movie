package com.ishom.app.presentation.util

import android.util.LayoutDirection
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.core.text.layoutDirection
import java.lang.reflect.Method
import java.util.Locale

@Stable
fun Modifier.mirror(): Modifier {
    return if (Locale.getDefault().layoutDirection == LayoutDirection.RTL)
        this.scale(scaleX = -1f, scaleY = 1f)
    else
        this
}

fun getFavoriteModule(): Method? {
    return try {
        val javaClass = Class.forName("com.ishom.favorite.presentation.FavoriteScreenKt")
        javaClass.methods.find { it.name == "FavoriteScreen" }
    } catch (e: Exception) {
        null
    }
}