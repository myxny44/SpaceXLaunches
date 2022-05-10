package com.s4days.feature_launches.api

import com.s4days.feature_launches.data.di.LaunchesModule
import com.s4days.module_injector.ComponentHolder
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

object LaunchesComponentHolder: ComponentHolder<LaunchesApi, LaunchesModule> {
    private val api: LaunchesApi by inject()

    override fun init(rootScope: Any) {
        if (KTP.isScopeOpen(this))
            return

        KTP.openScope(rootScope).apply {
            installModules(LaunchesModule())
        }.inject(this)
    }

    override fun get(): LaunchesApi = api

    override fun reset() {
        KTP.closeScope(this)
    }
}