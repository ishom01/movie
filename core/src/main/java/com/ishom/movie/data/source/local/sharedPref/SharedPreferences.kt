package com.ishom.movie.data.source.local.sharedPref

import android.content.Context
import com.ishom.movie.data.constant.ObjLanguageId

class SharedPreferences(private val context: Context) {

    companion object {
        const val PREF_MOVIE = "sharedpref_movie"
        const val LANGUAGE = "language"
    }

    private val sharedPreferences
        get() = context.getSharedPreferences(PREF_MOVIE, Context.MODE_PRIVATE)

    var languageCode: String = ObjLanguageId.code
        set(value) {
            sharedPreferences.edit().putString(LANGUAGE, value).apply()
            field = value
        }
        get() = sharedPreferences.getString(LANGUAGE, null) ?: ObjLanguageId.code
}