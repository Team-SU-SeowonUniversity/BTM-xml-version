package team.su.btmxmlversion.base

import android.app.Application
import android.content.SharedPreferences

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