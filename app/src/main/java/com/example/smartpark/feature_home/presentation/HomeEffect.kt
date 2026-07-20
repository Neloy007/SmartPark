package com.example.smartpark.feature_home.presentation

sealed interface HomeEffect {

    data object NavigateLogin : HomeEffect

    data object NavigateProfile : HomeEffect

    data object NavigateNotifications : HomeEffect

    data class NavigateParkingDetails(
        val parkingId: String
    ) : HomeEffect

    data object NavigateParkingList : HomeEffect

    data class ShowSnackbar(
        val message: String
    ) : HomeEffect

}