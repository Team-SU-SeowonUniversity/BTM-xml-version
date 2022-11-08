package team.su.btmxmlversion.signup.signupinfirm

import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import androidx.core.app.ActivityCompat
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentSignupInfirmBinding
import team.su.btmxmlversion.main.infirmMain.MainActivity
import team.su.btmxmlversion.signup.SignupActivity

class SignupInfirmFragment:
    BaseFragment<FragmentSignupInfirmBinding>(FragmentSignupInfirmBinding::bind, R.layout.fragment_signup_infirm) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.phoneNumInput.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }

    override fun onResume() {
        super.onResume()

        binding.checkBtn.setOnClickListener {
            startActivity(Intent(binding.root.context, MainActivity::class.java))
            ActivityCompat.finishAffinity(context as SignupActivity)
        }
    }

}