package com.example.smartpark.feature_home.presentation.components

data class ParkingUiModel(

    val id: String,

    val name: String,

    val address: String,

    val price: String,

    val distance: String,

    val rating: Double,

    val availableSlots: Int,

    val image: Int

)