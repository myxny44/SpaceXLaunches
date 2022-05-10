package com.s4days.core_network.data

import com.s4days.core_network.api.NetworkApi
import com.s4days.core_network.data.model.LaunchesRemoteApi
import com.s4days.core_network.data.model.SpaceXLaunch
import retrofit2.Retrofit
import toothpick.InjectConstructor

@InjectConstructor
internal class NetworkImpl(
    private val retrofit: Retrofit
): NetworkApi {
    private fun <T> provideApi(service: Class<T>): T = retrofit.create(service)
    override suspend fun getLaunches(): List<SpaceXLaunch>? =
        provideApi(LaunchesRemoteApi::class.java).getLaunches().body()

}