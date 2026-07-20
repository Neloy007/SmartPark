package com.example.smartpark.feature_profile.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LogoutButton(

    modifier: Modifier = Modifier,

    onLogoutClick: () -> Unit

) {

    Button(

        onClick = onLogoutClick,

        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFD32F2F),
            contentColor = Color.White
        )

    ) {

        Icon(
            imageVector = Icons.Default.Logout,
            contentDescription = null
        )

        Text(
            text = "Logout",
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.titleMedium
        )

    }

}