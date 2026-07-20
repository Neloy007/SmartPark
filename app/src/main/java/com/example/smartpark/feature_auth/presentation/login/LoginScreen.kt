package com.example.smartpark.feature_auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.smartpark.R
import com.example.smartpark.feature_home.presentation.components.LoadingView
import com.example.smartpark.navigation.Routes

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    var passwordVisible by remember { mutableStateOf(false) }

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {

        viewModel.effect.collect { effect ->

            when (effect) {

                LoginEffect.NavigateToHome -> {

                    navController.navigate(Routes.Home) {
                        popUpTo(Routes.Login) {
                            inclusive = true
                        }
                    }

                }

                LoginEffect.NavigateToRegister -> {
                    navController.navigate(Routes.Register)
                }

                LoginEffect.NavigateToForgotPassword -> {
                    navController.navigate(Routes.ForgotPassword)
                }

                is LoginEffect.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(effect.message)
                }

            }

        }

    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F8FB))
                .padding(padding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(R.drawable.ic_smartpark_logo),
                    contentDescription = "SmartPark Logo",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Login to continue using SmartPark",
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(32.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        OutlinedTextField(
                            value = state.email,
                            onValueChange = {
                                viewModel.onEvent(LoginEvent.EmailChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text("Email")
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Email, null)
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            ),
                            isError = state.emailError != null,
                            supportingText = {
                                state.emailError?.let {
                                    Text(it)
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = state.password,
                            onValueChange = {
                                viewModel.onEvent(LoginEvent.PasswordChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text("Password")
                            },
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
                                        imageVector =
                                            if (passwordVisible)
                                                Icons.Default.Visibility
                                            else
                                                Icons.Default.VisibilityOff,
                                        contentDescription = null
                                    )

                                }

                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            visualTransformation =
                                if (passwordVisible)
                                    VisualTransformation.None
                                else
                                    PasswordVisualTransformation(),
                            isError = state.passwordError != null,
                            supportingText = {
                                state.passwordError?.let {
                                    Text(it)
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Forgot Password?",
                            modifier = Modifier
                                .align(Alignment.End)
                                .clickable {
                                    viewModel.onEvent(LoginEvent.ForgotPasswordClicked)
                                },
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            enabled = !state.isLoading,
                            onClick = {
                                viewModel.onEvent(LoginEvent.LoginClicked)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2E7D32)
                            )
                        ) {

                            if (state.isLoading) {

                                CircularProgressIndicator(
                                    modifier = Modifier.size(22.dp),
                                    color = Color.White,
                                    strokeWidth = 2.dp
                                )

                            } else {

                                Text(
                                    text = "Login",
                                    fontSize = 18.sp
                                )

                            }

                        }
//                        Box {
//
//                            LoginContent()
//
//                            if (state.isLoading) {
//                                LoadingView(
//                                    modifier = Modifier.matchParentSize(),
//                                    message = "Signing In..."
//                                )
//                            }
//
//                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        TextButton(
                            onClick = {
                                navController.navigate(Routes.Home)
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text("Continue as Guest")

                        }

                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                TextButton(
                    onClick = {
                        viewModel.onEvent(LoginEvent.RegisterClicked)
                    }
                ) {

                    Text("Don't have an account? Register")

                }

            }

        }

    }

}