package ks.hotelsapp.data

import ks.hotelsapp.domain.Hotel

// Data Transfer Object model

data class HotelDto(
    val id: Int,
    val name: String,
    val address: String,
    val stars: Float,
    val suites_availability: String,
    val distance: Int
) {
    fun toDomainModel(): Hotel {
        val availableRoomsCount = suites_availability.split(":").size
        return Hotel(
            id = id,
            name = name,
            address = address,
            stars = stars,
            availableRooms = "$availableRoomsCount",
            distance = distance
        )
    }
}
