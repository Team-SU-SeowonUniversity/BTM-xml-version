

package team.su.btmxmlversion.login.infirm

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import team.su.btmxmlversion.R
import team.su.btmxmlversion.config.BaseFragment
import team.su.btmxmlversion.databinding.FragmentInfirmBinding

class InfirmFragment: BaseFragment<FragmentInfirmBinding>(FragmentInfirmBinding::bind, R.layout.fragment_infirm) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.phoneNumInput.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }

    override fun onPause() {
        super.onPause()

        binding.phoneNumInput.text = null
    }

}