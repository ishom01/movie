package com.ishom.movie.data.constant

sealed class ObjLanguage(
    val name: String,
    val code: String
)
data object ObjLanguageEn: ObjLanguage("English", "en")
data object ObjLanguageId: ObjLanguage("Indonesia", "in")
data object ObjLanguageEs: ObjLanguage("Spain", "es")
data object ObjLanguageAr: ObjLanguage("Arabic", "ar")