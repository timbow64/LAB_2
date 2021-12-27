package com.example.labo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val model by viewModels<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button_current_tmp).setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                model.getTemperature("Moscow")
            }
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(Intent(applicationContext, WeatherListActivity::class.java))
        }

        // слушаем ответ от сервера
        model.tmpNow.observe(this) {
            findViewById<TextView>(R.id.currentTemperature).text = "Текущая температура ${it}C"
        }
    }
}