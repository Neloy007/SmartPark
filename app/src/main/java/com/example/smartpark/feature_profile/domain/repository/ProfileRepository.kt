package com.example.smartpark.feature_profile.domain.repository

import com.example.smartpark.feature_auth.data.model.User

interface ProfileRepository {

    suspend fun getProfile(): Result<User>

    suspend fun updateProfile(
        user: User
    ): Result<Unit>

    suspend fun logout()

}