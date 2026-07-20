package com.example.smartpark.feature_admin.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminTopBar(

    modifier: Modifier = Modifier

) {

    CenterAlignedTopAppBar(

        modifier = modifier.fillMaxWidth(),

        title = {

            Text(

                text = "Admin Dashboard",

                style = MaterialTheme.typography.titleLarge

            )

        },

        navigationIcon = {

            Icon(

                imageVector = Icons.Default.AdminPanelSettings,

                contentDescription = "Admin",

                modifier = Modifier.padding(start = 16.dp),

                tint = Color.White

            )

        },

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(

            containerColor = Color(0xFF2E7D32),

            titleContentColor = Color.White,

            navigationIconContentColor = Color.White

        )

    )

}