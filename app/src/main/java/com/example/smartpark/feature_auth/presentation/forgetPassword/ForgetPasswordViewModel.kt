package com.example.smartpark.feature_auth.presentation.forgetPassword

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartpark.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
class ForgotPasswordViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ForgotPasswordState())
    val state: StateFlow<ForgotPasswordState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ForgotPasswordEffect>()
    val effect: SharedFlow<ForgotPasswordEffect> = _effect.asSharedFlow()

    fun onEvent(event: ForgotPasswordEvent) {
        when (event) {

            is ForgotPasswordEvent.EmailChanged -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        emailError = null
                    )
                }
            }

            ForgotPasswordEvent.ResetClicked -> {
                resetPassword()
            }

            ForgotPasswordEvent.BackToLoginClicked -> {
                viewModelScope.launch {
                    _effect.emit(
                        ForgotPasswordEffect.NavigateToLogin
                    )
                }
            }
        }
    }

    private fun resetPassword() {

        val email = _state.value.email.trim()

        if (email.isBlank()) {
            _state.update {
                it.copy(
                    emailError = "Email is required"
                )
            }
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _state.update {
                it.copy(
                    emailError = "Invalid email address"
                )
            }
            return
        }

        viewModelScope.launch {

            _state.update {
                it.copy(isLoading = true)
            }

            val result = repository.sendPasswordResetEmail(email)

            result
                .onSuccess {

                    _effect.emit(
                        ForgotPasswordEffect.ShowSnackbar(
                            "Password reset link has been sent to your email."
                        )
                    )

                    delay(1200)

                    _effect.emit(
                        ForgotPasswordEffect.NavigateToLogin
                    )

                }
                .onFailure { exception ->

                    _effect.emit(
                        ForgotPasswordEffect.ShowSnackbar(
                            exception.message ?: "Failed to send reset email."
                        )
                    )

                }

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}