package com.example.smartpark.feature_map.presentation


import com.example.smartpark.feature_map.domain.model.ParkingLocation


sealed interface MapEvent {


    data object LoadParking : MapEvent


    data class ParkingSelected(
        val parking: ParkingLocation
    ): MapEvent


}
