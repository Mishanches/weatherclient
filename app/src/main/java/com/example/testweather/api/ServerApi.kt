package com.example.testweather.api

import com.example.testweather.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerApi {

    @GET("data/2.5/forecast")
    fun getWeather(@Query("id") id: String = "524901",
                   @Query("appid") appid: String = "0cc5df6f550b8d5597d1b49e0bf145f3"): Single<WeatherResponse>
}