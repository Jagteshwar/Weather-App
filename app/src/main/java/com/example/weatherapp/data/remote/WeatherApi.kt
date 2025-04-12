package com.example.weatherapp.data.remote

import com.example.weatherapp.data.remote.dto.TemperatureDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String,
    ): TemperatureDto
}