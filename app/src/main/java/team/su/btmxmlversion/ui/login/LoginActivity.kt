package team.su.btmxmlversion.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import team.su.btmxmlversion.base.BaseActivity
import team.su.btmxmlversion.databinding.ActivityLoginBinding
import team.su.btmxmlversion.ui.login.adapter.LoginVpAdapter
import team.su.btmxmlversion.ui.main.infirmMain.MainActivity
import team.su.btmxmlversion.ui.signup.SignupActivity

class LoginActivity: BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val viewModel: team.su.btmxmlversion.ui.login.LoginViewModel by viewModels()

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
            val userTable = getSharedPreferences("USER_TABLE", 0)
            val userInfo = getSharedPreferences("USER_INFO", 0)
            val diagnosisResult = getSharedPreferences("DIAGNOSIS_RESULT", 0)
            val score = getSharedPreferences("SCORE", 0)
            var isLoginPass = false

            val totalUserNum = userTable.getInt("USER_COUNT", 0)
            var inputPhoneNumber = ""

            viewModel.phoneNumInput.observe(this) {
                inputPhoneNumber = it
            }

            for (count in 1..totalUserNum) {
                val userPhoneNumber = userTable.getString("${count}_PHONE_NUMBER", "")
                if (userPhoneNumber == inputPhoneNumber) {
                    isLoginPass = true
                }
            }

            if (isLoginPass) {
                val user = getSharedPreferences(inputPhoneNumber, 0)

                userInfo.edit().apply {
                    putString("USING_USER_PHONE_NUMBER", inputPhoneNumber)
                    putString("USING_USER_NAME", user.getString("name", ""))
                    putBoolean("AUTO_LOGIN", true)
                }.apply()

                diagnosisResult.edit().apply {
                    putInt("NUMBER", user.getInt("diagnosisNum", 0))
                }.apply()

                diagnosisResult.edit().apply {
                    for (number in 1..diagnosisResult.getInt("NUMBER", 0)) {
                        putString(
                            "${number}_ITEM_RESULT_TIME",
                            user.getString("${number}_itemResultTime", "")
                        )
                        putInt("${number}_ITEM_PASS", user.getInt("${number}_itemPass", 0))
                        putInt("${number}_ITEM_FAIL", user.getInt("${number}_itemFail", 0))
                    }
                }.apply()

                score.edit().apply {
                    putFloat("INTUITION_SCORE", user.getFloat("intuitionScore", 0f))
                    putFloat("ANALYSIS_SCORE", user.getFloat("analysisScore", 0f))
                    putFloat("CALCULATION_SCORE", user.getFloat("calculationScore", 0f))
                    putFloat("MEMORY_SCORE", user.getFloat("memoryScore", 0f))
                    putFloat("PERCEPTION_SCORE", user.getFloat("perceptionScore", 0f))
                }.apply()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                showCustomToast("전화번호를 확인 해주세요.")
            }
        }

        binding.signupBtn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

}