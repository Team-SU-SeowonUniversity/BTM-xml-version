package team.su.btmxmlversion.base

import android.app.Application
import android.content.SharedPreferences

class ApplicationClass: Application() {

    companion object {
        lateinit var valueSp: SharedPreferences
        lateinit var scoreSp: SharedPreferences
        lateinit var resultSp: SharedPreferences
        lateinit var userInfoSp: SharedPreferences
        lateinit var userTableSp: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        valueSp = applicationContext.getSharedPreferences("BTM_APP", MODE_PRIVATE)
        //valueSp.edit().clear().apply()
        scoreSp = applicationContext.getSharedPreferences("SCORE", MODE_PRIVATE)
        //scoreSp.edit().clear().apply()
        resultSp = applicationContext.getSharedPreferences("DIAGNOSIS_RESULT", MODE_PRIVATE)
        //resultSp.edit().clear().apply()
        userInfoSp = applicationContext.getSharedPreferences("USER_INFO", MODE_PRIVATE)
        //userInfoSp.edit().clear().apply()
        userTableSp = applicationContext.getSharedPreferences("USER_TABLE", MODE_PRIVATE)
        //userTableSp.edit().clear().apply()
    }

}