package com.example.smartpark.feature_auth.presentation.forgetPassword


data class ForgotPasswordState(

    val email: String = "",

    val isLoading: Boolean = false,

    val emailError: String? = null

)