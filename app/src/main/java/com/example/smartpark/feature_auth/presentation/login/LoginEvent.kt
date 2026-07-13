package com.example.smartpark.feature_auth.presentation.login

sealed interface LoginEvent {

    data class EmailChanged(
        val email: String
    ) : LoginEvent

    data class PasswordChanged(
        val password: String
    ) : LoginEvent

    data object LoginClicked : LoginEvent

    data object ForgotPasswordClicked : LoginEvent

    data object RegisterClicked : LoginEvent
}