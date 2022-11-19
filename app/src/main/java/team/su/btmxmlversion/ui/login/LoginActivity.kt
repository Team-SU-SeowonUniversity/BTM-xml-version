package team.su.btmxmlversion.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import team.su.btmxmlversion.base.BaseActivity
import team.su.btmxmlversion.databinding.ActivityLoginBinding
import team.su.btmxmlversion.dto.LoginInfirmRequestBody
import team.su.btmxmlversion.dto.LoginProtectorBody
import team.su.btmxmlversion.models.LoginInfirmResponse
import team.su.btmxmlversion.models.LoginProtectorResponse
import team.su.btmxmlversion.network.CommonDataServiceLocator
import team.su.btmxmlversion.repository.LoginRepository
import team.su.btmxmlversion.ui.login.adapter.LoginVpAdapter
import team.su.btmxmlversion.ui.login.infirm.InfirmLoginCallback
import team.su.btmxmlversion.ui.login.protector.ProtectorCallback
import team.su.btmxmlversion.ui.main.infirmMain.MainActivity
import team.su.btmxmlversion.ui.signup.SignupActivity
import java.util.regex.Pattern

class LoginActivity :
    BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate),
    InfirmLoginCallback,
    ProtectorCallback
{

    private val viewModel: LoginViewModel by viewModels()
    private var inputPhoneNumber = ""
    private var inputEmail = ""
    private var inputPassWard = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.loginTabVp.adapter = LoginVpAdapter(this)

        TabLayoutMediator(binding.loginTab, binding.loginTabVp) { tab, position ->
            when (position) {
                0 -> tab.text = "노약자"
                1 -> tab.text = "보호자"
            }
        }.attach()
    }

    override fun onResume() {
        super.onResume()

        binding.loginBtn.setOnClickListener {
            val phoneNumberRegex = "^\\d{3}-\\d{3,4}-\\d{4}$"
            val emailRegex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[.][a-zA-Z]{2,3}$"
            val passWordRegex = "^[a-zA-Z0-9!@.#$%^&*?_~]{8,15}$"

            viewModel.phoneNumInput.observe(this) {
                inputPhoneNumber = it
            }
            viewModel.emailInput.observe(this) {
                inputEmail = it
            }
            viewModel.passWardInput.observe(this) {
                inputPassWard = it
            }

            if (inputEmail == "" && inputPassWard == "" && inputPhoneNumber == "") { // 아무것도 입력이 없을 시
                showCustomToast("로그인 정보가 없습니다.")
            } else if (inputEmail == "" && inputPassWard == "") { // 노약자 로그인 준비된 상태
                if (Pattern.matches(phoneNumberRegex, inputPhoneNumber)) {
                    showLoadingDialog(this)
                    LoginRepository(CommonDataServiceLocator.loginService)
                        .tryLoginInfirm(LoginInfirmRequestBody(inputPhoneNumber),this)
                } else {
                    showCustomToast("전화번호를 다시 입력해주세요.")
                }
            } else if (inputPhoneNumber == "") { // 관리자 로그인 준비된 상태
                if (Pattern.matches(emailRegex, inputEmail)) {
                    if (Pattern.matches(passWordRegex, inputPassWard)) {
                        showLoadingDialog(this)
                        LoginRepository(CommonDataServiceLocator.loginService)
                            .tryLoginProtector(LoginProtectorBody(inputEmail,inputPassWard),this)
                    } else {
                        showCustomToast("비밀번호를 다시 입력해주세요.")
                    }
                } else {
                    showCustomToast("이메일을 다시 입력해주세요.")
                }
            }
        }

        binding.signupBtn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    override fun loginInfirmSuccess(response: LoginInfirmResponse) {
        if (response.result_code == 100) {
            val sharedPreferences = getSharedPreferences("BTM_APP", 0)
            sharedPreferences.edit().apply {
                putString("uuid", inputPhoneNumber)
                putBoolean("autoLogin", true)
            }.apply()

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            showCustomToast(response.message)
        }

        dismissLoadingDialog()
    }

    override fun getProtectorLoginSuccess(response: LoginProtectorResponse) {
        if (response.result_code == 200) {
            showCustomToast(response.message)
            dismissLoadingDialog()
        } else {
            showCustomToast(response.message)
            dismissLoadingDialog()

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun getRetrofitException() {
        showCustomToast("통신 오류")
        dismissLoadingDialog()
    }

}