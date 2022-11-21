package team.su.btmxmlversion.ui.main.parentMain.myPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentMyPageProtectorBinding
import team.su.btmxmlversion.ui.login.LoginActivity

class MyPageParentFragment:
    BaseFragment<FragmentMyPageProtectorBinding>(FragmentMyPageProtectorBinding::bind, R.layout.fragment_my_page_protector)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = activity?.getSharedPreferences("BTM_APP",0)
        val email = sharedPreferences?.getString("email", "")
        val name = sharedPreferences?.getString("name", "")

        binding.nameText.text = StringBuilder().append("이름 : ").append(name)
        binding.emailText.text = StringBuilder().append("Email : ").append(email)
        binding.affiliationText.text = StringBuilder().append("소속 : 부모")
    }

    override fun onResume() {
        super.onResume()

        binding.logoutButton.setOnClickListener {
            activity?.getSharedPreferences("BTM_APP",0)?.edit()?.clear()?.apply()
            startActivity(Intent(binding.root.context, LoginActivity::class.java))
            activity?.finish()
        }
    }
}