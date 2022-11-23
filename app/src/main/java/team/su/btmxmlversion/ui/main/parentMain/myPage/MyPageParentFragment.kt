package team.su.btmxmlversion.ui.main.parentMain.myPage

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentMyPageProtectorBinding
import team.su.btmxmlversion.ui.login.LoginActivity
import team.su.btmxmlversion.until.ChangeDialog
import team.su.btmxmlversion.until.RefreshFragment

class MyPageParentFragment:
    BaseFragment<FragmentMyPageProtectorBinding>(FragmentMyPageProtectorBinding::bind, R.layout.fragment_my_page_protector)
{
    private var sharedPreferences: SharedPreferences? = null
    private var email: String? = null
    private var name: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = activity?.getSharedPreferences("BTM_APP",0)
        email = sharedPreferences?.getString("email", "")
        name = sharedPreferences?.getString("name", "")

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

        binding.emailChangeText.setOnClickListener {
            ChangeDialog(
                context = binding.root.context,
                email = email.toString(),
                isChangeEmail = true,
                onRefresh = { RefreshFragment.refreshFragment(this,parentFragmentManager) },
                sharedPreferences = sharedPreferences
            ).show()
        }

        binding.emailChangeButton.setOnClickListener {
            ChangeDialog(
                context = binding.root.context,
                email = email.toString(),
                isChangeEmail = true,
                onRefresh = { RefreshFragment.refreshFragment(this,parentFragmentManager) },
                sharedPreferences = sharedPreferences
            ).show()
        }

        binding.nameChangeText.setOnClickListener {
            ChangeDialog(
                context = binding.root.context,
                email = email.toString(),
                isChangeEmail = false,
                onRefresh = { RefreshFragment.refreshFragment(this,parentFragmentManager) },
                sharedPreferences = sharedPreferences
            ).show()
        }

        binding.nameChangeButton.setOnClickListener {
            ChangeDialog(
                context = binding.root.context,
                email = email.toString(),
                isChangeEmail = false,
                onRefresh = { RefreshFragment.refreshFragment(this,parentFragmentManager) },
                sharedPreferences = sharedPreferences
            ).show()
        }
    }
}