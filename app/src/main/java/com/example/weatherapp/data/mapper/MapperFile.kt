package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.remote.dto.CurrentDto
import com.example.weatherapp.data.remote.dto.LocationDto
import com.example.weatherapp.data.remote.dto.TemperatureDto
import com.example.weatherapp.domain.model.Current
import com.example.weatherapp.domain.model.Location
import com.example.weatherapp.domain.model.Temperature

fun LocationDto.toLocation(): Location = Location(
    country = country,
    lat = lat,
    localtime = localtime,
    localtime_epoch = localtime_epoch,
    lon = lon,
    name = name
)

fun CurrentDto.toCurrent(): Current = Current(
    temp_c = temp_c,
    temp_f = temp_f
)

fun TemperatureDto.toTemperature(): Temperature = Temperature(
    location = location.toLocation(),
    current = current.toCurrent()
)