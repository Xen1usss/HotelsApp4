package ks.hotelsapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ks.hotelsapp.data.HotelsRepositoryImpl
import ks.hotelsapp.domain.GetHotelsUseCase
import ks.hotelsapp.domain.Hotel
import javax.inject.Inject

@HiltViewModel
class HotelDetailsViewModel @Inject constructor( // ViewModel получает список отелей и находит нужный по id
    private val getHotelsUseCase: GetHotelsUseCase,
    private val hotelsRepository: HotelsRepositoryImpl  // Репозиторий для получения изображений
) : ViewModel() {

    private val _hotel = MutableLiveData<Hotel?>()
    val hotel: LiveData<Hotel?> = _hotel

    private val _hotelImage = MutableLiveData<String?>()
    val hotelImage: LiveData<String?> = _hotelImage

    private val _hotelImageError = MutableLiveData<Boolean>()
    val hotelImageError: LiveData<Boolean> = _hotelImageError

    fun loadHotel(hotelId: Int) {
        Log.d("loadHotel", hotelId.toString())
        viewModelScope.launch {
            val hotels = getHotelsUseCase()
            _hotel.postValue(hotels.find { it.id == hotelId })
            val imageUrl = hotelsRepository.getHotelImage(hotelId)  // Загружаем изображение
            if (imageUrl == null) {
                _hotelImageError.postValue(true)  // Устанавливаем флаг ошибки, если изображение не найдено
            } else {
                _hotelImageError.postValue(false)
            }
            _hotelImage.postValue(imageUrl)
        }
    }
}