package com.example.weatherapp.data.repository

import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.remote.dto.TemperatureDto
import com.example.weatherapp.domain.model.Temperature
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {
    override suspend fun getCurrentWeather(location: String, aqi: String): TemperatureDto {
        return weatherApi.getCurrentWeather(location = location, aqi = aqi)
    }
}