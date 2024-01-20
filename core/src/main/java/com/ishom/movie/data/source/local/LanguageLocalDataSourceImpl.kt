package com.ishom.movie.data.source.local

import com.ishom.movie.data.constant.ObjLanguageAr
import com.ishom.movie.data.constant.ObjLanguageEn
import com.ishom.movie.data.constant.ObjLanguageEs
import com.ishom.movie.data.constant.ObjLanguageId
import com.ishom.movie.data.source.local.sharedPref.SharedPreferences
import com.ishom.movie.domain.model.DataMapper
import com.ishom.movie.domain.model.Language
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LanguageLocalDataSourceImpl @Inject constructor(
    private val preferences: SharedPreferences
): LanguageLocalDataSource {

    override fun setLanguage(code: String) {
        preferences.languageCode = code
    }

    override fun all(): Flow<List<Language>> = flow {
        val languages = DataMapper.parseLanguageToDomain(
            listOf(
                ObjLanguageId,
                ObjLanguageEn,
                ObjLanguageEs,
                ObjLanguageAr
            )
        )
        languages.firstOrNull { it.code == preferences.languageCode }?.isSelected = true
        emit(languages)
    }.flowOn(Dispatchers.IO)
}