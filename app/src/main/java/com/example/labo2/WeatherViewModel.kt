package com.example.labo2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class WeatherViewModel : ViewModel() {

    val tmpNow = MutableLiveData<String>()
    val citiesWeather = MutableLiveData<List<Pair<String, String>>>()

    suspend fun getTemperature(city: String) {
        getWeather(city = city, onSuccess = (tmpNow::postValue))
    }

    suspend fun getTemperature(cities: List<String>) {
        val list = mutableListOf<Pair<String, String>>()
        cities.forEach { city ->
            getWeather(city = city) {
                list.add(Pair(city, it))
                citiesWeather.postValue(list)
            }
        }
    }

    private suspend fun getWeather(city: String, onSuccess: (String) -> Unit) {
        flow {
            emit(API.getRetrofitService().getWeather(q = city))
        }.collect {
            onSuccess(it.data?.current_condition?.get(0)?.temp_C.toString())
        }
    }
}