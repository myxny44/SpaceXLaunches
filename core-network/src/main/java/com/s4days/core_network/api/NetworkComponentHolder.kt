package com.s4days.core_network.api

import com.s4days.core_network.data.di.NetworkModule
import com.s4days.module_injector.ComponentHolder
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

object NetworkComponentHolder: ComponentHolder<NetworkApi, NetworkModule> {
    private val api: NetworkApi by inject()

    override fun init(rootScope: Any) {
        if (KTP.isScopeOpen(this))
            return
        KTP.openScope(rootScope).apply {
            installModules(NetworkModule())
        }.inject(this)
    }

    override fun get(): NetworkApi = api

    override fun reset() {
        KTP.closeScope(this)
    }
}