package com.example.smartpark.feature_splash.presentaion

sealed interface SplashEffect {

    data class Navigate(
        val route: String
    ) : SplashEffect

}