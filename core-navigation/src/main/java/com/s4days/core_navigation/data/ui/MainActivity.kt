package com.s4days.core_navigation.data.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.s4days.core_navigation.R
import com.s4days.core_navigation.data.di.AppModule
import toothpick.ktp.KTP

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    var doubleBackToExitPressedOnce = false
    var backTwiceTimeout: Long = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependencyInjection()

        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount != 0 || !isTaskRoot) {
            super.onBackPressed()
            return
        }

        if (doubleBackToExitPressedOnce) finishAffinity()

        doubleBackToExitPressedOnce = true
        Toast.makeText(this, R.string.press_back_again_to_exit, Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(
            { doubleBackToExitPressedOnce = false },
            backTwiceTimeout
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initDependencyInjection() {

        KTP.openScope(applicationContext).installModules(AppModule(application)).inject(this)
    }
}