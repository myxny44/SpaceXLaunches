package com.s4days.core_database.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.s4days.core_database.data.entity.FavoriteLaunch
import com.s4days.core_database.data.entity.FavoritesDAO

@Database(entities = [FavoriteLaunch::class], version = 1, exportSchema = true)
abstract class SpaceXLaunchDatabase: RoomDatabase() {

    internal abstract fun favoritesDao() : FavoritesDAO

}