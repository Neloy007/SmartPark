package com.example.smartpark.feature_admin.presentation

import com.example.smartpark.feature_admin.domain.model.Parking

data class AdminState(

    val isLoading: Boolean = false,

    val parkingList: List<Parking> = emptyList(),

    val selectedParking: Parking? = null,

    // Form Fields
    val parkingName: String = "",

    val address: String = "",

    val latitude: String = "",

    val longitude: String = "",

    val totalSlots: String = "",

    val error: String? = null

)