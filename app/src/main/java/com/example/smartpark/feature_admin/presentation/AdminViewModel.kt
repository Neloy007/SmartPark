package com.example.smartpark.feature_admin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartpark.feature_admin.domain.model.Parking
import com.example.smartpark.feature_admin.domain.repository.AdminRepository
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
class AdminViewModel @Inject constructor(

    private val repository: AdminRepository,
    private val authRepository: AuthRepository

) : ViewModel() {

    private val _state = MutableStateFlow(AdminState())
    val state: StateFlow<AdminState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<AdminEffect>()
    val effect: SharedFlow<AdminEffect> = _effect.asSharedFlow()

    private fun updateParking(
        parking: Parking
    ) {

        viewModelScope.launch {

            _state.update {

                it.copy(
                    isLoading = true
                )

            }

            repository
                .updateParking(parking)
                .onSuccess {

                    _state.update {

                        it.copy(
                            isLoading = false,
                            selectedParking = null
                        )

                    }

                    loadParking()

                    clearForm()

                    _effect.emit(
                        AdminEffect.ParkingUpdated
                    )

                    _effect.emit(

                        AdminEffect.ShowSnackbar(
                            "Parking updated successfully."
                        )

                    )

                }
                .onFailure { exception ->

                    _state.update {

                        it.copy(
                            isLoading = false
                        )

                    }

                    _effect.emit(

                        AdminEffect.ShowSnackbar(
                            exception.message
                                ?: "Unable to update parking."
                        )

                    )

                }

        }

    }

    init {
        onEvent(AdminEvent.LoadParking)
    }

    fun onEvent(
        event: AdminEvent
    ) {

        when (event) {

            AdminEvent.LoadParking -> {
                loadParking()
            }

            AdminEvent.AddParking -> {
                addParking()
            }

            is AdminEvent.DeleteParking -> {
                deleteParking(event.parkingId)
            }

            is AdminEvent.ParkingNameChanged -> {

                _state.update {

                    it.copy(
                        parkingName = event.value
                    )

                }

            }

            is AdminEvent.AddressChanged -> {

                _state.update {

                    it.copy(
                        address = event.value
                    )

                }

            }

            is AdminEvent.LatitudeChanged -> {

                _state.update {

                    it.copy(
                        latitude = event.value
                    )

                }

            }

            is AdminEvent.LongitudeChanged -> {

                _state.update {

                    it.copy(
                        longitude = event.value
                    )

                }

            }

            is AdminEvent.TotalSlotsChanged -> {

                _state.update {

                    it.copy(
                        totalSlots = event.value
                    )

                }

            }

            is AdminEvent.SelectParking -> {

                _state.update {

                    it.copy(
                        selectedParking = event.parking
                    )

                }

            }

            AdminEvent.ClearSelection -> {

                _state.update {

                    it.copy(
                        selectedParking = null
                    )

                }

            }

            AdminEvent.ClearForm -> {
                clearForm()
            }

            is AdminEvent.UpdateParking -> {

                updateParking(
                    event.parking
                )

            }

            AdminEvent.Logout -> logout()


        }

    }

    private fun logout() {

        viewModelScope.launch {

            authRepository.logout()

            _effect.emit(
                AdminEffect.NavigateLogin
            )

        }

    }
    /**
     * Load all parking locations.
     */
    private fun loadParking() {

        viewModelScope.launch {

            _state.update {

                it.copy(
                    isLoading = true,
                    error = null
                )

            }

            repository
                .getParkingList()
                .onSuccess { list ->

                    _state.update {

                        it.copy(
                            isLoading = false,
                            parkingList = list
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

                        AdminEffect.ShowSnackbar(
                            exception.message ?: "Unable to load parking."
                        )

                    )

                }

        }

    }

    /**
     * Add parking.
     */
    private fun addParking() {

        val current = _state.value

        if (
            current.parkingName.isBlank() ||
            current.address.isBlank() ||
            current.latitude.isBlank() ||
            current.longitude.isBlank() ||
            current.totalSlots.isBlank()
        ) {

            viewModelScope.launch {

                _effect.emit(

                    AdminEffect.ShowSnackbar(
                        "Please fill all fields."
                    )

                )

            }

            return
        }

        viewModelScope.launch {

            _state.update {

                it.copy(
                    isLoading = true
                )

            }

            val slots = current.totalSlots.toIntOrNull() ?: 0

            val parking = Parking(

                name = current.parkingName,

                address = current.address,

                latitude = current.latitude.toDouble(),

                longitude = current.longitude.toDouble(),

                totalSlots = slots,

                availableSlots = slots

            )

            repository
                .addParking(parking)
                .onSuccess {

                    clearForm()

                    loadParking()

                    _effect.emit(
                        AdminEffect.ParkingAdded
                    )

                    _effect.emit(

                        AdminEffect.ShowSnackbar(
                            "Parking added successfully."
                        )

                    )

                }
                .onFailure { exception ->

                    _state.update {

                        it.copy(
                            isLoading = false
                        )

                    }

                    _effect.emit(

                        AdminEffect.ShowSnackbar(
                            exception.message
                                ?: "Unable to add parking."
                        )

                    )

                }

        }

    }

    /**
     * Delete parking.
     */
    private fun deleteParking(
        parkingId: String
    ) {

        viewModelScope.launch {

            repository
                .deleteParking(parkingId)
                .onSuccess {

                    loadParking()

                    _effect.emit(
                        AdminEffect.ParkingDeleted
                    )

                    _effect.emit(

                        AdminEffect.ShowSnackbar(
                            "Parking deleted."
                        )

                    )

                }
                .onFailure { exception ->

                    _effect.emit(

                        AdminEffect.ShowSnackbar(
                            exception.message
                                ?: "Unable to delete parking."
                        )

                    )

                }

        }

    }

    /**
     * Reset form.
     */
    private fun clearForm() {

        _state.update {

            it.copy(

                parkingName = "",

                address = "",

                latitude = "",

                longitude = "",

                totalSlots = "",

                selectedParking = null,

                isLoading = false

            )

        }

    }

}