package com.example.weatherapp.data.repository

import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.remote.dto.TemperatureDto
import com.example.weatherapp.domain.model.Temperature
import com.example.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override suspend fun getCurrentWeather(location: String): TemperatureDto {
        return weatherApi.getCurrentWeather("",location)
    }
}