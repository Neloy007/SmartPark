package com.example.smartpark.feature_home.presentation

import com.example.smartpark.feature_auth.data.model.User
import com.example.smartpark.feature_home.domain.model.ParkingSpot

data class HomeState(

    val isLoading: Boolean = false,

    val currentUser: User? = null,

    val welcomeMessage: String = "",

    val searchQuery: String = "",

    val selectedCategory: String = "All",

    val parkingList: List<ParkingSpot> = emptyList(),

    val filteredParkingList: List<ParkingSpot> = emptyList(),

    val error: String? = null

)