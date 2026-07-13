package com.example.smartpark.feature_auth.presentation.forgetPassword

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            "Reset Password",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = state.email,
            onValueChange = {
                viewModel.onEvent(
                    ForgotPasswordEvent.EmailChanged(it)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Email")
            },
            leadingIcon = {
                Icon(Icons.Default.Email, null)
            }
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.onEvent(
                    ForgotPasswordEvent.ResetClicked
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send Reset Link")
        }

        Spacer(Modifier.height(12.dp))

        TextButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Login")
        }

    }

}