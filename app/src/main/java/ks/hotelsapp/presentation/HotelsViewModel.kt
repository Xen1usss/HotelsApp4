package ks.hotelsapp.presentation

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
    // private val repository: HotelsRepositoryImpl,
    private val getHotelsUseCase: GetHotelsUseCase// getHotelsUseCase: GetHotelsUseCase
) : ViewModel() {

    private val _hotels = MutableLiveData<List<Hotel>>()
    val hotels: LiveData<List<Hotel>> = _hotels

    fun loadHotels() {
        viewModelScope.launch {
            try {
                _hotels.value = getHotelsUseCase()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
