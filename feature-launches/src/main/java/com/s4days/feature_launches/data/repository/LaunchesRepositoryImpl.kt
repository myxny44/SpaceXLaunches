package com.s4days.feature_launches.data.repository

import com.s4days.core_database.api.DatabaseApi
import com.s4days.core_database.data.entity.FavoriteLaunch
import com.s4days.core_network.api.NetworkApi
import com.s4days.core_network.data.model.SpaceXLaunch
import toothpick.InjectConstructor

@InjectConstructor
internal class LaunchesRepositoryImpl(
    private val networkApi: NetworkApi,
    private val databaseApi: DatabaseApi
): LaunchesRepository {

    override suspend fun getLaunches(): List<SpaceXLaunch>? =
        networkApi.getLaunches()?.sortedByDescending { it.dateUnix }

    override suspend fun getFavorites(): List<String> = databaseApi.getLaunches().map { it.launchId }

    override suspend fun deleteFavorites(launch: String) {
        databaseApi.deleteLaunch(FavoriteLaunch(launch))
    }

    override suspend fun addFavorites(launch: String) {
        databaseApi.addLaunch(FavoriteLaunch(launch))
    }

}