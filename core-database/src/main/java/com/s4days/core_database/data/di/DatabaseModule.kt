package com.s4days.core_database.data.di

import android.content.Context
import androidx.room.Room
import com.s4days.core_database.api.DatabaseApi
import com.s4days.core_database.data.DatabaseImpl
import com.s4days.core_database.data.repository.FavoriteRepository
import com.s4days.core_database.data.repository.FavoriteRepositoryImpl
import com.s4days.core_database.data.room.database.SpaceXLaunchDatabase
import toothpick.InjectConstructor
import toothpick.config.Module
import javax.inject.Singleton

@Singleton
@InjectConstructor
internal class DatabaseModule(context: Context) : Module() {
    init {
        bind(Context::class.java).toInstance(context)

        val favoritesDatabase = Room
            .databaseBuilder(
                context.applicationContext,
                SpaceXLaunchDatabase::class.java,
                "SpaceXLaunchDatabase.db"
            )
            .build()

        bind(FavoriteRepository::class.java).to(FavoriteRepositoryImpl::class.java).singleton()

        bind(SpaceXLaunchDatabase::class.java).toInstance(favoritesDatabase)
        bind(DatabaseApi::class.java).to(DatabaseImpl::class.java).singleton()
    }
}