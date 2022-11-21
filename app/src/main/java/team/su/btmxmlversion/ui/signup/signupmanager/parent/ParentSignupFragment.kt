package team.su.btmxmlversion.ui.signup.signupmanager.parent

import android.content.Intent
import androidx.core.app.ActivityCompat
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentSignupParentBinding
import team.su.btmxmlversion.dto.SignupParentBody
import team.su.btmxmlversion.models.SignupParentResponse
import team.su.btmxmlversion.network.CommonDataServiceLocator
import team.su.btmxmlversion.repository.SignupRepository
import team.su.btmxmlversion.ui.main.infirmMain.MainActivity
import team.su.btmxmlversion.ui.main.parentMain.ParentMainActivity
import team.su.btmxmlversion.ui.signup.SignupActivity
import java.util.regex.Pattern

class ParentSignupFragment :
    BaseFragment<FragmentSignupParentBinding>(
        FragmentSignupParentBinding::bind,
        R.layout.fragment_signup_parent
    ),
    ParentSignupCallback
{
    override fun onResume() {
        super.onResume()

        binding.checkBtn.setOnClickListener {
            val email = binding.emailSignupInput.text.toString()
            val passWord = binding.passwordSignupInput.text.toString()
            val name = binding.nameInput.text.toString()
            val emailRegex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[.][a-zA-Z]{2,3}$"
            val passWordRegex = "^[a-zA-Z0-9!@.#$%^&*?_~]{8,15}$"
            val nameRegex = "^[가-힣]*$"

            if (Pattern.matches(emailRegex, email)) {
                if (Pattern.matches(passWordRegex, passWord)) {
                    if (Pattern.matches(nameRegex, name)) {
                        showLoadingDialog(binding.root.context)
                        SignupRepository(CommonDataServiceLocator.signupService)
                            .tryParentSignup(SignupParentBody(email,passWord,name),this)
                    } else {
                        showCustomToast("이름을 다시 입력해주세요.")
                    }
                } else {
                    showCustomToast("비밀번호를 다시 입력해주세요.")
                }
            } else {
                showCustomToast("이메일을 다시 입력해주세요.")
            }
        }
    }

    override fun getParentSignupSuccess(response: SignupParentResponse) {
        val sharedPreferences = activity?.getSharedPreferences("BTM_APP", 0)

        if (response.result_code == 200) {
            showCustomToast(response.message)
            dismissLoadingDialog()
        } else {
            sharedPreferences?.edit()?.apply {
                putBoolean("isInstitution", false)
                putString("email", response.email)
                putString("name", response.name)
            }?.apply()
            showCustomToast(response.message)
            dismissLoadingDialog()

            startActivity(Intent(binding.root.context, ParentMainActivity::class.java))
            ActivityCompat.finishAffinity(context as SignupActivity)
        }
    }

    override fun getRetrofitException() {
        showCustomToast("통신 오류")
        dismissLoadingDialog()
    }

}