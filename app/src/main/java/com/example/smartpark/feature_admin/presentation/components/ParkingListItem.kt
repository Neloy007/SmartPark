package com.example.smartpark.feature_admin.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.smartpark.feature_admin.domain.model.Parking

@Composable
fun ParkingListItem(

    parking: Parking,

    modifier: Modifier = Modifier,

    onEditClick: (Parking) -> Unit,

    onDeleteClick: (Parking) -> Unit

) {

    Card(

        modifier = modifier.fillMaxWidth(),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )

    ) {

        Column(

            modifier = Modifier.padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            Row(

                modifier = Modifier.fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(

                    imageVector = Icons.Default.LocalParking,

                    contentDescription = null,

                    tint = Color(0xFF2E7D32)

                )

                Text(

                    text = parking.name,

                    modifier = Modifier.padding(start = 12.dp),

                    style = MaterialTheme.typography.titleMedium,

                    fontWeight = FontWeight.Bold

                )

            }

            Row(

                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(

                    imageVector = Icons.Default.LocationOn,

                    contentDescription = null,

                    tint = Color.Gray

                )

                Text(

                    text = parking.address,

                    modifier = Modifier.padding(start = 8.dp),

                    color = Color.Gray

                )

            }

            Text(

                text = "Available Slots: ${parking.availableSlots}/${parking.totalSlots}",

                style = MaterialTheme.typography.bodyMedium,

                color = Color(0xFF2E7D32),

                fontWeight = FontWeight.SemiBold

            )

            Text(

                text = "Lat: ${parking.latitude}",

                style = MaterialTheme.typography.bodySmall

            )

            Text(

                text = "Lng: ${parking.longitude}",

                style = MaterialTheme.typography.bodySmall

            )

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.End

            ) {

                IconButton(

                    onClick = {

                        onEditClick(parking)

                    }

                ) {

                    Icon(

                        imageVector = Icons.Default.Edit,

                        contentDescription = "Edit",

                        tint = Color(0xFF1976D2)

                    )

                }

                IconButton(

                    onClick = {

                        onDeleteClick(parking)

                    }

                ) {

                    Icon(

                        imageVector = Icons.Default.Delete,

                        contentDescription = "Delete",

                        tint = Color.Red

                    )

                }

            }

        }

    }

}