package team.su.btmxmlversion.login

import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import team.su.btmxmlversion.config.BaseActivity
import team.su.btmxmlversion.databinding.ActivityLoginBinding
import team.su.btmxmlversion.login.adapter.LoginVpAdapter
import team.su.btmxmlversion.main.MainActivity

class LoginActivity: BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.loginTabVp.adapter = LoginVpAdapter(this)

        TabLayoutMediator(binding.loginTab, binding.loginTabVp) { tab, position ->
            when(position) {
                0 -> tab.text = "노약자"
                1 -> tab.text = "관리자"
            }
        }.attach()

    }

    override fun onResume() {
        super.onResume()

        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.signupBtn.setOnClickListener {

        }
    }

}