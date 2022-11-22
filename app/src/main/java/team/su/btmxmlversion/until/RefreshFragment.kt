package team.su.btmxmlversion.until

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

object RefreshFragment {
    @SuppressLint("DetachAndAttachSameFragment")
    fun refreshFragment(fragment: Fragment, fragmentManager: FragmentManager) {
        val ft: FragmentTransaction = fragmentManager.beginTransaction()
        ft.detach(fragment).commitNow()
        ft.attach(fragment).commitNow()
    }
}