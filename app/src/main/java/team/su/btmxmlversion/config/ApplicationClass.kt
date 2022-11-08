package team.su.btmxmlversion.config

import android.app.Application
import android.content.SharedPreferences
import android.util.Log

class ApplicationClass: Application() {

    companion object {
        lateinit var sSharedPreferences: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        sSharedPreferences = applicationContext.getSharedPreferences("BTM_APP", MODE_PRIVATE)
        sSharedPreferences.edit().clear().apply()
    }

}