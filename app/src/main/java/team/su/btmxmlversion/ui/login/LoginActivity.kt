package team.su.btmxmlversion.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import team.su.btmxmlversion.base.BaseActivity
import team.su.btmxmlversion.databinding.ActivityLoginBinding
import team.su.btmxmlversion.dto.LoginInfirmRequestBody
import team.su.btmxmlversion.models.LoginInfirmResponse
import team.su.btmxmlversion.network.CommonDataServiceLocator
import team.su.btmxmlversion.repository.LoginRepository
import team.su.btmxmlversion.ui.login.adapter.LoginVpAdapter
import team.su.btmxmlversion.ui.login.infirm.InfirmLoginCallback
import team.su.btmxmlversion.ui.login.service.LoginService
import team.su.btmxmlversion.ui.main.infirmMain.MainActivity
import team.su.btmxmlversion.ui.signup.SignupActivity
import java.util.regex.Pattern

class LoginActivity: BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate), InfirmLoginCallback {

    private val viewModel: LoginViewModel by viewModels()
    private var inputPhoneNumber = ""

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

            viewModel.phoneNumInput.observe(this) {
                inputPhoneNumber = it
            }


            if (Pattern.matches("^\\d{3}-\\d{3,4}-\\d{4}$", inputPhoneNumber)) {
                LoginRepository(CommonDataServiceLocator.loginService)
                    .tryLoginInfirm(LoginInfirmRequestBody(inputPhoneNumber),this)
            } else {
                showCustomToast("전화번호를 다시 확인해주세요.")
            }

        }

        binding.signupBtn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    override fun loginInfirmSuccess(response: LoginInfirmResponse) {
        showLoadingDialog(this)

        if (response.result_code == 100) {
            val sharedPreferences = getSharedPreferences("BTM_APP", 0)
            sharedPreferences.edit().apply {
                putString("usingPhoneNumber", inputPhoneNumber)
                putString("usingInfirmName", response.name)
            }.apply()

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            showCustomToast(response.message)
        }

        dismissLoadingDialog()
    }

    override fun getRetrofitException() {
        showCustomToast("통신 오류")
    }

}