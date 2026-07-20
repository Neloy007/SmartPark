package com.example.smartpark.feature_admin.domain.model


data class Parking(

    val id: String = "",

    val name: String = "",

    val address: String = "",

    val latitude: Double = 0.0,

    val longitude: Double = 0.0,

    val totalSlots: Int = 0,

    val availableSlots: Int = 0,

    val createdBy: String = "",

    val createdAt: Long = System.currentTimeMillis()

)