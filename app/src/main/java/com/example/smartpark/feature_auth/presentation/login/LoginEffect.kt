package com.example.smartpark.feature_auth.presentation.login

sealed interface LoginEffect {

    data class ShowSnackbar(
        val message: String
    ) : LoginEffect

    data object NavigateToHome : LoginEffect

    data object NavigateToAdmin : LoginEffect

    data object NavigateToRegister : LoginEffect

    data object NavigateToForgotPassword : LoginEffect
}