package com.s4days.feature_launches.data.di

import com.s4days.feature_launches.api.LaunchesApi
import com.s4days.feature_launches.data.LaunchesImpl
import com.s4days.feature_launches.data.repository.LaunchesRepository
import com.s4days.feature_launches.data.repository.LaunchesRepositoryImpl
import toothpick.config.Module

internal class LaunchesModule: Module() {
    init {
        bind(LaunchesRepository::class.java).to(LaunchesRepositoryImpl::class.java)
        bind(LaunchesApi::class.java).to(LaunchesImpl::class.java).singleton()
    }
}