package com.s4days.core_navigation.data.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.s4days.core_database.api.DatabaseApi
import com.s4days.core_database.api.DatabaseComponentHolder
import com.s4days.core_navigation.data.ViewModelFactory
import com.s4days.core_network.api.NetworkApi
import com.s4days.core_network.api.NetworkComponentHolder
import toothpick.config.Module

internal class AppModule(application: Application): Module() {
    init {

        bind(Application::class.java).toInstance( application )
        bind(Context::class.java).toInstance( application )
        bind(NetworkApi::class.java).toInstance( NetworkComponentHolder.initAndGet( application ) )
        bind(DatabaseApi::class.java).toInstance( DatabaseComponentHolder.initAndGet( application ) )

        bind(ViewModelProvider.NewInstanceFactory::class.java).toInstance( ViewModelFactory(application) )
    }
}