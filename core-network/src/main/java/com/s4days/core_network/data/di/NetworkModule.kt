package com.s4days.core_network.data.di

import com.s4days.core_network.api.NetworkApi
import com.s4days.core_network.data.NetworkImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import toothpick.config.Module

internal class NetworkModule: Module() {
    init {

        val baseUrl = "https://api.spacexdata.com/"

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        bind(Retrofit::class.java).toInstance(retrofit)
        bind(NetworkApi::class.java).to(NetworkImpl::class.java).singleton()
    }
}