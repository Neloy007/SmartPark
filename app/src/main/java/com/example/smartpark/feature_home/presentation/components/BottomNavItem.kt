package com.example.smartpark.feature_home.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.smartpark.navigation.Routes

data class BottomNavItem(

    val title: String,

    val route: String,

    val icon: ImageVector

)

val bottomNavItems = listOf(

    BottomNavItem(
        title = "Home",
        route = Routes.Home,
        icon = Icons.Default.Home
    ),

    BottomNavItem(
        title = "Map",
        route = Routes.Map,
        icon = Icons.Default.Map
    ),

    BottomNavItem(
        title = "Bookings",
        route = Routes.Booking,
        icon = Icons.Default.Bookmark
    ),

    BottomNavItem(
        title = "Profile",
        route = Routes.Profile,
        icon = Icons.Default.Person
    )

)