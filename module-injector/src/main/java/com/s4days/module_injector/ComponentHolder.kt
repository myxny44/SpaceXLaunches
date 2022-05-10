package com.s4days.module_injector

import toothpick.config.Module

interface ComponentHolder <C : BaseAPI, D : Module> {

    fun init(rootScope: Any)

    fun get(): C

    fun reset()

    fun initAndGet(rootScope: Any): C {
        init(rootScope)
        return get()
    }
}

interface BaseAPI
