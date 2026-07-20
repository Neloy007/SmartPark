package com.example.smartpark.feature_home.domain.model

data class ParkingSpot(

    val id: String = "",

    val name: String = "",

    val address: String = "",

    val imageUrl: String = "",

    val latitude: Double = 0.0,

    val longitude: Double = 0.0,

    val totalSlots: Int = 0,

    val availableSlots: Int = 0,

    val pricePerHour: Double = 0.0,

    val distanceKm: Double = 0.0,

    val rating: Double = 0.0,

    val isOpen: Boolean = true,

    val hasEvCharging: Boolean = false,

    val hasCCTV: Boolean = false,

    val hasSecurity: Boolean = false,

    val hasRoof: Boolean = false
)