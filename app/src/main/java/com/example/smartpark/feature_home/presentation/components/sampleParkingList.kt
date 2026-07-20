package com.example.smartpark.feature_home.presentation.components

import com.example.smartpark.R

val sampleParkingList = listOf(

    ParkingUiModel(
        id = "1",
        name = "City Center Parking",
        address = "Banani, Dhaka",
        price = "৳60/hr",
        distance = "0.7 km",
        rating = 4.8,
        availableSlots = 18,
        image = R.drawable.placeholder
    ),

    ParkingUiModel(
        id = "2",
        name = "Jamuna Future Park",
        address = "Kuril, Dhaka",
        price = "৳80/hr",
        distance = "1.3 km",
        rating = 4.7,
        availableSlots = 32,
        image = R.drawable.placeholder
    ),

    ParkingUiModel(
        id = "3",
        name = "Airport Parking",
        address = "Airport Road",
        price = "৳100/hr",
        distance = "3.2 km",
        rating = 4.9,
        availableSlots = 45,
        image = R.drawable.placeholder
    )

)