package com.s4days.core_database.api

import android.content.Context
import com.s4days.core_database.data.di.DatabaseModule
import com.s4days.module_injector.ComponentHolder
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

object DatabaseComponentHolder: ComponentHolder<DatabaseApi, DatabaseModule> {

    private val api: DatabaseApi by inject()

    override fun init(rootScope: Any) {
        if (KTP.isScopeOpen(rootScope))
            return

        KTP.openScope(rootScope).apply {
            installModules(DatabaseModule(rootScope as Context))
        }.inject(this)
    }

    override fun get(): DatabaseApi = api

    override fun reset() {
        KTP.closeScope(this)
    }
}