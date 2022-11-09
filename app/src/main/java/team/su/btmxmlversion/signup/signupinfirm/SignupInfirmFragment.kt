package team.su.btmxmlversion.signup.signupinfirm

import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import androidx.core.app.ActivityCompat
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentSignupInfirmBinding
import team.su.btmxmlversion.main.infirmMain.MainActivity
import team.su.btmxmlversion.signup.SignupActivity

class SignupInfirmFragment:
    BaseFragment<FragmentSignupInfirmBinding>(FragmentSignupInfirmBinding::bind, R.layout.fragment_signup_infirm) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.phoneNumInput.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }

    override fun onResume() {
        super.onResume()

        binding.checkBtn.setOnClickListener {
            val phoneNumber = binding.phoneNumInput.text.toString()
            val name = binding.name.text.toString()
            var isSignupPass = true

            val userTable = this.activity?.getSharedPreferences("USER_TABLE", 0)
            val userInfo = this.activity?.getSharedPreferences("USER_INFO", 0)
            val diagnosisResult = this.activity?.getSharedPreferences("DIAGNOSIS_RESULT", 0)
            val score = this.activity?.getSharedPreferences("SCORE", 0)

            for (count in 1..userTable?.getInt("USER_COUNT",0)!!) {
                if(phoneNumber == userTable.getString("${count}_PHONE_NUMBER", "")) {
                    isSignupPass = false
                    showCustomToast("중복된 전화번호 입니다.")
                }
            }

            if (isSignupPass) {
                userTable.edit().apply {
                    val userCount = userTable.getInt("USER_COUNT", 0)
                    this?.putInt("USER_COUNT", userCount + 1)
                    this?.putString("${userCount.plus(1)}_PHONE_NUMBER", phoneNumber)
                    this?.putString("${userCount.plus(1)}_NAME", name)
                }?.apply()

                userInfo?.edit()?.apply {
                    putBoolean("AUTO_LOGIN", true)
                    putString("USING_USER_PHONE_NUMBER", phoneNumber)
                    putString("USING_USER_NAME", name)
                }?.apply()

                diagnosisResult?.edit()?.apply {
                    putInt("NUMBER", 0)
                }?.apply()

                score?.edit()?.apply {
                    putFloat("INTUITION_SCORE", 0f)
                    putFloat("ANALYSIS_SCORE", 0f)
                    putFloat("CALCULATION_SCORE", 0f)
                    putFloat("MEMORY_SCORE", 0f)
                    putFloat("PERCEPTION_SCORE", 0f)
                }?.apply()

                startActivity(Intent(binding.root.context, MainActivity::class.java))
                ActivityCompat.finishAffinity(context as SignupActivity)
            }
        }
    }

}