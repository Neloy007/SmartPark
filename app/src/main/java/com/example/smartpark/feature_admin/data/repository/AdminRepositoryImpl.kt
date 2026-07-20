package com.example.smartpark.feature_admin.data.repository


import com.example.smartpark.feature_admin.domain.model.Parking
import com.example.smartpark.feature_admin.domain.repository.AdminRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AdminRepositoryImpl @Inject constructor(

    private val firestore: FirebaseFirestore,

    private val auth: FirebaseAuth

): AdminRepository {



    override suspend fun addParking(
        parking: Parking
    ): Result<Unit> {


        return try {


            val id =
                firestore
                    .collection("parking")
                    .document()
                    .id



            firestore
                .collection("parking")
                .document(id)
                .set(
                    parking.copy(
                        id = id,
                        createdBy =
                            auth.currentUser?.uid ?: ""
                    )
                )
                .await()



            Result.success(Unit)



        } catch(e:Exception){

            Result.failure(e)

        }


    }



    override suspend fun getParkingList():
            Result<List<Parking>> {


        return try {


            val snapshot =
                firestore
                    .collection("parking")
                    .get()
                    .await()



            val list =
                snapshot.documents.map {


                    it.toObject(
                        Parking::class.java
                    )!!.copy(
                        id = it.id
                    )

                }



            Result.success(list)


        }catch(e:Exception){

            Result.failure(e)

        }


    }



    override suspend fun deleteParking(
        id:String
    ):Result<Unit>{


        return try {


            firestore
                .collection("parking")
                .document(id)
                .delete()
                .await()


            Result.success(Unit)


        }catch(e:Exception){


            Result.failure(e)

        }


    }

    override suspend fun updateParking(
        parking: Parking
    ): Result<Unit> {

        return try {

            firestore
                .collection("parking")
                .document(parking.id)
                .set(parking)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }


}