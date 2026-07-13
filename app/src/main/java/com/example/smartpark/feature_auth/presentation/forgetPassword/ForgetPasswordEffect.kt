package com.example.smartpark.feature_auth.presentation.forgetPassword

sealed interface ForgotPasswordEffect {

    /**
     * Navigate back to Login screen.
     */
    data object NavigateToLogin : ForgotPasswordEffect

    /**
     * Show success or error message.
     */
    data class ShowSnackbar(
        val message: String
    ) : ForgotPasswordEffect

}