package ks.hotelsapp.domain

data class Hotel(
    val id: Int,
    val name: String,
    val address: String,
    val stars: Float,
    val distance: Float,
    val availableSuitesCount: Int
)