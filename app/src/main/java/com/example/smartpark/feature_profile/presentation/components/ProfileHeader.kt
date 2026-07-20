package com.example.smartpark.feature_profile.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileHeader(

    name: String,

    email: String,

    profileImage: String = ""

) {

    Column(

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {

        Surface(

            modifier = Modifier.size(110.dp),

            shape = CircleShape,

            color = Color(0xFFE8F5E9)

        ) {

            if (profileImage.isEmpty()) {

                Icon(

                    imageVector = Icons.Default.Person,

                    contentDescription = null,

                    tint = Color(0xFF2E7D32),

                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(24.dp)

                )

            } else {

                // Later
                // AsyncImage(
                //     model = profileImage,
                //     contentDescription = null
                // )

            }

        }

        Text(

            text = name,

            style = MaterialTheme.typography.headlineSmall

        )

        Text(

            text = email,

            style = MaterialTheme.typography.bodyMedium,

            color = Color.Gray

        )

    }

}