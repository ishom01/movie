package com.ishom.movie.di

import android.content.Context
import com.ishom.movie.data.source.local.room.AppDatabase
import com.ishom.movie.data.source.local.sharedPref.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun providePreferences(@ApplicationContext context: Context): SharedPreferences {
        return SharedPreferences(context)
    }

    @Provides
    @Named("language-code")
    fun provideLanguageCode(@ApplicationContext context: Context): String {
        return SharedPreferences(context).languageCode
    }
}