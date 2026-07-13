package com.example.smartpark.feature_home.presentation

import com.example.smartpark.feature_auth.data.model.User

data class HomeState(

    val isLoading: Boolean = false,

    val currentUser: User? = null,

    val welcomeMessage: String = "",

    val error: String? = null

)