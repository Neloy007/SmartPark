package com.example.smartpark.feature_home.presentation

sealed interface HomeEffect {

    data object NavigateLogin : HomeEffect

    data class ShowSnackbar(
        val message: String
    ) : HomeEffect

}