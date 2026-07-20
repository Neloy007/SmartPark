package com.example.smartpark.feature_home.domain.repository

import com.example.smartpark.feature_home.domain.model.ParkingSpot

interface ParkingRepository {

    suspend fun getNearbyParking(): Result<List<ParkingSpot>>

    suspend fun getParkingById(
        parkingId: String
    ): Result<ParkingSpot>

}