package com.example.smartpark.feature_map.domain.repository


import com.example.smartpark.feature_map.domain.model.ParkingLocation


interface MapRepository {


    suspend fun getParkingLocations():
            Result<List<ParkingLocation>>

}