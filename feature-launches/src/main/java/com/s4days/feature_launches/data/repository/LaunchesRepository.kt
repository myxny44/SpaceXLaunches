package com.s4days.feature_launches.data.repository

import com.s4days.core_database.data.entity.FavoriteLaunch
import com.s4days.core_network.data.model.SpaceXLaunch

internal interface LaunchesRepository {
    suspend fun getLaunches(): List<SpaceXLaunch>?
    suspend fun getFavorites(): List<String>
    suspend fun deleteFavorites(launch: String)
    suspend fun addFavorites(launch: String)
}