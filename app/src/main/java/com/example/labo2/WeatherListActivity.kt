package com.example.labo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherListActivity : AppCompatActivity() {

    private val cities = listOf(
        "Dubna",
        "Vladivostok", "Yekaterinburg", "Moscow",
        "Novosibirsk", "Anapa", "Astrakhan",
        "Blagoveshchensk", "Omsk", "Barnaul", "Gudermes",
        "Beslan", "Kirov", "Bryansk", "Yeysk"
    )

    private val model by viewModels<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        lifecycleScope.launch(Dispatchers.IO) {
            model.getTemperature(cities)
        }

        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(applicationContext)

        // слушаем получение городов
        model.citiesWeather.observe(this) {
            rv.adapter = WeatherRvAdapter(it)
        }
    }
}