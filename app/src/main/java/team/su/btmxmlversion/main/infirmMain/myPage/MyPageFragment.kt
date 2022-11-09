package team.su.btmxmlversion.main.infirmMain.myPage

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentMyPageBinding
import team.su.btmxmlversion.login.LoginActivity
import team.su.btmxmlversion.main.infirmMain.dementiaDiagnosis.data.DiagnosisStatement

class MyPageFragment: BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {

    companion object {
        private lateinit var barList: ArrayList<BarEntry>
        private lateinit var barDataSet: BarDataSet
        private lateinit var barData: BarData
    }

    private lateinit var scoreOfAreaType: ArrayList<Float>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val score = this.activity?.getSharedPreferences("SCORE", 0)
        val intuitionScore = score?.getFloat("INTUITION_SCORE", 0f)
        val analysisScore = score?.getFloat("ANALYSIS_SCORE", 0f)
        val calculationScore = score?.getFloat("CALCULATION_SCORE", 0f)
        val memoryScore = score?.getFloat("MEMORY_SCORE", 0f)
        val perceptionScore = score?.getFloat("PERCEPTION_SCORE", 0f)

        val userInfo = this.activity?.getSharedPreferences("USER_INFO", 0)
        val infirmName = userInfo?.getString("USING_USER_NAME", "")
        val infirmPhoneNumber = userInfo?.getString("USING_USER_PHONE_NUMBER", "")

        Log.d("뭐지?", "$infirmName, $infirmPhoneNumber ")

        binding.infirmName.text = infirmName!!
        binding.infirmPhoneNumber.text = infirmPhoneNumber!!

        scoreOfAreaType = ArrayList()
        scoreOfAreaType.add(perceptionScore!!.toFloat())
        scoreOfAreaType.add(memoryScore!!.toFloat())
        scoreOfAreaType.add(intuitionScore!!.toFloat())
        scoreOfAreaType.add(calculationScore!!.toFloat())
        scoreOfAreaType.add(analysisScore!!.toFloat())

        setHealthState()
        setBarChart()

        // 관지라 연동 추후 반영 예정
        binding.managerImage.setImageResource(R.drawable.question_mark)
        binding.managerName.text = "연결되지 않음"
        binding.managerEmailAddress.visibility = View.INVISIBLE

    }

    override fun onResume() {
        super.onResume()

        binding.logoutButton.setOnClickListener { // 로그아웃 버튼

            val userInfo = this.activity?.getSharedPreferences("USER_INFO", 0)

            userInfo?.edit()?.putBoolean("AUTO_LOGIN", false)?.apply() // 자동 로그인 해제

            val user = this.activity?.getSharedPreferences("${userInfo?.getString("USING_USER_PHONE_NUMBER","")}",0)

            user?.edit()?.apply { // 진단 내역 데이터, 그래프 점수, 이름 저장
                val score = this@MyPageFragment.activity?.getSharedPreferences("SCORE", 0)
                val intuitionScore = score?.getFloat("INTUITION_SCORE", 0f)
                val analysisScore = score?.getFloat("ANALYSIS_SCORE", 0f)
                val calculationScore = score?.getFloat("CALCULATION_SCORE", 0f)
                val memoryScore = score?.getFloat("MEMORY_SCORE", 0f)
                val perceptionScore = score?.getFloat("PERCEPTION_SCORE", 0f)

                val diagnosisResult = this@MyPageFragment.activity?.getSharedPreferences("DIAGNOSIS_RESULT",0)
                val diagnosisNum = diagnosisResult?.getInt("NUMBER", 0)

                val name = userInfo?.getString("USING_USER_NAME", "")

                putFloat("intuitionScore", intuitionScore!!)
                putFloat("analysisScore", analysisScore!!)
                putFloat("calculationScore", calculationScore!!)
                putFloat("memoryScore", memoryScore!!)
                putFloat("perceptionScore", perceptionScore!!)
                putInt("diagnosisNum", diagnosisNum!!)
                putString("name", name)

                for (number in 1..diagnosisNum) {
                    putString("${number}_itemResultTime", diagnosisResult.getString("${number}_ITEM_RESULT_TIME", ""))
                    putInt("${number}_itemPass", diagnosisResult.getInt("${number}_ITEM_PASS", 0))
                    putInt("${number}_itemFail", diagnosisResult.getInt("${number}_ITEM_FAIL", 0))
                }

            }?.apply()

            startActivity(Intent(binding.root.context, LoginActivity::class.java))
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
        barDataSet.color = Color.GREEN
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 15f

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

}