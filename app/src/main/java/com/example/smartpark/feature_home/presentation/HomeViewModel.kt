package com.example.smartpark.feature_home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartpark.feature_auth.domain.repository.AuthRepository
import com.example.smartpark.feature_home.domain.repository.ParkingRepository
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
    private val userRepository: UserRepository,
    private val parkingRepository: ParkingRepository

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

            HomeEvent.LoadUser -> loadHome()

            HomeEvent.Refresh -> loadHome()

            HomeEvent.LogoutClicked -> logout()

            is HomeEvent.CategorySelected -> {
                selectCategory(event.category)
            }
        }

    }

    /**
     * Loads everything required for the Home screen.
     */
    private fun loadHome() {

        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            if (!authRepository.isLoggedIn()) {

                _state.update {
                    it.copy(isLoading = false)
                }

                _effect.emit(HomeEffect.NavigateLogin)
                return@launch
            }

            loadCurrentUser()

        }

    }

    /**
     * Loads current user's profile.
     */
    private suspend fun loadCurrentUser() {

        userRepository
            .getCurrentUser()
            .onSuccess { user ->

                _state.update {
                    it.copy(
                        currentUser = user,
                        welcomeMessage = "Welcome, ${user.name}"
                    )
                }

                loadParking()

            }
            .onFailure { exception ->

                _state.update {
                    it.copy(
                        isLoading = false,
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

    /**
     * Loads nearby parking.
     */
    private suspend fun loadParking() {

        parkingRepository
            .getNearbyParking()
            .onSuccess { parkingList ->

                _state.update {

                    it.copy(
                        isLoading = false,
                        parkingList = parkingList,
                        filteredParkingList = parkingList
                    )

                }

            }
            .onFailure { exception ->

                _state.update {

                    it.copy(
                        isLoading = false,
                        parkingList = emptyList(),
                        filteredParkingList = emptyList(),
                        error = exception.message
                    )

                }

                _effect.emit(
                    HomeEffect.ShowSnackbar(
                        exception.message ?: "Unable to load nearby parking."
                    )
                )

            }

    }

    /**
     * Changes selected category.
     *
     * Filtering logic will be added later.
     */
    private fun selectCategory(category: String) {

        _state.update {
            it.copy(
                selectedCategory = category
            )
        }

        // TODO
        // filterParking(category)

    }

    /**
     * Signs out current user.
     */
    private fun logout() {

        viewModelScope.launch {

            authRepository.logout()

            _effect.emit(
                HomeEffect.NavigateLogin
            )

        }

    }

}