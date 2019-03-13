package com.example.testweather.interfaces

import com.example.testweather.model.WeatherDate

interface IMainActivity {

    fun showWeathers(listWeather: List<WeatherDate>)
    fun showError(error: String?)
    fun upDateActionBar(nameCity: Int)

}