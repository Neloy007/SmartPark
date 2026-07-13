package com.example.smartpark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import com.example.smartpark.navigation.AppNavGraph
import com.example.smartpark.ui.theme.SmartParkTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            SmartParkTheme {

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

                    val navController = rememberNavController()

                    AppNavGraph(
                        navController = navController
                    )

                }

            }

        }
    }
}