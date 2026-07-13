package com.example.smartpark.feature_auth.data.model

data class User(

    val uid: String = "",

    val name: String = "",

    val email: String = "",

    val phone: String = "",

    val vehicleNumber: String = "",

    val role: String = "DRIVER",

    val profileImage: String = "",

    val isVerified: Boolean = false,

    val createdAt: Long = System.currentTimeMillis()

)