package com.s4days.core_navigation.api

import android.app.Activity
import android.os.Bundle
import com.s4days.module_injector.BaseAPI
import java.lang.Exception

interface NavigationApi : BaseAPI {

    fun navigateTo(link: String, bundle: Bundle)
    fun navigateTo(link: String, bundle: Bundle, onError: Runnable)
    fun navigateTo(activity: Activity, endpoint: String, bundle: Bundle)
    fun onError(e: Exception)

}