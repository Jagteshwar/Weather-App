package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.remote.dto.TemperatureDto
import com.example.weatherapp.domain.model.Temperature
import retrofit2.http.Query

interface WeatherRepository {

    suspend fun getCurrentWeather(location: String,api: String): TemperatureDto
}