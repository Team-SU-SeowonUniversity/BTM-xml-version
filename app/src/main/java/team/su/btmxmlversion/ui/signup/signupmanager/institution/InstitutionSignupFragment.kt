package team.su.btmxmlversion.ui.signup.signupmanager.institution

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentSignupInstitutionBinding
import team.su.btmxmlversion.network.CommonDataServiceLocator
import team.su.btmxmlversion.repository.NursingHomeRepository
import team.su.btmxmlversion.models.NursingHomeResponse
import team.su.btmxmlversion.ui.main.infirmMain.MainActivity
import team.su.btmxmlversion.ui.signup.SignupActivity

class InstitutionSignupFragment:
    BaseFragment<FragmentSignupInstitutionBinding>(FragmentSignupInstitutionBinding::bind, R.layout.fragment_signup_institution),
    InstitutionSignupCallback
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        binding.checkBtn.setOnClickListener {
            if(!binding.certifiedBtn.isEnabled) {
                startActivity(Intent(binding.root.context, MainActivity::class.java))
                ActivityCompat.finishAffinity(context as SignupActivity)
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
        dismissLoadingDialog()

        val data = response.data
        val facilityName = binding.facilityName.text.toString()
        val representative = binding.representative.text.toString()

        val correctInput = data.filter { it.representative == representative && it.facilityName == facilityName }

        when(correctInput.isEmpty()) {
            true -> { showCustomToast("시설명 혹은 대표자명을 확인해주세요.") }
            false -> {
                showCustomToast("인증되었습니다.")
                binding.certifiedBtn.isEnabled = false
                binding.facilityName.isEnabled = false
                binding.representative.isEnabled = false
            }
        }

    }

    override fun getNursingHomeDataFailure() {
        showCustomToast("연동 실패")
        dismissLoadingDialog()
    }

    override fun getRetrofitException() {
        showCustomToast("통신 오류")
        dismissLoadingDialog()
    }

}
