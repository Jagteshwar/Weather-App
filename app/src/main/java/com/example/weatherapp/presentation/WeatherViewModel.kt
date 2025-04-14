package com.example.weatherapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.domain.usecases.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeather: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _weatherUiState = MutableStateFlow(WeatherUiState())
    val weatherUiState: StateFlow<WeatherUiState> = _weatherUiState

    private val _location = MutableStateFlow("")
    val location: StateFlow<String> = _location

    fun updateLocation(location: String){
        _location.value = location
    }

    fun getWeatherWithLocation(aqi: String = "no"){
        viewModelScope.launch {
        _weatherUiState.value = _weatherUiState.value.copy(isLoading = true)
            try{
                val temp = getWeather(_location.value, aqi)
            _weatherUiState.value = WeatherUiState(
                temperature = temp,
                isLoading = false
            )
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}