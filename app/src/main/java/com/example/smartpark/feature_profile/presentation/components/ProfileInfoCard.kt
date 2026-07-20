package com.example.smartpark.feature_profile.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProfileInfoCard(

    title: String,

    value: String,

    icon: ImageVector = Icons.Default.Info,

    modifier: Modifier = Modifier

) {

    Card(

        modifier = modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            verticalAlignment = Alignment.CenterVertically,

            horizontalArrangement = Arrangement.spacedBy(16.dp)

        ) {

            Icon(

                imageVector = icon,

                contentDescription = title,

                tint = Color(0xFF2E7D32)

            )

            Column {

                Text(

                    text = title,

                    style = MaterialTheme.typography.labelMedium,

                    color = Color.Gray

                )

                Text(

                    text = value,

                    style = MaterialTheme.typography.bodyLarge,

                    fontWeight = FontWeight.SemiBold

                )

            }

        }

    }

}