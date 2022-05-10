package com.s4days.spacexlaunces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.s4days.core_navigation.api.NavigationComponentHolder

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        NavigationComponentHolder.initAndGet(applicationContext).navigateTo("launches", Bundle.EMPTY)
    }
}