package com.example.smartpark.feature_home.data.repository

import com.example.smartpark.feature_home.domain.model.ParkingSpot
import com.example.smartpark.feature_home.domain.repository.ParkingRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ParkingRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ParkingRepository {

    private val parkingCollection = firestore.collection("parking_spots")

    override suspend fun getNearbyParking(): Result<List<ParkingSpot>> {

        return try {

            val snapshot = parkingCollection
                .get()
                .await()

            val parkingList = snapshot.documents.mapNotNull {

                it.toObject(ParkingSpot::class.java)

            }

            Result.success(parkingList)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    override suspend fun getParkingById(
        parkingId: String
    ): Result<ParkingSpot> {

        return try {

            val document = parkingCollection
                .document(parkingId)
                .get()
                .await()

            val parking = document.toObject(ParkingSpot::class.java)

            if (parking != null) {

                Result.success(parking)

            } else {

                Result.failure(
                    Exception("Parking not found.")
                )

            }

        } catch (e: Exception) {

            Result.failure(e)

        }

    }
}