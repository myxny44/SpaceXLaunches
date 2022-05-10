package com.s4days.core_database.data.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface FavoritesDAO {

    @Insert
    fun insert(favorite: FavoriteLaunch)

    @Query("SELECT * FROM FavoritesLaunch")
    fun getFavorites(): List<FavoriteLaunch>

    @Delete
    fun deleteFavorite(favorite: FavoriteLaunch)

}