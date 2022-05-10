package com.s4days.core_database.data.repository

import com.s4days.core_database.data.entity.FavoriteLaunch

internal interface FavoriteRepository {

    fun getLaunches(): List<FavoriteLaunch>
    fun deleteLaunch(launch: FavoriteLaunch)
    fun addLaunch(launch: FavoriteLaunch)

}