package com.example.testweather.dagger

import com.example.testweather.api.ServiceGenerator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun getServiceGenerator(): ServiceGenerator {
        return ServiceGenerator
    }

}