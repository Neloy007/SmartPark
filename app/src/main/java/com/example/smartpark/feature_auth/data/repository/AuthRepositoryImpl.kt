package com.example.smartpark.feature_auth.data.repository

import com.example.smartpark.feature_auth.data.model.User
import com.example.smartpark.feature_auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(

    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore

) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            auth.signInWithEmailAndPassword(
                email,
                password
            ).await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    override suspend fun register(
        name: String,
        email: String,
        phone: String,
        vehicleNumber: String,
        password: String
    ): Result<Unit> {

        return try {

            val result = auth
                .createUserWithEmailAndPassword(
                    email,
                    password
                )
                .await()

            val uid = result.user!!.uid

            val user = User(
                uid = uid,
                name = name,
                email = email,
                phone = phone,
                vehicleNumber = vehicleNumber
            )

            firestore
                .collection("users")
                .document(uid)
                .set(user)
                .await()

            firestore
                .collection("users")
                .document(uid)
                .set(user)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    override suspend fun sendPasswordResetEmail(
        email: String
    ): Result<Unit> {

        return try {

            auth.sendPasswordResetEmail(email)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    override suspend fun logout() {

        auth.signOut()

    }

    override fun isLoggedIn(): Boolean {

        return auth.currentUser != null

    }

}