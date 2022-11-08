package team.su.btmxmlversion.login.manager

import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentManagerBinding

class ManagerFragment: BaseFragment<FragmentManagerBinding>(FragmentManagerBinding::bind, R.layout.fragment_manager) {


    override fun onPause() {
        super.onPause()

        binding.emailInput.text = null
        binding.passwordInput.text = null
    }

}