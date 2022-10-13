package team.su.btmxmlversion.signup.signupmanager.manager

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import team.su.btmxmlversion.R
import team.su.btmxmlversion.config.BaseFragment
import team.su.btmxmlversion.databinding.FragmentSignupInstitutionBinding
import team.su.btmxmlversion.main.MainActivity
import team.su.btmxmlversion.signup.SignupActivity

class InstitutionSignupFragment:
    BaseFragment<FragmentSignupInstitutionBinding>(FragmentSignupInstitutionBinding::bind, R.layout.fragment_signup_institution) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        binding.checkBtn.setOnClickListener {
            startActivity(Intent(binding.root.context, MainActivity::class.java))
            ActivityCompat.finishAffinity(context as SignupActivity)
        }
    }

}