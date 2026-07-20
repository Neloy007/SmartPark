package com.example.smartpark.feature_map.domain.model


data class ParkingLocation(

    val id: String = "",

    val name: String = "",

    val address: String = "",

    val latitude: Double = 0.0,

    val longitude: Double = 0.0,

    val totalSlots: Int = 0,

    val availableSlots: Int = 0

)