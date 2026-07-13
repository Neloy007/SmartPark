package com.example.smartpark.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartpark.feature_auth.presentation.forgetPassword.ForgotPasswordScreen
import com.example.smartpark.feature_auth.presentation.login.LoginScreen
import com.example.smartpark.feature_auth.presentation.register.RegisterScreen
import com.example.smartpark.feature_home.presentation.HomeScreen
import com.example.smartpark.feature_splash.presentaion.SplashViewModel
import com.example.smartpark.feature_splash.presentation.SplashScreen

@Composable
fun AppNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {

        composable(Routes.Splash) {

            val viewModel: SplashViewModel = hiltViewModel()

            SplashScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(Routes.Login) {
            LoginScreen(
                navController = navController
            )
        }

        composable(Routes.Register) {
            RegisterScreen(navController)
        }

        composable(Routes.ForgotPassword) {
            ForgotPasswordScreen(navController)
        }

        composable(Routes.Home) {
            HomeScreen(
                navController = navController
            )
        }
    }
}