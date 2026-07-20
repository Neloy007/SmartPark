package com.example.smartpark.feature_profile.data.repository

import com.example.smartpark.feature_auth.data.model.User
import com.example.smartpark.feature_profile.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(

    private val auth: FirebaseAuth,

    private val firestore: FirebaseFirestore

) : ProfileRepository {

    override suspend fun getProfile(): Result<User> {

        return try {

            val firebaseUser = auth.currentUser
                ?: return Result.failure(
                    Exception("User not logged in")
                )

            val uid = firebaseUser.uid

            val snapshot = firestore
                .collection("users")
                .document(uid)
                .get()
                .await()


            if (!snapshot.exists()) {

                return Result.failure(
                    Exception("Profile document does not exist")
                )

            }


            val user = snapshot.toObject(User::class.java)


            if (user == null) {

                return Result.failure(
                    Exception("Unable to parse user data")
                )

            }


            Result.success(user)


        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    override suspend fun updateProfile(
        user: User
    ): Result<Unit> {

        return try {

            val currentUid = auth.currentUser?.uid
                ?: return Result.failure(
                    Exception("User not logged in")
                )


            if (currentUid != user.uid) {

                return Result.failure(
                    Exception("Unauthorized update")
                )

            }


            firestore
                .collection("users")
                .document(currentUid)
                .set(user)
                .await()


            Result.success(Unit)


        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    override suspend fun logout() {

        auth.signOut()

    }

}