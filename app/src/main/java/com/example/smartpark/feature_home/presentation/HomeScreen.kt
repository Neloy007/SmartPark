package com.example.smartpark.feature_home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.smartpark.feature_home.presentation.components.BottomNavigationBar
import com.example.smartpark.feature_home.presentation.components.CategoryRow
import com.example.smartpark.feature_home.presentation.components.HomeTopBar
import com.example.smartpark.feature_home.presentation.components.LoadingView
import com.example.smartpark.feature_home.presentation.components.NearbyParkingSection
import com.example.smartpark.feature_home.presentation.components.PromotionBanner
import com.example.smartpark.feature_home.presentation.components.SearchBar
import com.example.smartpark.navigation.Routes

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {

        viewModel.effect.collect { effect ->

            when (effect) {

                HomeEffect.NavigateLogin -> {

                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Home) {
                            inclusive = true
                        }
                    }

                }

                is HomeEffect.ShowSnackbar -> {

                    snackbarHostState.showSnackbar(
                        effect.message
                    )

                }

                else -> {}
            }

        }

    }

    if (state.isLoading) {
        LoadingView()
        return
    }

    Scaffold(

        containerColor = Color(0xFFF6F8FB),

        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },

        bottomBar = {

            BottomNavigationBar(
                selectedRoute = Routes.Home,
                onItemSelected = { route ->
                    if (route != Routes.Home) {
                        navController.navigate(route)
                    }
                }
            )

        }

    ) { innerPadding ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F8FB))
                .padding(innerPadding)
                .navigationBarsPadding(),

            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 20.dp
            ),

            verticalArrangement = Arrangement.spacedBy(20.dp)

        ) {

            item {

                HomeTopBar(
                    userName = state.currentUser?.name ?: "Guest"
                )

            }

            item {

                SearchBar(

                    query = state.searchQuery,

                    onQueryChange = {
                        // TODO
                        // viewModel.onEvent(
                        //     HomeEvent.SearchChanged(it)
                        // )
                    }

                )

            }

            item {

                PromotionBanner()

            }

            item {

                CategoryRow(
                    selectedCategory = state.selectedCategory,
                    onCategorySelected = { category ->
                        viewModel.onEvent(HomeEvent.CategorySelected(category))
                    }
                )

            }

            item {

                NearbyParkingSection(

                    parkingList = state.filteredParkingList,

                    onParkingClick = { parking ->

                        // TODO
                        // navController.navigate(
                        //     Routes.ParkingDetails + "/${parking.id}"
                        // )

                    },

                    onSeeAllClick = {

                        // TODO
                        // Navigate to parking list screen

                    }

                )

            }

        }

    }

}