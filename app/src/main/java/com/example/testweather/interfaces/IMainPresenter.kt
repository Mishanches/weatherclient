package com.example.testweather.interfaces

interface IMainPresenter {

    fun loadWeather(codeCity: String = "524901")
    fun setUpView(view: IMainActivity?)
    fun getCodeCity(): String
    fun onNavigationItem(item: Int)
    fun onDestroy()

}