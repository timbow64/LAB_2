package com.example.labo2

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("premium/v1/weather.ashx")
    suspend fun getWeather(
        @Query("q") q: String = "Russia",
        @Query("key") apiKey: String = "54b4849fcc9b4ef59f7183543211812",
        @Query("num_of_days") numOfDays: Int = 1,
        @Query("format") format: String = "json"
    ): Root
}

data class Data(var current_condition: List<CurrentCondition>? = null)
data class Root(var data: Data? = null)
data class CurrentCondition(var temp_C: String? = null)
