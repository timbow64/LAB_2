package com.example.labo2

object API {

    fun getRetrofitService(): WeatherAPI {
        return RetrofitClient.getClient("https://api.worldweatheronline.com/")
            .create(WeatherAPI::class.java)
    }
}