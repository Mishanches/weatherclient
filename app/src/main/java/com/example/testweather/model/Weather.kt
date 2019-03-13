package com.example.testweather.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("cod") val code: String,
    @SerializedName("list") val weatherDates: List<WeatherDate>
)

data class WeatherDate(
    @SerializedName("main") val main: WeatherMain,
    @SerializedName("dt_txt") val date: String
)

data class WeatherMain(
    @SerializedName("temp") val temperature: Double)