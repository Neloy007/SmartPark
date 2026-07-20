package com.example.smartpark.feature_profile.presentation

sealed interface ProfileEffect {

    /**
     * Navigate back to Login screen
     * after logout or session expiration.
     */
    data object NavigateLogin : ProfileEffect

    /**
     * Show one-time Snackbar message.
     */
    data class ShowSnackbar(
        val message: String
    ) : ProfileEffect

    /**
     * Profile updated successfully.
     */
    data object ProfileUpdated : ProfileEffect

}