package com.example.epam_project.repository

import com.example.epam_project.model.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor() {
    fun getProfile(): Flow<Profile> = flow {
        emit(Profile("Alisher Seitkazin", "alisherssmm@gmail.com", "https://i.pravatar.cc"))
    }
}
