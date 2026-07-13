package com.example.smartpark.feature_auth.domain.repository

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): Result<Unit>

    suspend fun register(
        name: String,
        email: String,
        phone: String,
        vehicleNumber: String,
        password: String
    ): Result<Unit>

    suspend fun sendPasswordResetEmail(
        email: String
    ): Result<Unit>

    suspend fun logout()

    fun isLoggedIn(): Boolean

}