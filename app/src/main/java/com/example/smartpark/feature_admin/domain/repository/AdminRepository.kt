package com.example.smartpark.feature_admin.domain.repository

import com.example.smartpark.feature_admin.domain.model.Parking

interface AdminRepository {

    suspend fun addParking(
        parking: Parking
    ): Result<Unit>

    suspend fun getParkingList():
            Result<List<Parking>>

    suspend fun updateParking(
        parking: Parking
    ): Result<Unit>

    suspend fun deleteParking(
        id: String
    ): Result<Unit>

}