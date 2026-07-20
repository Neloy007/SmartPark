package com.example.smartpark.feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.smartpark.feature_home.domain.model.ParkingSpot

@Composable
fun NearbyParkingSection(
    parkingList: List<ParkingSpot>,
    modifier: Modifier = Modifier,
    onParkingClick: (ParkingSpot) -> Unit = {},
    onSeeAllClick: () -> Unit = {}
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        Header(
            onSeeAllClick = onSeeAllClick
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(parkingList) { parking ->

                ParkingCard(
                    parking = parking,
                    onClick = {
                        onParkingClick(parking)
                    }
                )

            }

        }

    }

}

@Composable
private fun Header(
    onSeeAllClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),

        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        Text(
            text = "Nearby Parking",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        TextButton(
            onClick = onSeeAllClick
        ) {
            Text("See All")
        }

    }

}