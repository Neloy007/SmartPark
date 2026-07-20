package com.example.smartpark.feature_profile.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartpark.feature_auth.data.model.User

@Composable
fun EditProfileDialog(

    user: User,

    onDismiss: () -> Unit,

    onSave: (User) -> Unit

) {

    var name by remember { mutableStateOf(user.name) }

    var phone by remember { mutableStateOf(user.phone) }

    var vehicleNumber by remember { mutableStateOf(user.vehicleNumber) }

    LaunchedEffect(user) {

        name = user.name

        phone = user.phone

        vehicleNumber = user.vehicleNumber

    }

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {

            Text(
                text = "Edit Profile"
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

                        Text("Full Name")

                    },

                    modifier = Modifier.fillMaxWidth()

                )

                OutlinedTextField(

                    value = phone,

                    onValueChange = {

                        phone = it

                    },

                    label = {

                        Text("Phone Number")

                    },

                    modifier = Modifier.fillMaxWidth()

                )

                OutlinedTextField(

                    value = vehicleNumber,

                    onValueChange = {

                        vehicleNumber = it

                    },

                    label = {

                        Text("Vehicle Number")

                    },

                    modifier = Modifier.fillMaxWidth()

                )

            }

        },

        confirmButton = {

            Button(

                onClick = {

                    onSave(

                        user.copy(

                            name = name,

                            phone = phone,

                            vehicleNumber = vehicleNumber

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