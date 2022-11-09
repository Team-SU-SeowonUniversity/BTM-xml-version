package team.su.btmxmlversion.login.infirm

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.view.View
import androidx.fragment.app.activityViewModels
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentInfirmBinding
import team.su.btmxmlversion.login.LoginViewModel

class InfirmFragment: BaseFragment<FragmentInfirmBinding>(FragmentInfirmBinding::bind, R.layout.fragment_infirm) {

    private val viewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.phoneNumInput.addTextChangedListener(TextWatcher())
    }

    override fun onPause() {
        super.onPause()

        binding.phoneNumInput.text = null
    }

    inner class TextWatcher : PhoneNumberFormattingTextWatcher(){
        override fun afterTextChanged(s: Editable?) {
            super.afterTextChanged(s)

            viewModel.setData(s.toString())
        }
    }
}