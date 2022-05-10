package com.s4days.core_database.data

import com.s4days.core_database.api.DatabaseApi
import com.s4days.core_database.data.entity.FavoriteLaunch
import com.s4days.core_database.data.repository.FavoriteRepository
import toothpick.InjectConstructor

@InjectConstructor
internal class DatabaseImpl(
    private val repository: FavoriteRepository
): DatabaseApi {
    override fun getLaunches(): List<FavoriteLaunch> = repository.getLaunches()

    override fun deleteLaunch(launch: FavoriteLaunch) {
        repository.deleteLaunch(launch)
    }

    override fun addLaunch(launch: FavoriteLaunch) {
        repository.addLaunch(launch)
    }
}