package ks.hotelsapp.data

import retrofit2.http.GET
import retrofit2.http.Path

interface  HotelsApi {
    @GET("0777.json")
    suspend fun getHotels(): List<HotelDto>

    @GET("{id}.json")  // Используем {id} в пути
    suspend fun getHotelImage(@Path("id") id: Int): HotelImageDto
}