package team.su.btmxmlversion.ui.main.infirmMain.myPage

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentMyPageBinding
import team.su.btmxmlversion.models.InfirmUserInfoResponse
import team.su.btmxmlversion.network.CommonDataServiceLocator
import team.su.btmxmlversion.repository.InfirmUserRepository
import team.su.btmxmlversion.until.HorizontalBarChart

class MyPageFragment:
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page)
{
    private lateinit var scoreOfAreaType: ArrayList<Float>
    private var sharedPreferences: SharedPreferences? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            sharedPreferences = this@MyPageFragment.activity?.getSharedPreferences("BTM_APP",0)
            val uuid = sharedPreferences?.getString("uuid", "").toString()
            val result = InfirmUserRepository(CommonDataServiceLocator.infirmUserInfoService)
                .tryGetInfirmUserInfo(uuid)
            getInfirmUserInfoSuccess(result)
        }
    }

    override fun onResume() {
        super.onResume()

        binding.logoutButton.setOnClickListener { // 로그아웃 버튼
            sharedPreferences?.edit()?.apply {
                putString("uuid", null)
                putBoolean("autoLogin", false)
            }?.apply()

            startActivity(Intent(binding.root.context, team.su.btmxmlversion.ui.login.LoginActivity::class.java))
            activity?.finish()
        }
    }

    private fun getInfirmUserInfoSuccess(response: InfirmUserInfoResponse) {
        showLoadingDialog(binding.root.context)

        binding.infirmName.text = response.name
        binding.infirmPhoneNumber.text = response.phoneNumber

        if (response.managerEmail == null) {
            binding.managerName.text = "연결되지 않음"
            binding.managerEmailAddress.visibility = View.INVISIBLE
        } else if(response.facilityName == "" || response.facilityName == null){
            binding.managerName.text = response.managerName
            binding.managerEmailAddress.text = response.managerEmail
            binding.managerImage.setImageResource(R.drawable.default_profile_image)
        } else {
            binding.managerName.text = response.facilityName
            binding.managerEmailAddress.text = response.managerEmail
            binding.managerImage.setImageResource(R.drawable.default_profile_image)
        }

        val data = response.typeScore

        scoreOfAreaType = ArrayList()
        scoreOfAreaType.add(data.perceptionScore)
        scoreOfAreaType.add(data.memoryScore)
        scoreOfAreaType.add(data.intuitionScore)
        scoreOfAreaType.add(data.calculationScore)
        scoreOfAreaType.add(data.analysisScore)

        val setBarChart = HorizontalBarChart(scoreOfAreaType, binding.barChart)
        setBarChart.setBarChart()
        setBarChart.setHealthState(
            context = binding.root.context,
            veryGoodImage = binding.veryGoodImage,
            goodImage = binding.goodImage,
            normalImage = binding.normalImage,
            badImage = binding.badImage,
            veryBadImage = binding.veryBadImage,
            veryGoodText = binding.veryGoodText,
            goodText = binding.goodText,
            normalText = binding.normalText,
            badText = binding.badText,
            veryBadText = binding.veryBadText
        )

        dismissLoadingDialog()
    }

}