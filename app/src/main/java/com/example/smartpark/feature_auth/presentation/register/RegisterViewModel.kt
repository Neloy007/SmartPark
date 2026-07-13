package com.example.smartpark.feature_auth.presentation.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartpark.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<RegisterEffect>()
    val effect: SharedFlow<RegisterEffect> = _effect.asSharedFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {

            is RegisterEvent.NameChanged -> {
                _state.update {
                    it.copy(
                        name = event.name,
                        nameError = null
                    )
                }
            }

            is RegisterEvent.EmailChanged -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        emailError = null
                    )
                }
            }

            is RegisterEvent.PhoneChanged -> {
                _state.update {
                    it.copy(
                        phone = event.phone,
                        phoneError = null
                    )
                }
            }

            is RegisterEvent.VehicleChanged -> {
                _state.update {
                    it.copy(
                        vehicleNumber = event.vehicleNumber,
                        vehicleError = null
                    )
                }
            }

            is RegisterEvent.PasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        passwordError = null
                    )
                }
            }

            is RegisterEvent.ConfirmPasswordChanged -> {
                _state.update {
                    it.copy(
                        confirmPassword = event.confirmPassword,
                        confirmPasswordError = null
                    )
                }
            }

            RegisterEvent.RegisterClicked -> {
                register()
            }

            RegisterEvent.LoginClicked -> {
                viewModelScope.launch {
                    _effect.emit(RegisterEffect.NavigateLogin)
                }
            }
        }
    }

    private fun register() {

        val state = _state.value

        when {

            state.name.isBlank() -> {
                _state.update {
                    it.copy(nameError = "Enter your full name")
                }
                return
            }

            state.email.isBlank() -> {
                _state.update {
                    it.copy(emailError = "Enter your email")
                }
                return
            }

            !Patterns.EMAIL_ADDRESS
                .matcher(state.email)
                .matches() -> {

                _state.update {
                    it.copy(emailError = "Invalid email address")
                }
                return
            }

            state.phone.isBlank() -> {
                _state.update {
                    it.copy(phoneError = "Enter your phone number")
                }
                return
            }

            state.vehicleNumber.isBlank() -> {
                _state.update {
                    it.copy(vehicleError = "Enter vehicle number")
                }
                return
            }

            state.password.length < 6 -> {
                _state.update {
                    it.copy(passwordError = "Password must be at least 6 characters")
                }
                return
            }

            state.password != state.confirmPassword -> {
                _state.update {
                    it.copy(confirmPasswordError = "Passwords do not match")
                }
                return
            }
        }

        viewModelScope.launch {

            _state.update {
                it.copy(isLoading = true)
            }

            val result = repository.register(
                name = state.name,
                email = state.email,
                phone = state.phone,
                vehicleNumber = state.vehicleNumber,
                password = state.password
            )

            result
                .onSuccess {

                    _effect.emit(
                        RegisterEffect.NavigateHome
                    )

                }
                .onFailure { exception ->

                    _effect.emit(
                        RegisterEffect.ShowSnackbar(
                            exception.message ?: "Registration failed."
                        )
                    )

                }

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}