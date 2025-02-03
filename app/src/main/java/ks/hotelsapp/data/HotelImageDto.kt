package ks.hotelsapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HotelImageDto(
    @Json(name = "image")
    val image: String?
)