package ks.hotelsapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ks.hotelsapp.domain.GetHotelsUseCase
import ks.hotelsapp.domain.Hotel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelsViewModel @Inject constructor(

    private val getHotelsUseCase: GetHotelsUseCase
) : ViewModel() {

    private val _hotels = MutableLiveData<List<Hotel>>()
    val hotels: LiveData<List<Hotel>> = _hotels

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadHotels()
    }

    fun loadHotels() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _hotels.postValue(getHotelsUseCase())
            } catch (e: Exception) {
                Log.e("HotelsViewModel", "Ошибка загрузки отелей", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}