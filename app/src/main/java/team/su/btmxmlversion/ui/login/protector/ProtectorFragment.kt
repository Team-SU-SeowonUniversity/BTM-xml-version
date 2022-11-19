package team.su.btmxmlversion.ui.login.protector

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentLoginProtectorBinding
import team.su.btmxmlversion.ui.login.LoginViewModel

class ProtectorFragment: BaseFragment<FragmentLoginProtectorBinding>(FragmentLoginProtectorBinding::bind, R.layout.fragment_login_protector) {

    private val viewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emailInput.addTextChangedListener { viewModel.setProtectorEmail(it.toString()) }
        binding.passwordInput.addTextChangedListener { viewModel.setProtectorPassWard(it.toString()) }

    }

    override fun onPause() {
        super.onPause()

        binding.emailInput.text = null
        binding.passwordInput.text = null
    }

}