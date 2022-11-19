package team.su.btmxmlversion.ui.signup.signupmanager.institution

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentSignupInstitutionBinding
import team.su.btmxmlversion.dto.SignupInstitutionBody
import team.su.btmxmlversion.models.CertifiedResultResponse
import team.su.btmxmlversion.network.CommonDataServiceLocator
import team.su.btmxmlversion.repository.NursingHomeRepository
import team.su.btmxmlversion.models.NursingHomeResponse
import team.su.btmxmlversion.models.SignupInstitutionResponse
import team.su.btmxmlversion.repository.SignupRepository
import team.su.btmxmlversion.ui.login.LoginViewModel
import team.su.btmxmlversion.ui.main.infirmMain.MainActivity
import team.su.btmxmlversion.ui.signup.SignupActivity
import team.su.btmxmlversion.ui.signup.SignupViewModel
import java.util.regex.Pattern

class InstitutionSignupFragment:
    BaseFragment<FragmentSignupInstitutionBinding>(FragmentSignupInstitutionBinding::bind, R.layout.fragment_signup_institution),
    InstitutionSignupCallback
{
    override fun onResume() {
        super.onResume()

        binding.checkBtn.setOnClickListener {
            val email = binding.emailSignupInput.text.toString()
            val passWord = binding.passwordSignupInput.text.toString()
            val facilityName = binding.facilityName.text.toString()
            val representative = binding.representative.text.toString()
            val emailRegex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[.][a-zA-Z]{2,3}$"
            val passWordRegex = "^[a-zA-Z0-9!@.#$%^&*?_~]{8,15}$"

            if(!binding.certifiedBtn.isEnabled) {
                if (Pattern.matches(emailRegex, email)){
                    if (Pattern.matches(passWordRegex, passWord)) {
                        showLoadingDialog(binding.root.context)
                        SignupRepository(CommonDataServiceLocator.signupService)
                            .tryInstitutionSignup(SignupInstitutionBody(email,passWord,facilityName,representative),this)
                    } else {
                        showCustomToast("비밀번호를 다시 입력해주세요.")
                    }
                } else {
                    showCustomToast("이메일을 다시 입력해주세요.")
                }
            } else {
                showCustomToast("인증을 완료해주세요.")
            }
        }

        binding.certifiedBtn.setOnClickListener {
            NursingHomeRepository(CommonDataServiceLocator.nursingHomeService).tryGetNursingHome(this)
        }

    }

    override fun getNursingHomeDataSuccess(response: NursingHomeResponse) {
        showLoadingDialog(binding.root.context)

        val data = response.data
        val facilityName = binding.facilityName.text.toString()
        val representative = binding.representative.text.toString()
        val correctInput = data.filter { it.representative == representative && it.facilityName == facilityName }

        when(correctInput.isEmpty()) {
            true -> {
                showCustomToast("시설명 혹은 대표자명을 확인해주세요.")
                dismissLoadingDialog()
            }
            false -> {
                SignupRepository(CommonDataServiceLocator.signupService)
                    .tryCertifiedInstitution(facilityName, representative, this)
            }
        }
    }

    override fun getInstitution(response: CertifiedResultResponse) {
        if (response.result_code == 200) {
            showCustomToast(response.message)
        } else {
            showCustomToast(response.message)
            binding.certifiedBtn.isEnabled = false
            binding.facilityName.isEnabled = false
            binding.representative.isEnabled = false
        }
        dismissLoadingDialog()
    }

    override fun getInstitutionSignupSuccess(response: SignupInstitutionResponse) {
        if (response.result_code == 200) {
            showCustomToast(response.message)
            dismissLoadingDialog()
        } else {
            showCustomToast(response.message)
            dismissLoadingDialog()

            startActivity(Intent(binding.root.context, MainActivity::class.java))
            ActivityCompat.finishAffinity(context as SignupActivity)
        }
    }

    override fun getRetrofitException() {
        showCustomToast("통신 오류")
        dismissLoadingDialog()
    }

}
