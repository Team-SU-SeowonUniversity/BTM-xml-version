package team.su.btmxmlversion.ui.main.infirmMain.myPage

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.coroutines.launch
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentMyPageBinding
import team.su.btmxmlversion.models.InfirmUserInfoResponse
import team.su.btmxmlversion.network.CommonDataServiceLocator
import team.su.btmxmlversion.repository.InfirmUserRepository

class MyPageFragment:
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page)
{
    private lateinit var barList: ArrayList<BarEntry>
    private lateinit var barDataSet: BarDataSet
    private lateinit var barData: BarData
    private lateinit var scoreOfAreaType: ArrayList<Float>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = this.activity?.getSharedPreferences("BTM_APP",0)
        val uuid = sharedPreferences?.getString("uuid", "").toString()

        lifecycleScope.launch {
            val result = InfirmUserRepository(CommonDataServiceLocator.infirmUserInfoService).tryGetInfirmUserInfo(uuid)
            getInfirmUserInfoSuccess(result)
            setHealthState()
            setBarChart()
            binding.barChart.invalidate()
        }
    }

    override fun onResume() {
        super.onResume()

        binding.logoutButton.setOnClickListener { // 로그아웃 버튼
            val sharedPreferences = this.activity?.getSharedPreferences("BTM_APP", 0)

            sharedPreferences?.edit()?.apply {
                putString("uuid", null)
                putBoolean("autoLogin", false)
            }?.apply()

            startActivity(Intent(binding.root.context, team.su.btmxmlversion.ui.login.LoginActivity::class.java))
            activity?.finish()
        }
    }

    private fun setHealthState() {
        val avg = this.scoreOfAreaType.let {
            var total = 0f
            for (i in it.indices) {
                total += it[i]
            }
            total / 5
        }

        val disableColor = ContextCompat.getColor(binding.root.context, R.color.health_state_text_disable_color)

        when(avg) {
            in 0f..20f -> {
                binding.veryGoodImage.setImageResource(R.drawable.very_good_icon_disable)
                binding.goodImage.setImageResource(R.drawable.good_icon_disable)
                binding.normalImage.setImageResource(R.drawable.normal_icon_disable)
                binding.badImage.setImageResource(R.drawable.bad_icon_disable)
                binding.veryBadImage.setImageResource(R.drawable.very_bad_icon)
                binding.veryGoodText.setTextColor(disableColor)
                binding.goodText.setTextColor(disableColor)
                binding.normalText.setTextColor(disableColor)
                binding.badText.setTextColor(disableColor)
                binding.veryBadText.setTextColor(Color.BLACK)
            }
            in 20.1f..40f -> {
                binding.veryGoodImage.setImageResource(R.drawable.very_good_icon_disable)
                binding.goodImage.setImageResource(R.drawable.good_icon_disable)
                binding.normalImage.setImageResource(R.drawable.normal_icon_disable)
                binding.badImage.setImageResource(R.drawable.bad_icon)
                binding.veryBadImage.setImageResource(R.drawable.very_bad_icon_disable)
                binding.veryGoodText.setTextColor(disableColor)
                binding.goodText.setTextColor(disableColor)
                binding.normalText.setTextColor(disableColor)
                binding.badText.setTextColor(Color.BLACK)
                binding.veryBadText.setTextColor(disableColor)
            }
            in 40.1f..60f -> {
                binding.veryGoodImage.setImageResource(R.drawable.very_good_icon_disable)
                binding.goodImage.setImageResource(R.drawable.good_icon_disable)
                binding.normalImage.setImageResource(R.drawable.normal_icon)
                binding.badImage.setImageResource(R.drawable.bad_icon_disable)
                binding.veryBadImage.setImageResource(R.drawable.very_bad_icon_disable)
                binding.veryGoodText.setTextColor(disableColor)
                binding.goodText.setTextColor(disableColor)
                binding.normalText.setTextColor(Color.BLACK)
                binding.badText.setTextColor(disableColor)
                binding.veryBadText.setTextColor(disableColor)
            }
            in 60.1f..80f -> {
                binding.veryGoodImage.setImageResource(R.drawable.very_good_icon_disable)
                binding.goodImage.setImageResource(R.drawable.good_icon)
                binding.normalImage.setImageResource(R.drawable.normal_icon_disable)
                binding.badImage.setImageResource(R.drawable.bad_icon_disable)
                binding.veryBadImage.setImageResource(R.drawable.very_bad_icon_disable)
                binding.veryGoodText.setTextColor(disableColor)
                binding.goodText.setTextColor(Color.BLACK)
                binding.normalText.setTextColor(disableColor)
                binding.badText.setTextColor(disableColor)
                binding.veryBadText.setTextColor(disableColor)
            }
            in 80.1f..100f -> {
                binding.veryGoodImage.setImageResource(R.drawable.very_good_icon)
                binding.goodImage.setImageResource(R.drawable.good_icon_disable)
                binding.normalImage.setImageResource(R.drawable.normal_icon_disable)
                binding.badImage.setImageResource(R.drawable.bad_icon_disable)
                binding.veryBadImage.setImageResource(R.drawable.very_bad_icon_disable)
                binding.veryGoodText.setTextColor(Color.BLACK)
                binding.goodText.setTextColor(disableColor)
                binding.normalText.setTextColor(disableColor)
                binding.badText.setTextColor(disableColor)
                binding.veryBadText.setTextColor(disableColor)
            }
            else -> {
                binding.veryGoodImage.setImageResource(R.drawable.very_good_icon_disable)
                binding.goodImage.setImageResource(R.drawable.good_icon_disable)
                binding.normalImage.setImageResource(R.drawable.normal_icon_disable)
                binding.badImage.setImageResource(R.drawable.bad_icon_disable)
                binding.veryBadImage.setImageResource(R.drawable.very_bad_icon_disable)
                binding.veryGoodText.setTextColor(disableColor)
                binding.goodText.setTextColor(disableColor)
                binding.normalText.setTextColor(disableColor)
                binding.badText.setTextColor(disableColor)
                binding.veryBadText.setTextColor(disableColor)
            }
        }
    }

    private fun setBarChart() {
        val labelList = listOf("분석", "계산", "직감", "기억", "지각")

        barList = ArrayList()
        barList.add(BarEntry(0f,scoreOfAreaType[4]))
        barList.add(BarEntry(1f,scoreOfAreaType[3]))
        barList.add(BarEntry(2f,scoreOfAreaType[2]))
        barList.add(BarEntry(3f,scoreOfAreaType[1]))
        barList.add(BarEntry(4f,scoreOfAreaType[0]))

        barDataSet = BarDataSet(barList,null)
        barDataSet.apply {
            color = Color.GREEN
            valueTextColor = Color.BLACK
            valueTextSize = 15f
        }


        barData = BarData(barDataSet)
        barData.barWidth = 0.5f

        binding.barChart.data = barData
        binding.barChart.description.isEnabled = false
        binding.barChart.legend.isEnabled = false
        binding.barChart.setTouchEnabled(false)

        val xAxis = binding.barChart.xAxis
        xAxis.setDrawAxisLine(false)
        xAxis.granularity = 1f
        xAxis.textSize = 15f
        xAxis.gridLineWidth = 25f
        xAxis.gridColor = Color.parseColor("#80E5E5E5")
        xAxis.position = XAxis.XAxisPosition.BOTTOM // X 축 데이터 표시 위치
        xAxis.valueFormatter = IndexAxisValueFormatter(labelList)

        val axisLeft = binding.barChart.axisLeft
        axisLeft.setDrawGridLines(false)
        axisLeft.setDrawAxisLine(false)
        axisLeft.axisMinimum = 0f // 최솟값
        axisLeft.axisMaximum = 100f // 최댓값
        axisLeft.granularity = 1f // 최대값만큼 라인선 설정
        axisLeft.textSize = 14f

        val axisRight = binding.barChart.axisRight
        axisRight.setDrawLabels(false) // label 삭제
        axisRight.setDrawGridLines(false)
        axisRight.setDrawAxisLine(false)

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

        dismissLoadingDialog()
    }

}