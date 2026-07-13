package com.example.smartpark.feature_splash.presentaion

sealed interface SplashEvent {

    data object Initialize : SplashEvent

}