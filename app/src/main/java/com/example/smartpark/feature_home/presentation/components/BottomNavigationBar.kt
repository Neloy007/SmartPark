package com.example.smartpark.feature_home.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BottomNavigationBar(
    selectedRoute: String,
    onItemSelected: (String) -> Unit
) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface
    ) {

        bottomNavItems.forEach { item ->

            NavigationBarItem(

                selected = selectedRoute == item.route,

                onClick = {
                    onItemSelected(item.route)
                },

                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },

                label = {
                    Text(item.title)
                },

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color(0xFF2E7D32),
                    indicatorColor = Color(0xFF2E7D32),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )

            )

        }

    }

}