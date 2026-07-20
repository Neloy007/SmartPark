package com.example.smartpark.feature_profile.presentation

import com.example.smartpark.feature_auth.data.model.User

data class ProfileState(

    val isLoading: Boolean = false,

    val user: User? = null,

    val isUpdating: Boolean = false,

    val error: String? = null

)