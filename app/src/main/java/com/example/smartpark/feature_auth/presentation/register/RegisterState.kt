package com.example.smartpark.feature_auth.presentation.register

data class RegisterState(

    val name: String = "",

    val email: String = "",

    val phone: String = "",

    val vehicleNumber: String = "",

    val password: String = "",

    val confirmPassword: String = "",

    val isLoading: Boolean = false,

    val nameError: String? = null,

    val emailError: String? = null,

    val phoneError: String? = null,

    val vehicleError: String? = null,

    val passwordError: String? = null,

    val confirmPasswordError: String? = null

)