package team.su.btmxmlversion.ui.signup.signupinfirm

import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentSignupInfirmBinding
import team.su.btmxmlversion.network.CommonDataServiceLocator
import team.su.btmxmlversion.dto.SignupInfirmRequestBody
import team.su.btmxmlversion.models.SignupInfirmResponse
import team.su.btmxmlversion.repository.SignupInfirmRepository
import team.su.btmxmlversion.ui.main.infirmMain.MainActivity
import team.su.btmxmlversion.ui.signup.SignupActivity
import java.util.regex.Pattern

class SignupInfirmFragment:
    BaseFragment<FragmentSignupInfirmBinding>(FragmentSignupInfirmBinding::bind, R.layout.fragment_signup_infirm), SignupInfirmCallback {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.phoneNumInput.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }

    override fun onResume() {
        super.onResume()

        binding.checkBtn.setOnClickListener {
            val phoneNumber = binding.phoneNumInput.text.toString()
            val name = binding.name.text.toString()

            if (Pattern.matches("^\\d{3}-\\d{3,4}-\\d{4}$", phoneNumber)) {
                if (Pattern.matches("^[가-힣]*$", name)) {
                    showLoadingDialog(binding.root.context)
                    SignupInfirmRepository(CommonDataServiceLocator.signupService)
                        .tryInfirmSignup(SignupInfirmRequestBody(phoneNumber, name), this)
                } else {
                    showCustomToast("이름을 한글만 입력해주세요.")
                    Log.d("이름 문제", "이름 문제")
                }
            } else {
                showCustomToast("전화번호 다시 입력 해주세요.")
                Log.d("전화번호 문제", "전화번호 문제")
            }

        }
    }

    override fun signupInfirmSuccess(response: SignupInfirmResponse) {
        if (response.result_code == 100) {
            val sharedPreferences = this.activity?.getSharedPreferences("BTM_APP", 0)

            sharedPreferences?.edit()?.putString("usingPhoneNumber", binding.phoneNumInput.text.toString())
                ?.apply() // 자동 로그인을 위해 SharedPreference 에 전화번호 저장

            startActivity(Intent(binding.root.context, MainActivity::class.java))
            ActivityCompat.finishAffinity(context as SignupActivity)
        }

        showCustomToast(response.message)
        dismissLoadingDialog()
    }

    override fun getRetrofitException() {
        showCustomToast("통신 오류")
        dismissLoadingDialog()
    }

}