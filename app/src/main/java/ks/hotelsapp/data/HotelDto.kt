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
    return Hotel(
        id = this.id,
        name = this.name,
        address = this.address,
        stars = this.stars,
        distance = this.distance,
        suitesAvailability = this.suites_availability
    )
}
//{
//    fun toDomainModel(): Hotel {
//        val availableRoomsCount = suites_availability.split(":").size
//        return Hotel(
//            id = id,
//            name = name,
//            address = address,
//            stars = stars,
//            distance = distance,
//            availableRooms = "$availableRoomsCount"
//        )
//    }
//}
