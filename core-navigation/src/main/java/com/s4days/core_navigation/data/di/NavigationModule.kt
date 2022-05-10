package com.s4days.core_navigation.data.di

import com.s4days.core_navigation.api.NavigationApi
import com.s4days.core_navigation.data.NavigationImpl
import toothpick.config.Module

internal class NavigationModule(): Module() {

    init {
        bind(NavigationApi::class.java).to(NavigationImpl::class.java).singleton()
    }
}