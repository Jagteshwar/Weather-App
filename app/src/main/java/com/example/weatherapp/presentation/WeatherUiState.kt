package com.example.weatherapp.presentation

import com.example.weatherapp.domain.model.Temperature

data class WeatherUiState(
    val temperature: Temperature? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)
