package ks.hotelsapp.domain

interface HotelsRepository {
    suspend fun getHotels(): List<Hotel>
}