package team.su.btmxmlversion.ui.signup.signupinfirm

import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import androidx.core.app.ActivityCompat
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentSignupInfirmBinding
import team.su.btmxmlversion.network.CommonDataServiceLocator
import team.su.btmxmlversion.dto.SignupInfirmRequestBody
import team.su.btmxmlversion.models.SignupInfirmResponse
import team.su.btmxmlversion.repository.SignupRepository
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
                    SignupRepository(CommonDataServiceLocator.signupService)
                        .tryInfirmSignup(SignupInfirmRequestBody(phoneNumber, name), this)
                } else {
                    showCustomToast("이름을 한글만 입력해주세요.")
                }
            } else {
                showCustomToast("전화번호 다시 입력 해주세요.")
            }
        }
    }

    override fun signupInfirmSuccess(response: SignupInfirmResponse) {
        showLoadingDialog(binding.root.context)

        if (response.result_code == 100) {
            val sharedPreferences = this.activity?.getSharedPreferences("BTM_APP", 0)

            sharedPreferences?.edit()?.apply {
                putString("uuid", binding.phoneNumInput.text.toString())
                putBoolean("autoLogin", true)
            }?.apply()

            startActivity(Intent(binding.root.context, MainActivity::class.java))
            ActivityCompat.finishAffinity(context as SignupActivity)
        }
        showCustomToast(response.message)

        dismissLoadingDialog()
    }

    override fun getRetrofitException() {
        showCustomToast("통신 오류")
    }

}