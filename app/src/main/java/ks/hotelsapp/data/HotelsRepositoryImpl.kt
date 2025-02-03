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
            hotelsDto.map { it.toDomainModel() }
        } catch (e: Exception) {
            Log.e("HotelsRepository", "Ошибка загрузки данных", e)
            emptyList()
        }
    }
    suspend fun getHotelImage(id: Int): String? {
        return try {
            val hotelImageDto = hotelsApi.getHotelImage(id)
            hotelImageDto.image?.let {
                return "https://github.com/iMofas/ios-android-test/raw/master/$it"
            }
            Log.d("HotelImage", "Изображение не найдено, поле image = null")
            null
        } catch (e: Exception) {
            Log.e("HotelsRepository", "Ошибка загрузки изображения", e)
            null
        }
    }
}