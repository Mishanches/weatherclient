package com.example.testweather.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testweather.R
import com.example.testweather.model.WeatherDate
import kotlinx.android.synthetic.main.weather_item.view.*
import kotlin.math.roundToLong

class WeatherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var tempKelvinScale: Double = 273.15
    private var degreeSign = "°С"

    var mData: List<WeatherDate>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
            LayoutInflater.from(p0.context).inflate(R.layout.weather_item, p0, false)) {}
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        
        val celsius: Long? = mData?.get(p1)?.main?.temperature?.minus(tempKelvinScale)?.roundToLong()

        p0.itemView.tv_date.text = mData?.get(p1)?.date
        p0.itemView.tv_temp.text = ("$celsius$degreeSign")

    }
}