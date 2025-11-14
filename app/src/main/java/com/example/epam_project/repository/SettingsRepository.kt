package com.example.epam_project.repository

import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(
    private val prefs: SharedPreferences
) {
    fun isDarkMode(): Flow<Boolean> = flow {
        emit(prefs.getBoolean("dark_mode", false))
    }

    fun setDarkMode(enabled: Boolean) {
        prefs.edit().putBoolean("dark_mode", enabled).apply()
    }
}
