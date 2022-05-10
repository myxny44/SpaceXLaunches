package com.s4days.core_database.api

import com.s4days.core_database.data.entity.FavoriteLaunch
import com.s4days.module_injector.BaseAPI

interface DatabaseApi: BaseAPI {

    fun getLaunches(): List<FavoriteLaunch>
    fun deleteLaunch(launch: FavoriteLaunch)
    fun addLaunch(launch: FavoriteLaunch)

}