package com.s4days.core_navigation.api

import android.app.Application
import com.s4days.core_navigation.data.di.AppModule
import com.s4days.core_navigation.data.di.NavigationModule
import com.s4days.module_injector.ComponentHolder
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

object NavigationComponentHolder : ComponentHolder<NavigationApi, NavigationModule> {
    private val api: NavigationApi by inject()

    override fun init(rootScope: Any){

        if (KTP.isScopeOpen(this))
            return

        KTP.openScope(rootScope).apply {

            installModules( AppModule(rootScope as Application) )
            installModules( NavigationModule() )
        }.inject(this)

    }

    override fun get(): NavigationApi = api

    override fun reset() {
        KTP.closeScope(this)
    }
}