package team.su.btmxmlversion.ui.signup.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import team.su.btmxmlversion.ui.signup.signupinfirm.SignupInfirmFragment
import team.su.btmxmlversion.ui.signup.signupmanager.SignupManagerFragment

class SignupVpAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SignupInfirmFragment()
            else -> SignupManagerFragment()
        }
    }

}