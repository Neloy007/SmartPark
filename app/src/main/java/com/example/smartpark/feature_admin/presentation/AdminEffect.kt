package com.example.smartpark.feature_admin.presentation

sealed interface AdminEffect {

    data class ShowSnackbar(
        val message: String
    ) : AdminEffect

    data object ParkingAdded : AdminEffect

    data object ParkingUpdated : AdminEffect

    data object ParkingDeleted : AdminEffect

    data object NavigateLogin : AdminEffect

}