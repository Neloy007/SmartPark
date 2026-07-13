package com.example.smartpark.feature_auth.presentation.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartpark.navigation.Routes

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmVisible by remember { mutableStateOf(false) }

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {

                RegisterEffect.NavigateHome -> {
                    navController.navigate(Routes.Home) {
                        popUpTo(Routes.Register) {
                            inclusive = true
                        }
                    }
                }

                RegisterEffect.NavigateLogin -> {
                    navController.popBackStack()
                }

                is RegisterEffect.ShowSnackbar -> {
                    // Snackbar later
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(Modifier.height(30.dp))

        Text(
            "Create Account",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = state.name,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.NameChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Full Name") },
            leadingIcon = {
                Icon(Icons.Default.Person, null)
            }
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.email,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.EmailChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Email") },
            leadingIcon = {
                Icon(Icons.Default.Email, null)
            }
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.phone,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.PhoneChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Phone") },
            leadingIcon = {
                Icon(Icons.Default.Phone, null)
            }
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.vehicleNumber,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.VehicleChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Vehicle Number") },
            leadingIcon = {
                Icon(Icons.Default.DirectionsCar, null)
            }
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.PasswordChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") },
            leadingIcon = {
                Icon(Icons.Default.Lock, null)
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Icon(
                        if (passwordVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        null
                    )
                }
            },
            visualTransformation =
                if (passwordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation()
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.confirmPassword,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.ConfirmPasswordChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Confirm Password") },
            leadingIcon = {
                Icon(Icons.Default.Lock, null)
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        confirmVisible = !confirmVisible
                    }
                ) {
                    Icon(
                        if (confirmVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        null
                    )
                }
            },
            visualTransformation =
                if (confirmVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation()
        )

        Spacer(Modifier.height(30.dp))

        Button(
            onClick = {
                viewModel.onEvent(RegisterEvent.RegisterClicked)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Account")
        }

        Spacer(Modifier.height(16.dp))

        TextButton(
            onClick = {
                viewModel.onEvent(RegisterEvent.LoginClicked)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Already have an account? Login")
        }

    }

}