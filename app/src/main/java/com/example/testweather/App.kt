package com.example.testweather

import android.app.Application
import com.example.testweather.dagger.*

class App: Application(){

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    fun getAppComponent() = appComponent

}