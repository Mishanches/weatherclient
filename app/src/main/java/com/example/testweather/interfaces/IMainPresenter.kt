package com.example.testweather.interfaces

interface IMainPresenter {

    fun loadWeather(codeCity: String = "524901")
    fun setTitleForCity(codeCity: Int)
    fun setUpView(view: IMainActivity?)
    fun onDestroy()
    fun getCodeCity(): String

}