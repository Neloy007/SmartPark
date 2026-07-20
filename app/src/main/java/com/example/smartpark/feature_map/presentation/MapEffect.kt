package com.example.smartpark.feature_map.presentation

sealed interface MapEffect {

    data class ShowSnackbar(
        val message: String
    ) : MapEffect

}