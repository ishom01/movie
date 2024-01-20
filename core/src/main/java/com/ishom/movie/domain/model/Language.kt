package com.ishom.movie.domain.model

data class Language(
    val name: String,
    val code: String,
    var isSelected: Boolean = false
)
