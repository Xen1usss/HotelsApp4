package ks.hotelsapp.data

import ks.hotelsapp.domain.Hotel
import ks.hotelsapp.domain.HotelsRepository
import javax.inject.Inject

class HotelsRepositoryImpl @Inject constructor(
    private val hotelsApi: HotelsApi
) : HotelsRepository {
    override suspend fun getHotels(): List<Hotel> {
        return hotelsApi.getHotels().map { it.toDomainModel() }
    }
}