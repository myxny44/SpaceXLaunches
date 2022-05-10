package com.s4days.core_database.data.repository

import com.s4days.core_database.data.entity.FavoriteLaunch
import com.s4days.core_database.data.room.database.SpaceXLaunchDatabase
import toothpick.InjectConstructor

@InjectConstructor
internal class FavoriteRepositoryImpl(
    private val database: SpaceXLaunchDatabase
): FavoriteRepository {
    override fun getLaunches(): List<FavoriteLaunch> = database.favoritesDao().getFavorites()

    override fun deleteLaunch(launch: FavoriteLaunch) {
        database.favoritesDao().deleteFavorite(launch)
    }

    override fun addLaunch(launch: FavoriteLaunch) {
        database.favoritesDao().insert(launch)
    }

}