package team.su.btmxmlversion.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import team.su.btmxmlversion.base.BaseActivity
import team.su.btmxmlversion.databinding.ActivitySplashBinding
import team.su.btmxmlversion.ui.main.infirmMain.MainActivity

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
            val isAutoLogin = getSharedPreferences("BTM_APP", 0).getBoolean("autoLogin", false)

            if(isAutoLogin) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, team.su.btmxmlversion.ui.login.LoginActivity::class.java))
                finish()
            }
        }, 1500)

    }

}