package team.su.btmxmlversion.ui.signup.signupmanager.parent

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentSignupParentBinding
import team.su.btmxmlversion.ui.main.infirmMain.MainActivity
import team.su.btmxmlversion.ui.signup.SignupActivity

class ParentSignupFragment:
    BaseFragment<FragmentSignupParentBinding>(FragmentSignupParentBinding::bind, R.layout.fragment_signup_parent) {

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