package com.example.smartpark.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
    message: String = "Loading..."
) {

    Box(
        modifier = modifier
            .background(
                Color.Black.copy(alpha = 0.35f)
            ),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            shadowElevation = 10.dp,
            tonalElevation = 6.dp,
            shape = MaterialTheme.shapes.extraLarge
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .size(180.dp)
            ) {

                CircularProgressIndicator(
                    color = Color(0xFF2E7D32)
                )

                androidx.compose.foundation.layout.Spacer(
                    modifier = Modifier.size(20.dp)
                )

                Text(
                    text = message,
                    style = MaterialTheme.typography.titleMedium
                )

            }

        }

    }

}

@Preview(showBackground = true)
@Composable
private fun LoadingViewPreview() {

    MaterialTheme {

        LoadingView(
            message = "Loading nearby parking..."
        )

    }

}