package com.s4days.core_database.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoritesLaunch")
data class FavoriteLaunch(
    @PrimaryKey(autoGenerate = false)
    val launchId: String
)
