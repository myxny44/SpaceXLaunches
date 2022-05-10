package com.s4days.core_navigation.data

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.s4days.core_navigation.R
import com.s4days.core_navigation.api.NavigationApi
import toothpick.InjectConstructor

@InjectConstructor
internal class NavigationImpl(private val context: Context) : NavigationApi {

    override fun navigateTo(link: String, bundle: Bundle) {
        try {
            performNavigate(link, bundle)
        } catch (e: Exception) {
            onError(e)
        }
    }

    override fun navigateTo(link: String, bundle: Bundle, onError: Runnable) {
        try {
            performNavigate(link, bundle)
        } catch (e: Exception) {
            onError(e)
        }
    }

    override fun onError(e: Exception) {
        Log.e(javaClass.simpleName, e.stackTraceToString())
    }

    override fun navigateTo(activity: Activity, endpoint: String, bundle: Bundle) {
        try {
            val link: String = context.getString(R.string.deeplink_base_url) + endpoint
            activity.startActivity(
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(link)
                    putExtras(bundle)
                }
            )
        } catch (e: Exception) {
            onError(e)
        }
    }

    private fun performNavigate(endpoint: String, bundle: Bundle) {
        val link: String = context.getString(R.string.deeplink_base_url) + endpoint
        val app = context as Application
        app.startActivity(
            Intent(Intent.ACTION_VIEW).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                data = Uri.parse(link)
                putExtras(bundle)
            }
        )
    }
}