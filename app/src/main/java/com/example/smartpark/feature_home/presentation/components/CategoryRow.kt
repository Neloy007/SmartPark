package com.example.smartpark.feature_home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.LocalAirport
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Immutable
data class ParkingCategory(

    val title: String,

    val icon: ImageVector

)

private val parkingCategories = listOf(

    ParkingCategory(
        "All",
        Icons.Default.LocalParking
    ),

    ParkingCategory(
        "Car",
        Icons.Default.DirectionsCar
    ),

    ParkingCategory(
        "Bike",
        Icons.Default.DirectionsBike
    ),

    ParkingCategory(
        "EV",
        Icons.Default.ElectricCar
    ),

    ParkingCategory(
        "Mall",
        Icons.Default.LocalMall
    ),

    ParkingCategory(
        "Airport",
        Icons.Default.LocalAirport
    )

)

@Composable
fun CategoryRow(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {

    Column {

        Text(
            text = "Categories",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            items(parkingCategories) { category ->

                val selected = category.title == selectedCategory

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        onCategorySelected(category.title)
                    }
                ) {

                    Surface(
                        modifier = Modifier.size(72.dp),
                        shape = CircleShape,
                        color = if (selected)
                            Color(0xFF2E7D32)
                        else
                            Color.White,
                        shadowElevation = 8.dp
                    ) {

                        androidx.compose.foundation.layout.Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(72.dp)
                        ) {

                            Icon(
                                imageVector = category.icon,
                                contentDescription = category.title,
                                tint = if (selected)
                                    Color.White
                                else
                                    Color(0xFF2E7D32),
                                modifier = Modifier.size(32.dp)
                            )

                        }

                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = category.title,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (selected)
                            Color(0xFF2E7D32)
                        else
                            Color.DarkGray,
                        fontWeight = if (selected)
                            FontWeight.Bold
                        else
                            FontWeight.Normal
                    )

                }

            }

        }

    }

}

@Preview(showBackground = true)
@Composable
private fun CategoryRowPreview() {

    MaterialTheme {

        CategoryRow(
            selectedCategory = "All",
            onCategorySelected = {}
        )

    }

}