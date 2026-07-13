package com.example.smartpark.feature_auth.presentation.register

sealed interface RegisterEffect {

    /**
     * Navigate to Home after successful registration
     */
    data object NavigateHome : RegisterEffect

    /**
     * Navigate back to Login screen
     */
    data object NavigateLogin : RegisterEffect

    /**
     * Show a Snackbar message
     */
    data class ShowSnackbar(
        val message: String
    ) : RegisterEffect

}