package com.example.smartpark.feature_admin.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun ParkingForm(

    name: String,
    onNameChange: (String) -> Unit,

    address: String,
    onAddressChange: (String) -> Unit,

    latitude: String,
    onLatitudeChange: (String) -> Unit,

    longitude: String,
    onLongitudeChange: (String) -> Unit,

    totalSlots: String,
    onTotalSlotsChange: (String) -> Unit,

    onAddParking: () -> Unit,

    modifier: Modifier = Modifier

) {

    Column(

        modifier = modifier.fillMaxWidth(),

        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {

        OutlinedTextField(

            value = name,

            onValueChange = onNameChange,

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("Parking Name")
            },

            singleLine = true

        )

        OutlinedTextField(

            value = address,

            onValueChange = onAddressChange,

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("Address")
            }

        )

        OutlinedTextField(

            value = latitude,

            onValueChange = onLatitudeChange,

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("Latitude")
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),

            singleLine = true

        )

        OutlinedTextField(

            value = longitude,

            onValueChange = onLongitudeChange,

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("Longitude")
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),

            singleLine = true

        )

        OutlinedTextField(

            value = totalSlots,

            onValueChange = onTotalSlotsChange,

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("Total Slots")
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),

            singleLine = true

        )

        Button(

            onClick = onAddParking,

            modifier = Modifier.fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E7D32)
            )

        ) {

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )

            Text(
                text = " Add Parking",
                style = MaterialTheme.typography.titleMedium
            )

        }

    }

}