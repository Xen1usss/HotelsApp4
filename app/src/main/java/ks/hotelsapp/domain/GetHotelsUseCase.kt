package ks.hotelsapp.domain

import javax.inject.Inject

class GetHotelsUseCase @Inject constructor(
    private val repository: HotelsRepository
) {
    suspend operator fun invoke(): List<Hotel> = repository.getHotels()
}
