package com.example.smartpark.feature_profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartpark.feature_profile.presentation.components.EditProfileDialog
import com.example.smartpark.feature_profile.presentation.components.LogoutButton
import com.example.smartpark.feature_profile.presentation.components.ProfileHeader
import com.example.smartpark.feature_profile.presentation.components.ProfileInfoCard
import com.example.smartpark.feature_profile.presentation.components.ProfileMenuItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateLogin: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    var showEditDialog by remember {
        mutableStateOf(false)
    }


    LaunchedEffect(Unit) {

        viewModel.effect.collect { effect ->

            when(effect) {

                ProfileEffect.NavigateLogin -> {

                    onNavigateLogin()

                }


                is ProfileEffect.ShowSnackbar -> {

                    // Add SnackbarHost if needed

                }


                ProfileEffect.ProfileUpdated -> {

                    // Profile updated

                }

            }

        }

    }



    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = {
                    Text(
                        text = "Profile"
                    )
                }

            )

        }

    ) { padding ->


        Box(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)

        ) {


            when {


                state.isLoading -> {


                    CircularProgressIndicator(

                        modifier = Modifier
                            .align(Alignment.Center)

                    )


                }


                state.error != null -> {


                    Text(

                        text = state.error ?: "Something went wrong",

                        modifier = Modifier
                            .align(Alignment.Center)

                    )


                }


                state.user != null -> {


                    val user = state.user!!


                    Column(

                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(
                                rememberScrollState()
                            ),

                        verticalArrangement = Arrangement.spacedBy(
                            16.dp
                        )

                    ) {


                        // Profile Image + Name + Email

                        ProfileHeader(

                            name = user.name,

                            email = user.email,

                            profileImage = user.profileImage

                        )



                        // Email Card

                        ProfileInfoCard(

                            title = "Email",

                            value = user.email,

                            icon = Icons.Default.Email

                        )



                        // Phone Card

                        ProfileInfoCard(

                            title = "Phone Number",

                            value = user.phone,

                            icon = Icons.Default.Phone

                        )



                        // Vehicle Card

                        ProfileInfoCard(

                            title = "Vehicle Number",

                            value = user.vehicleNumber,

                            icon = Icons.Default.DirectionsCar

                        )



                        // Edit Profile

                        ProfileMenuItem(

                            title = "Edit Profile",

                            icon = Icons.Default.Edit

                        ) {

                            showEditDialog = true

                        }



                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )



                        LogoutButton(

                            onLogoutClick = {

                                viewModel.onEvent(
                                    ProfileEvent.Logout
                                )

                            }

                        )


                    }


                }


            }


        }



        // Edit Profile Dialog

        if (showEditDialog && state.user != null) {


            EditProfileDialog(

                user = state.user!!,

                onDismiss = {

                    showEditDialog = false

                },

                onSave = { updatedUser ->


                    viewModel.onEvent(

                        ProfileEvent.UpdateProfile(
                            updatedUser
                        )

                    )


                    showEditDialog = false


                }

            )


        }


    }

}