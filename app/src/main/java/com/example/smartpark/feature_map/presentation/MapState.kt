package com.example.smartpark.feature_map.presentation


import com.example.smartpark.feature_map.domain.model.ParkingLocation


data class MapState(

    val isLoading: Boolean = false,

    val parkingList: List<ParkingLocation> = emptyList(),

    val selectedParking: ParkingLocation? = null,

    val error: String? = null

)