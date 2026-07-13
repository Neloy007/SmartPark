package com.example.smartpark.feature_home.presentation

sealed interface HomeEvent {

    data object LoadUser : HomeEvent

    data object Refresh : HomeEvent

    data object LogoutClicked : HomeEvent

}