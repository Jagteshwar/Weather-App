package com.example.weatherapp.domain.usecases

import com.example.weatherapp.data.mapper.toTemperature
import com.example.weatherapp.domain.model.Temperature
import com.example.weatherapp.domain.repository.WeatherRepository

class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(location: String): Temperature{
        return repository.getCurrentWeather(location).toTemperature()
    }
}