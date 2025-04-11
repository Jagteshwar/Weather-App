package com.example.weatherapp.domain.model

import com.example.weatherapp.data.remote.dto.ConditionDto

data class Current(
    val temp_c: Double,
    val temp_f: Double,
)
