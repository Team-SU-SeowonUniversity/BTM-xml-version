package team.su.btmxmlversion.signup.signupmanager.manager.institution

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import team.su.btmxmlversion.R
import team.su.btmxmlversion.config.BaseFragment
import team.su.btmxmlversion.databinding.FragmentSignupInstitutionBinding
import team.su.btmxmlversion.main.MainActivity
import team.su.btmxmlversion.network.CommonDataServiceLocator
import team.su.btmxmlversion.signup.SignupActivity
import team.su.btmxmlversion.signup.SignupRepository
import team.su.btmxmlversion.signup.signupmanager.manager.institution.models.NursingHomeResponse

class InstitutionSignupFragment:
    BaseFragment<FragmentSignupInstitutionBinding>(FragmentSignupInstitutionBinding::bind, R.layout.fragment_signup_institution), InstitutionSignupCallback
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SignupRepository(CommonDataServiceLocator.nursingHomeService, this).tryGetNursingHome()

    }

    override fun onResume() {
        super.onResume()

        binding.checkBtn.setOnClickListener {
            startActivity(Intent(binding.root.context, MainActivity::class.java))
            ActivityCompat.finishAffinity(context as SignupActivity)
        }
    }

    override fun getNursingHomeDataSuccess(response: NursingHomeResponse) {
        Log.d("결과", "getNursingHomeDataSuccess: $response")
    }

    override fun getNursingHomeDataFailure(response: NursingHomeResponse) {
        Log.d("결과", "getNursingHomeDataSuccess: $response")
    }

    override fun getRetrofitException(message: String) {
        Log.d("결과", "getNursingHomeDataSuccess: $message")
    }

}
