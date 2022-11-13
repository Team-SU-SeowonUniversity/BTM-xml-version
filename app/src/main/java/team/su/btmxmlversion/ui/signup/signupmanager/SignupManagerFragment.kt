package team.su.btmxmlversion.ui.signup.signupmanager

import android.os.Bundle
import android.view.View
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentSignupManagerBinding
import team.su.btmxmlversion.ui.signup.signupmanager.institution.InstitutionSignupFragment
import team.su.btmxmlversion.ui.signup.signupmanager.parent.ParentSignupFragment

class SignupManagerFragment:
    BaseFragment<FragmentSignupManagerBinding>(FragmentSignupManagerBinding::bind, R.layout.fragment_signup_manager){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.parent_radio ->
                    childFragmentManager.beginTransaction().replace(R.id.radio_fragment, ParentSignupFragment()).commit()
                R.id.institution_radio ->
                    childFragmentManager.beginTransaction().replace(R.id.radio_fragment, InstitutionSignupFragment()).commit()
            }
        }
    }

}