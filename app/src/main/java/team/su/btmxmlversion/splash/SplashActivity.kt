package team.su.btmxmlversion.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import team.su.btmxmlversion.config.BaseActivity
import team.su.btmxmlversion.databinding.ActivitySplashBinding
import team.su.btmxmlversion.login.LoginActivity

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
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1500)

    }

}