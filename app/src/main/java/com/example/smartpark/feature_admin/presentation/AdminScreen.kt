package com.example.smartpark.feature_admin.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.smartpark.feature_admin.domain.model.Parking
import com.example.smartpark.feature_admin.presentation.components.AdminBottomBar
import com.example.smartpark.feature_admin.presentation.components.AdminTopBar
import com.example.smartpark.feature_admin.presentation.components.EditParkingDialog
import com.example.smartpark.feature_admin.presentation.components.ParkingForm
import com.example.smartpark.feature_admin.presentation.components.ParkingListItem

@Composable
fun AdminScreen(
    navController: NavHostController,
    viewModel: AdminViewModel = hiltViewModel()

) {

    val state by viewModel.state.collectAsState()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    var showEditDialog by remember {
        mutableStateOf(false)
    }

    var selectedParking by remember {
        mutableStateOf<Parking?>(null)
    }

    LaunchedEffect(Unit) {

        viewModel.effect.collect { effect ->

            when (effect) {

                is AdminEffect.ShowSnackbar -> {

                    snackbarHostState.showSnackbar(
                        effect.message
                    )

                }

                AdminEffect.NavigateLogin -> {

                    navController.navigate("login") {

                        popUpTo(0) {
                            inclusive = true
                        }

                        launchSingleTop = true

                    }

                }

                else -> Unit

            }

        }

    }

    Scaffold(

        containerColor = Color(0xFFF6F8FB),

        topBar = {

            AdminTopBar()

        },

        snackbarHost = {

            SnackbarHost(
                snackbarHostState
            )

        },

        bottomBar = {

            AdminBottomBar(

                onLogout = {

                    viewModel.onEvent(
                        AdminEvent.Logout
                    )

                }

            )

        }

    ) { paddingValues ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F8FB))
                .padding(paddingValues)
                .navigationBarsPadding(),

            contentPadding = PaddingValues(20.dp),

            verticalArrangement = Arrangement.spacedBy(20.dp)

        ) {

            item {

                ParkingForm(

                    name = state.parkingName,

                    onNameChange = {

                        viewModel.onEvent(
                            AdminEvent.ParkingNameChanged(it)
                        )

                    },

                    address = state.address,

                    onAddressChange = {

                        viewModel.onEvent(
                            AdminEvent.AddressChanged(it)
                        )

                    },

                    latitude = state.latitude,

                    onLatitudeChange = {

                        viewModel.onEvent(
                            AdminEvent.LatitudeChanged(it)
                        )

                    },

                    longitude = state.longitude,

                    onLongitudeChange = {

                        viewModel.onEvent(
                            AdminEvent.LongitudeChanged(it)
                        )

                    },

                    totalSlots = state.totalSlots,

                    onTotalSlotsChange = {

                        viewModel.onEvent(
                            AdminEvent.TotalSlotsChanged(it)
                        )

                    },

                    onAddParking = {

                        viewModel.onEvent(
                            AdminEvent.AddParking
                        )

                    }

                )

            }

            items(state.parkingList) { parking ->

                ParkingListItem(

                    parking = parking,

                    onEditClick = {

                        selectedParking = it

                        showEditDialog = true

                    },

                    onDeleteClick = {

                        viewModel.onEvent(
                            AdminEvent.DeleteParking(it.id)
                        )

                    }

                )

            }

        }

    }

    if (showEditDialog && selectedParking != null) {

        EditParkingDialog(

            parking = selectedParking!!,

            onDismiss = {

                showEditDialog = false

                selectedParking = null

            },

            onSave = { updatedParking ->

                viewModel.onEvent(
                    AdminEvent.UpdateParking(updatedParking)
                )

                showEditDialog = false

                selectedParking = null

            }

        )

    }

}