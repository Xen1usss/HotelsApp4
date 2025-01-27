package ks.hotelsapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ks.hotelsapp.domain.Hotel

// Data Transfer Object model
@JsonClass(generateAdapter = true)
data class HotelDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "address")
    val address: String,
    @Json(name = "stars")
    val stars: Float,
    @Json(name = "distance")
    val distance: Float,
    @Json(name = "suites_availability")
    val suites_availability: String
)

fun HotelDto.toDomainModel(): Hotel {
    val availableSuitesCount = suites_availability
        .split(":")
        .filter { it.isNotEmpty() } // нужно, чтобы строки по типу "1:" не считывались, как 2 номера, вместо 1
        .size

    return Hotel(
        id = this.id,
        name = this.name,
        address = this.address,
        stars = this.stars,
        distance = this.distance,
        availableSuitesCount = availableSuitesCount // Присваиваем количество
    )
}

