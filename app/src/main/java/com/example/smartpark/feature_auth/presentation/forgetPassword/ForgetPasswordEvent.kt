package com.example.smartpark.feature_auth.presentation.forgetPassword

sealed interface ForgotPasswordEvent {

    /**
     * User types an email.
     */
    data class EmailChanged(
        val email: String
    ) : ForgotPasswordEvent

    /**
     * User taps "Send Reset Link".
     */
    data object ResetClicked : ForgotPasswordEvent

    /**
     * User taps "Back to Login".
     */
    data object BackToLoginClicked : ForgotPasswordEvent

}