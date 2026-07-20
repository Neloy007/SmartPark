package com.example.smartpark.feature_admin.presentation

import com.example.smartpark.feature_admin.domain.model.Parking

sealed interface AdminEvent {

    /**
     * Load all parking locations
     */
    data object LoadParking : AdminEvent

    /**
     * Add new parking
     */
    data object AddParking : AdminEvent

    /**
     * Update existing parking
     */
    data class UpdateParking(
        val parking: Parking
    ) : AdminEvent

    /**
     * Delete parking
     */
    data class DeleteParking(
        val parkingId: String
    ) : AdminEvent

    /**
     * Select parking for editing
     */
    data class SelectParking(
        val parking: Parking
    ) : AdminEvent

    /**
     * Clear selected parking
     */
    data object ClearSelection : AdminEvent

    /**
     * Update form fields
     */
    data class ParkingNameChanged(
        val value: String
    ) : AdminEvent

    data class AddressChanged(
        val value: String
    ) : AdminEvent

    data class LatitudeChanged(
        val value: String
    ) : AdminEvent

    data class LongitudeChanged(
        val value: String
    ) : AdminEvent

    data class TotalSlotsChanged(
        val value: String
    ) : AdminEvent

    /**
     * Clear all input fields
     */
    data object ClearForm : AdminEvent

    data object Logout : AdminEvent

}