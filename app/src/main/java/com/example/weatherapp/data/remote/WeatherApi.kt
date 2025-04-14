package com.example.weatherapp.data.remote

import com.example.weatherapp.data.remote.dto.TemperatureDto
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.weatherapp.BuildConfig

interface WeatherApi {


    @GET("v1/current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("q") location: String,
        @Query("aqi") aqi: String
    ): TemperatureDto
}