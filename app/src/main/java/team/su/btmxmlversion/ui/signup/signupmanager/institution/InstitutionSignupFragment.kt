package team.su.btmxmlversion.ui.signup.signupmanager.institution

import android.content.Intent
import androidx.core.app.ActivityCompat
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
import team.su.btmxmlversion.ui.main.infirmMain.MainActivity
import team.su.btmxmlversion.ui.signup.SignupActivity
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
            val name = binding.nameSignupInput.text.toString()
            val facilityName = binding.facilityName.text.toString()
            val representative = binding.representative.text.toString()
            val emailRegex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[.][a-zA-Z]{2,3}$"
            val passWordRegex = "^[a-zA-Z0-9!@.#$%^&*?_~]{8,15}$"

            if(!binding.certifiedBtn.isEnabled) {
                if (Pattern.matches(emailRegex, email)){
                    if (Pattern.matches(passWordRegex, passWord)) {
                        showLoadingDialog(binding.root.context)
                        SignupRepository(CommonDataServiceLocator.signupService)
                            .tryInstitutionSignup(SignupInstitutionBody(email,passWord,name,facilityName,representative),this)
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
            showLoadingDialog(binding.root.context)
            NursingHomeRepository(CommonDataServiceLocator.nursingHomeService).tryGetNursingHome(this)
        }

    }

    override fun getNursingHomeDataSuccess(response: NursingHomeResponse) {
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

    override fun getCertifiedResult(response: CertifiedResultResponse) {
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
        val sharedPreferences = activity?.getSharedPreferences("BTM_APP", 0)

        if (response.result_code == 200) {
            showCustomToast(response.message)
            dismissLoadingDialog()
        } else {
            sharedPreferences?.edit()?.apply {
                putBoolean("isInstitution", true)
                putString("email", response.email)
                putString("name", response.name)
                putString("affiliation", response.facilityName)
            }?.apply()
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
