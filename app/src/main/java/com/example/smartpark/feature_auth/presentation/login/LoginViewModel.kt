package com.example.smartpark.feature_auth.presentation.login

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
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<LoginEffect>()
    val effect: SharedFlow<LoginEffect> = _effect.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.EmailChanged -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        emailError = null
                    )
                }
            }

            is LoginEvent.PasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        passwordError = null
                    )
                }
            }

            LoginEvent.LoginClicked -> {
                login()
            }

            LoginEvent.RegisterClicked -> {
                viewModelScope.launch {
                    _effect.emit(LoginEffect.NavigateToRegister)
                }
            }

            LoginEvent.ForgotPasswordClicked -> {
                viewModelScope.launch {
                    _effect.emit(LoginEffect.NavigateToForgotPassword)
                }
            }
        }
    }

    private fun login() {

        val email = _state.value.email.trim()
        val password = _state.value.password

        var hasError = false

        if (email.isBlank()) {
            _state.update {
                it.copy(emailError = "Email is required")
            }
            hasError = true
        }

        if (password.isBlank()) {
            _state.update {
                it.copy(passwordError = "Password is required")
            }
            hasError = true
        }

        if (hasError) return

        viewModelScope.launch {

            _state.update {
                it.copy(isLoading = true)
            }

            val result = repository.login(
                email = email,
                password = password
            )

            result
                .onSuccess {

                    _effect.emit(
                        LoginEffect.NavigateToHome
                    )

                }
                .onFailure { exception ->

                    _effect.emit(
                        LoginEffect.ShowSnackbar(
                            exception.message ?: "Login failed."
                        )
                    )

                }

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}