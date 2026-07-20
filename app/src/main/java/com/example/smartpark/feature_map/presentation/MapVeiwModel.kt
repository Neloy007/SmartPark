package com.example.smartpark.feature_map.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartpark.feature_map.domain.repository.MapRepository
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
class MapViewModel @Inject constructor(

    private val repository: MapRepository

) : ViewModel() {

    private val _state = MutableStateFlow(MapState())
    val state: StateFlow<MapState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<MapEffect>()
    val effect: SharedFlow<MapEffect> = _effect.asSharedFlow()

    init {
        onEvent(MapEvent.LoadParking)
    }

    fun onEvent(
        event: MapEvent
    ) {

        when (event) {

            MapEvent.LoadParking -> {
                loadParking()
            }

            is MapEvent.ParkingSelected -> {

                _state.update {

                    it.copy(
                        selectedParking = event.parking
                    )

                }

            }

        }

    }

    private fun loadParking() {

        viewModelScope.launch {

            _state.update {

                it.copy(
                    isLoading = true,
                    error = null
                )

            }

            repository
                .getParkingLocations()
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

                        MapEffect.ShowSnackbar(
                            exception.message ?: "Unable to load parking."
                        )

                    )

                }

        }

    }

}