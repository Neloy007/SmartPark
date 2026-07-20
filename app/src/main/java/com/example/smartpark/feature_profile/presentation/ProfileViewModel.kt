package com.example.smartpark.feature_profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartpark.feature_profile.domain.repository.ProfileRepository
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
class ProfileViewModel @Inject constructor(

    private val repository: ProfileRepository

) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ProfileEffect>()
    val effect: SharedFlow<ProfileEffect> = _effect.asSharedFlow()

    init {
        onEvent(ProfileEvent.LoadProfile)
    }

    fun onEvent(
        event: ProfileEvent
    ) {

        when (event) {

            ProfileEvent.LoadProfile -> {
                loadProfile()
            }

            ProfileEvent.Refresh -> {
                loadProfile()
            }

            is ProfileEvent.UpdateProfile -> {
                updateProfile(event.user)
            }

            ProfileEvent.Logout -> {
                logout()
            }

        }

    }

    /**
     * Load current user's profile
     * from Firestore.
     */
    private fun loadProfile() {

        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            repository
                .getProfile()
                .onSuccess { user ->

                    _state.update {

                        it.copy(
                            isLoading = false,
                            user = user
                        )

                    }

                }
                .onFailure { exception ->

                    _state.update {

                        it.copy(
                            isLoading = false,
                            error = exception.message
                        )

                    }

                    _effect.emit(
                        ProfileEffect.ShowSnackbar(
                            exception.message ?: "Unable to load profile."
                        )
                    )

                }

        }

    }

    /**
     * Update profile in Firestore.
     */
    private fun updateProfile(
        user: com.example.smartpark.feature_auth.data.model.User
    ) {

        viewModelScope.launch {

            _state.update {
                it.copy(
                    isUpdating = true
                )
            }

            repository
                .updateProfile(user)
                .onSuccess {

                    _state.update {

                        it.copy(
                            isUpdating = false,
                            user = user
                        )

                    }

                    _effect.emit(
                        ProfileEffect.ProfileUpdated
                    )

                    _effect.emit(
                        ProfileEffect.ShowSnackbar(
                            "Profile updated successfully."
                        )
                    )

                }
                .onFailure { exception ->

                    _state.update {

                        it.copy(
                            isUpdating = false
                        )

                    }

                    _effect.emit(
                        ProfileEffect.ShowSnackbar(
                            exception.message
                                ?: "Unable to update profile."
                        )
                    )

                }

        }

    }

    /**
     * Logout current user.
     */
    private fun logout() {

        viewModelScope.launch {

            repository.logout()

            _effect.emit(
                ProfileEffect.NavigateLogin
            )

        }

    }

}