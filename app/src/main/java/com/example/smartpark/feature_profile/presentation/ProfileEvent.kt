package com.example.smartpark.feature_profile.presentation

import com.example.smartpark.feature_auth.data.model.User

sealed interface ProfileEvent {

    /**
     * Load current user's profile
     */
    data object LoadProfile : ProfileEvent

    /**
     * Save updated profile
     */
    data class UpdateProfile(
        val user: User
    ) : ProfileEvent

    /**
     * Logout current user
     */
    data object Logout : ProfileEvent

    /**
     * Refresh profile from Firestore
     */
    data object Refresh : ProfileEvent

}