package com.example.smartpark.feature_user.data.repository

import com.example.smartpark.feature_auth.data.model.User
import com.example.smartpark.feature_user.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : UserRepository {

    override suspend fun getCurrentUser(): Result<User> {

        return try {

            val uid = auth.currentUser?.uid
                ?: return Result.failure(
                    Exception("User not logged in")
                )

            val snapshot = firestore
                .collection("users")
                .document(uid)
                .get()
                .await()

            val user = snapshot.toObject(User::class.java)
                ?: return Result.failure(
                    Exception("User not found")
                )

            Result.success(user)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    override suspend fun updateProfile(
        user: User
    ): Result<Unit> {

        return try {

            firestore
                .collection("users")
                .document(user.uid)
                .set(user)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

}