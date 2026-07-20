package com.example.smartpark.feature_home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.smartpark.feature_admin.presentation.AdminScreen
import com.example.smartpark.feature_home.presentation.components.LoadingView

@Composable
fun HomeRoute(

    navController: NavHostController,

    homeViewModel: HomeViewModel = hiltViewModel()

) {

    val state by homeViewModel.state.collectAsState()

    val currentUser = state.currentUser

    when {

        state.isLoading -> {

            LoadingView()

        }

        currentUser == null -> {

            LoadingView(
                message = "Loading user..."
            )

        }

        currentUser.role == "ADMIN" -> {

            AdminScreen(
                navController = navController
            )

        }

        else -> {

            HomeScreen(
                navController = navController,
                viewModel = homeViewModel
            )

        }

    }

}