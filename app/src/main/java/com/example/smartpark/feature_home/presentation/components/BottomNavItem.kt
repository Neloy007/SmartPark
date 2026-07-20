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

fun bottomNavItems(
    isAdmin: Boolean
): List<BottomNavItem> {

    return listOf(

        BottomNavItem(
            if (isAdmin) "Admin" else "Home",
            if (isAdmin) Routes.Admin else Routes.Home,
            Icons.Default.Home
        ),

        BottomNavItem(
            "Map",
            Routes.Map,
            Icons.Default.Map
        ),

        BottomNavItem(
            "Bookings",
            Routes.Booking,
            Icons.Default.Bookmark
        ),

        BottomNavItem(
            "Profile",
            Routes.Profile,
            Icons.Default.Person
        )

    )

}