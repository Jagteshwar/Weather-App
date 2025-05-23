package com.example.weatherapp.di

import com.example.weatherapp.common.Constants
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.usecases.GetCurrentWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesWeatherApi(): WeatherApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun providesWeatherRepository(api: WeatherApi): WeatherRepository{
        return WeatherRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesGetWeatherUseCase(repository: WeatherRepository): GetCurrentWeatherUseCase{
        return GetCurrentWeatherUseCase(repository)
    }
}