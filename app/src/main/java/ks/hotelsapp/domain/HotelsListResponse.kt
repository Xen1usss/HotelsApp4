package ks.hotelsapp.domain

//data class HotelsListResponse()

data class Hotel(
    val name: String,
    val rating: Float,
    val availableRooms: Int,
    val distance: String)
