package com.example.smartpark.feature_map.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState

@Composable
fun MapScreen(

    viewModel: MapViewModel = hiltViewModel()

) {

    val state by viewModel.state.collectAsState()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(Unit) {

        viewModel.effect.collect { effect ->

            when (effect) {

                is MapEffect.ShowSnackbar -> {

                    snackbarHostState.showSnackbar(
                        effect.message
                    )

                }

            }

        }

    }

    LaunchedEffect(state.selectedParking) {

        state.selectedParking?.let { parking ->

            cameraPositionState.animate(

                update = CameraUpdateFactory.newLatLngZoom(

                    LatLng(
                        parking.latitude,
                        parking.longitude
                    ),

                    16f

                )

            )

        }

    }

    Scaffold(

        snackbarHost = {

            SnackbarHost(
                hostState = snackbarHostState
            )

        }

    ) { innerPadding ->

        GoogleMap(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            cameraPositionState = cameraPositionState,

            properties = MapProperties(
                isMyLocationEnabled = false
            )

        ) {

            state.parkingList.forEach { parking ->

                Marker(

                    state = rememberUpdatedMarkerState(

                        position = LatLng(

                            parking.latitude,
                            parking.longitude

                        )

                    ),

                    title = parking.name,

                    snippet = "${parking.availableSlots}/${parking.totalSlots} Available"

                )

            }

        }

    }

}