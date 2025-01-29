package ks.hotelsapp.data

import retrofit2.http.GET

interface HotelsApi {
    @GET("0777.json")
    suspend fun getHotels(): List<HotelDto>
}