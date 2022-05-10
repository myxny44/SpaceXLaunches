package com.s4days.feature_launches.data.repository

import com.s4days.core_network.api.NetworkApi
import com.s4days.core_network.data.model.SpaceXLaunch
import toothpick.InjectConstructor

@InjectConstructor
internal class LaunchesRepositoryImpl(
    private val networkApi: NetworkApi
): LaunchesRepository {

    override suspend fun getLaunches(): List<SpaceXLaunch>? =
        networkApi.getLaunches()?.sortedByDescending { it.dateUnix }

}