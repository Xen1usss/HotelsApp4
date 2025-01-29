package ks.hotelsapp.data

import android.util.Log
import ks.hotelsapp.domain.Hotel
import ks.hotelsapp.domain.HotelsRepository
import javax.inject.Inject

class HotelsRepositoryImpl @Inject constructor(
    private val hotelsApi: HotelsApi
) : HotelsRepository {
    override suspend fun getHotels(): List<Hotel> {
        return try {
            val hotelsDto = hotelsApi.getHotels()
            Log.d("HotelsApi", "API Response: $hotelsDto") // Лог данных от API
            hotelsDto.map { it.toDomainModel() }
        } catch (e: Exception) {
            Log.e("HotelsRepositoryImpl", "Error fetching hotels: ${e.message}", e)
            emptyList()
        }
    }
}