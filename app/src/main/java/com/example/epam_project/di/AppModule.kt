package com.example.epam_project.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.epam_project.data.ArticleDao
import com.example.epam_project.data.ArticleDatabase
import com.example.epam_project.network.ArticleApiService
import com.example.epam_project.network.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ArticleApiService = RetrofitInstance.api

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ArticleDatabase =
        ArticleDatabase.getInstance(context)

    @Provides
    fun provideArticleDao(db: ArticleDatabase): ArticleDao = db.articleDao()

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}
