package team.su.btmxmlversion.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import team.su.btmxmlversion.base.BaseActivity
import team.su.btmxmlversion.databinding.ActivitySplashBinding
import team.su.btmxmlversion.login.LoginActivity
import team.su.btmxmlversion.main.infirmMain.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.logoText.text = StringBuilder().apply {
            append("뇌를\n")
            append(" 훈련하는\n")
            append("  아침")
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val isAutoLogin = getSharedPreferences("USER_INFO", 0).getBoolean("AUTO_LOGIN", false)

            if(isAutoLogin) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }

        }, 1500)

    }

}