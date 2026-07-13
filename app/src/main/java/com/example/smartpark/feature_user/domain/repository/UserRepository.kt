package com.example.smartpark.feature_user.domain.repository

import com.example.smartpark.feature_auth.data.model.User

interface UserRepository {

    suspend fun getCurrentUser(): Result<User>

    suspend fun updateProfile(
        user: User
    ): Result<Unit>

}