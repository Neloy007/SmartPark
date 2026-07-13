package com.example.smartpark.feature_auth.presentation.register

sealed interface RegisterEvent {

    data class NameChanged(
        val name: String
    ) : RegisterEvent

    data class EmailChanged(
        val email: String
    ) : RegisterEvent

    data class PhoneChanged(
        val phone: String
    ) : RegisterEvent

    data class VehicleChanged(
        val vehicleNumber: String
    ) : RegisterEvent

    data class PasswordChanged(
        val password: String
    ) : RegisterEvent

    data class ConfirmPasswordChanged(
        val confirmPassword: String
    ) : RegisterEvent

    data object RegisterClicked : RegisterEvent

    data object LoginClicked : RegisterEvent

}