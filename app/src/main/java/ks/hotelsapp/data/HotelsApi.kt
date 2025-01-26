package ks.hotelsapp.data

import retrofit2.http.GET
import ks.hotelsapp.domain.Hotel

// API-интерфейс

interface HotelsApi {
    @GET("0777.json")
    suspend fun getHotels(): List<HotelDto>
}