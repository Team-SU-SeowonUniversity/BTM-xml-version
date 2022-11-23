package team.su.btmxmlversion.ui.main.parentMain.infirmInfo

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentInfirmInfoParentBinding
import team.su.btmxmlversion.dto.InterlockTerminationRequestBody
import team.su.btmxmlversion.models.BaseResponse
import team.su.btmxmlversion.models.InterlockInfoResponse
import team.su.btmxmlversion.network.CommonDataServiceLocator
import team.su.btmxmlversion.repository.InterlockRepository
import team.su.btmxmlversion.until.HorizontalBarChart
import team.su.btmxmlversion.until.RefreshFragment.refreshFragment

class InfirmInfoParentFragment :
    BaseFragment<FragmentInfirmInfoParentBinding>(
        FragmentInfirmInfoParentBinding::bind,
        R.layout.fragment_infirm_info_parent
    ),
    InfirmInfoParentCallback
{
    private var email: String = ""
    private var protectorName: String = ""
    private var facilityName: String? = ""
    private var infirmPhoneNumber: String = ""
    private var infirmName: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = activity?.getSharedPreferences("BTM_APP", 0)
        email = sharedPreferences?.getString("email", "").toString()
        protectorName = sharedPreferences?.getString("name", "").toString()
        facilityName = sharedPreferences?.getString("affiliation", "").toString()

        binding.managerName.text = protectorName
        binding.managerEmailAddress.text = email

        lifecycleScope.launch {
            val response = InterlockRepository(CommonDataServiceLocator.infirmInfoParentService)
                .tryGetInterlockInfo(email)
            getInterlockInfoSuccess(response)
        }
    }

    override fun onResume() {
        super.onResume()

        binding.interlockButton.setOnClickListener { // 연동 하기
            showAddInfirmDialog(
                context = binding.root.context,
                email = email,
                protectorName = protectorName,
                facilityName = facilityName,
                onRefresh = { refreshFragment(this, parentFragmentManager) }
            )
        }

        binding.interlockOutButton.setOnClickListener { // 연동 해지
            InterlockRepository(CommonDataServiceLocator.infirmInfoParentService)
                .tryInterlockTermination(InterlockTerminationRequestBody(infirmPhoneNumber),this)
        }
    }

    private fun getInterlockInfoSuccess(response: InterlockInfoResponse) {
        if (response.result_code == 200) {
            binding.interlockOutButton.visibility = View.INVISIBLE
            binding.infirmImage.visibility = View.INVISIBLE
            binding.infirmName.visibility = View.INVISIBLE
            binding.infirmPhoneNumber.visibility = View.INVISIBLE
            binding.interlockButton.visibility = View.VISIBLE
            showCustomToast(response.message)
        } else {
            val interlockInfo = response.infirmInfo
            val scoreList = arrayListOf<Float>()
            scoreList.add(interlockInfo[0].perceptionScore)
            scoreList.add(interlockInfo[0].memoryScore)
            scoreList.add(interlockInfo[0].intuitionScore)
            scoreList.add(interlockInfo[0].calculationScore)
            scoreList.add(interlockInfo[0].analysisScore)
            val setChart = HorizontalBarChart(scoreList, binding.barChart)
            setChart.setBarChart()
            setChart.setHealthState(
                veryGoodImage = binding.veryGoodImage,
                goodImage = binding.goodImage,
                normalImage = binding.normalImage,
                badImage = binding.badImage,
                veryBadImage = binding.veryBadImage
            )
            infirmPhoneNumber = interlockInfo[0].phoneNumber
            infirmName = interlockInfo[0].infirmName

            binding.interlockOutButton.visibility = View.VISIBLE
            binding.infirmImage.visibility = View.VISIBLE
            binding.infirmName.visibility = View.VISIBLE
            binding.infirmPhoneNumber.visibility = View.VISIBLE
            binding.interlockButton.visibility = View.INVISIBLE

            binding.infirmName.text = infirmName
            binding.infirmPhoneNumber.text = infirmPhoneNumber
        }
    }

    override fun getInterlockTerminationSuccess(response: BaseResponse) {
        if (response.result_code == 100) {
            showCustomToast(response.message) // 해지 완료
            refreshFragment(this, parentFragmentManager)
        } else {
            showCustomToast(response.message) // 해지 실패
        }
    }

    override fun getRetrofitException() {
        showCustomToast("통신 오류")
    }

}