package com.example.smartpark.feature_home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartpark.feature_auth.domain.repository.AuthRepository
import com.example.smartpark.feature_user.domain.repository.UserRepository
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
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeEffect>()
    val effect: SharedFlow<HomeEffect> = _effect.asSharedFlow()

    init {
        onEvent(HomeEvent.LoadUser)
    }

    fun onEvent(event: HomeEvent) {

        when (event) {

            HomeEvent.LoadUser -> {
                loadUser()
            }

            HomeEvent.Refresh -> {
                loadUser()
            }

            HomeEvent.LogoutClicked -> {
                logout()
            }
        }

    }

    /**
     * Temporary implementation.
     *
     * Later this method will load the user profile
     * from Firestore through UserRepository.
     */
    private fun loadUser() {

        viewModelScope.launch {

            _state.update {
                it.copy(isLoading = true)
            }

            if (!authRepository.isLoggedIn()) {

                _state.update {
                    it.copy(isLoading = false)
                }

                _effect.emit(HomeEffect.NavigateLogin)

                return@launch
            }

            val result = userRepository.getCurrentUser()

            result
                .onSuccess { user ->

                    _state.update {

                        it.copy(
                            isLoading = false,
                            currentUser = user,
                            welcomeMessage = "Welcome, ${user.name}"
                        )

                    }

                }
                .onFailure { exception ->

                    _state.update {
                        it.copy(
                            isLoading =false,
                            error = exception.message
                        )
                    }

                    _effect.emit(
                        HomeEffect.ShowSnackbar(
                            exception.message ?: "Unable to load profile."
                        )
                    )

                }

        }

    }

    private fun logout() {

        viewModelScope.launch {

            authRepository.logout()

            _effect.emit(HomeEffect.NavigateLogin)

        }

    }

}