package com.example.smartpark.feature_map.data.repository

import com.example.smartpark.feature_map.domain.model.ParkingLocation
import com.example.smartpark.feature_map.domain.repository.MapRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(

    private val firestore: FirebaseFirestore

) : MapRepository {

    override suspend fun getParkingLocations(): Result<List<ParkingLocation>> {

        return try {

            val snapshot = firestore
                .collection("parking")
                .get()
                .await()

            val list = snapshot.documents.mapNotNull {

                it.toObject(ParkingLocation::class.java)

            }

            Result.success(list)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

}