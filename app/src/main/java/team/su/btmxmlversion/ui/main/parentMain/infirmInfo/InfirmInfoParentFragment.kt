package team.su.btmxmlversion.ui.main.parentMain.infirmInfo

import android.os.Bundle
import android.view.View
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentInfirmInfoParentBinding

class InfirmInfoParentFragment:
    BaseFragment<FragmentInfirmInfoParentBinding>(FragmentInfirmInfoParentBinding::bind, R.layout.fragment_infirm_info_parent)
{
    private var email: String = ""
    private var protectorName: String = ""
    private var facilityName: String? = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = activity?.getSharedPreferences("BTM_APP", 0)
        email = sharedPreferences?.getString("email","").toString()
        protectorName = sharedPreferences?.getString("name","").toString()
        facilityName = sharedPreferences?.getString("affiliation","").toString()

        binding.managerName.text = protectorName
        binding.managerEmailAddress.text = email
        binding.interlockOutButton.visibility = View.INVISIBLE
        binding.infirmImage.visibility = View.INVISIBLE
        binding.infirmName.visibility = View.INVISIBLE
        binding.infirmPhoneNumber.visibility = View.INVISIBLE
        binding.healthIconsLayout.visibility = View.INVISIBLE

    }

    override fun onResume() {
        super.onResume()

        binding.interlockButton.setOnClickListener {
            showAddInfirmDialog(binding.root.context, email, protectorName, facilityName)
        }
    }

}