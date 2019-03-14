package com.example.testweather.dagger

import com.example.testweather.MainPresenter
import com.example.testweather.api.ServiceGenerator
import com.example.testweather.interfaces.IMainPresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    fun provideMainPresenter(serviceGenerator: ServiceGenerator): IMainPresenter {
        return MainPresenter(serviceGenerator)
    }

}