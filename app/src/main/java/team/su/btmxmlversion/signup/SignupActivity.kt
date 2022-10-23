package team.su.btmxmlversion.signup

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import team.su.btmxmlversion.config.BaseActivity
import team.su.btmxmlversion.databinding.ActivitySignupBinding
import team.su.btmxmlversion.signup.adapter.QuizTypeVpAdapter
import team.su.btmxmlversion.signup.adapter.SignupVpAdapter

class SignupActivity: BaseActivity<ActivitySignupBinding>(ActivitySignupBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.signupVp.adapter = SignupVpAdapter(this)

        TabLayoutMediator(binding.signupTab, binding.signupVp) { tab, position ->
            when(position) {
                0 -> tab.text = "노약자"
                1 -> tab.text = "관리자"
            }
        }.attach()

    }

    override fun onResume() {
        super.onResume()

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}