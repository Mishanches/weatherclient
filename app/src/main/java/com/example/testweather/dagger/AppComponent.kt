package com.example.testweather.dagger

import com.example.testweather.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, PresenterModule::class, RepositoryModule::class])

interface AppComponent {

    fun inject(mainActivity: MainActivity)

}