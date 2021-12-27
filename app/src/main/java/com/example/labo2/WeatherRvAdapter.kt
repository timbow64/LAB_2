package com.example.labo2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherRvAdapter(
    private val list: List<Pair<String, String>>
) : RecyclerView.Adapter<WeatherRvAdapter.WeatherViewHolder>() {


    class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val city: TextView = view.findViewById(R.id.city)
        val temperature: TextView = view.findViewById(R.id.temperature)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weater_item, parent, false)
        return WeatherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        with(list[position]) {
            holder.city.text = first
            holder.temperature.text = "Температура в городе: ${second}C"
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}