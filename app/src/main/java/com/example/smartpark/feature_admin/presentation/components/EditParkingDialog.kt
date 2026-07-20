package com.example.smartpark.feature_admin.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.smartpark.feature_admin.domain.model.Parking

@Composable
fun EditParkingDialog(

    parking: Parking,

    onDismiss: () -> Unit,

    onSave: (Parking) -> Unit

) {

    var name by remember {
        mutableStateOf(parking.name)
    }

    var address by remember {
        mutableStateOf(parking.address)
    }

    var latitude by remember {
        mutableStateOf(parking.latitude.toString())
    }

    var longitude by remember {
        mutableStateOf(parking.longitude.toString())
    }

    var totalSlots by remember {
        mutableStateOf(parking.totalSlots.toString())
    }

    LaunchedEffect(parking) {

        name = parking.name

        address = parking.address

        latitude = parking.latitude.toString()

        longitude = parking.longitude.toString()

        totalSlots = parking.totalSlots.toString()

    }

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {

            Text(
                text = "Edit Parking"
            )

        },

        text = {

            Column(

                verticalArrangement = Arrangement.spacedBy(16.dp)

            ) {

                OutlinedTextField(

                    value = name,

                    onValueChange = {

                        name = it

                    },

                    label = {

                        Text("Parking Name")

                    },

                    modifier = Modifier.fillMaxWidth()

                )

                OutlinedTextField(

                    value = address,

                    onValueChange = {

                        address = it

                    },

                    label = {

                        Text("Address")

                    },

                    modifier = Modifier.fillMaxWidth()

                )

                OutlinedTextField(

                    value = latitude,

                    onValueChange = {

                        latitude = it

                    },

                    label = {

                        Text("Latitude")

                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),

                    modifier = Modifier.fillMaxWidth()

                )

                OutlinedTextField(

                    value = longitude,

                    onValueChange = {

                        longitude = it

                    },

                    label = {

                        Text("Longitude")

                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),

                    modifier = Modifier.fillMaxWidth()

                )

                OutlinedTextField(

                    value = totalSlots,

                    onValueChange = {

                        totalSlots = it

                    },

                    label = {

                        Text("Total Slots")

                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),

                    modifier = Modifier.fillMaxWidth()

                )

            }

        },

        confirmButton = {

            Button(

                onClick = {

                    val slots = totalSlots.toIntOrNull()
                        ?: parking.totalSlots

                    onSave(

                        parking.copy(

                            name = name,

                            address = address,

                            latitude = latitude.toDoubleOrNull()
                                ?: parking.latitude,

                            longitude = longitude.toDoubleOrNull()
                                ?: parking.longitude,

                            totalSlots = slots,

                            availableSlots = minOf(
                                parking.availableSlots,
                                slots
                            )

                        )

                    )

                }

            ) {

                Text("Save")

            }

        },

        dismissButton = {

            OutlinedButton(

                onClick = onDismiss

            ) {

                Text("Cancel")

            }

        }

    )

}