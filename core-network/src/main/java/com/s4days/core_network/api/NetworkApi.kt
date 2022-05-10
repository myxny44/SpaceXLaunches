package com.s4days.core_network.api

import com.s4days.core_network.data.model.SpaceXLaunch
import com.s4days.module_injector.BaseAPI

interface NetworkApi : BaseAPI {
    suspend fun getLaunches(): List<SpaceXLaunch>?
}