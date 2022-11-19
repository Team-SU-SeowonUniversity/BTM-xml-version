package team.su.btmxmlversion.ui.login.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import team.su.btmxmlversion.ui.login.infirm.InfirmLonginFragment
import team.su.btmxmlversion.ui.login.protector.ProtectorFragment

class LoginVpAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> InfirmLonginFragment()
            else -> ProtectorFragment()
        }
    }

}