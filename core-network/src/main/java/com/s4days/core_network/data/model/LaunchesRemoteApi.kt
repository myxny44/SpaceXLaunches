package com.s4days.core_network.data.model

import retrofit2.Response
import retrofit2.http.GET

internal interface LaunchesRemoteApi {
    @GET("/v4/launches")
    suspend fun getLaunches(): Response<List<SpaceXLaunch>>
}