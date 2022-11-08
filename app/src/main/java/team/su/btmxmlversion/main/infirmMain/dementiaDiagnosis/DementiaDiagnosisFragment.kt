package team.su.btmxmlversion.main.infirmMain.dementiaDiagnosis

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import team.su.btmxmlversion.R
import team.su.btmxmlversion.config.BaseFragment
import team.su.btmxmlversion.databinding.FragmentDementiaDiagnosisBinding
import team.su.btmxmlversion.main.infirmMain.dementiaDiagnosis.adapter.DiagnosisStatementRvAdapter
import team.su.btmxmlversion.main.infirmMain.quiz.analysis.weather.WeatherActivity
import team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic.ArithmeticActivity
import team.su.btmxmlversion.main.infirmMain.quiz.memory.blinking.BlinkingActivity
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.ShadowingActivity

class DementiaDiagnosisFragment:
    BaseFragment<FragmentDementiaDiagnosisBinding>(FragmentDementiaDiagnosisBinding::bind, R.layout.fragment_dementia_diagnosis) {

    private var isDiagnosticExperience: Boolean = false

    override fun onResume() {
        super.onResume()

        val intuitionValue = this.activity?.getSharedPreferences("BTM_APP", 0)?.getInt("INTUITION_VALUE", 0)
        val analysisValue = this.activity?.getSharedPreferences("BTM_APP", 0)?.getInt("ANALYSIS_VALUE", 0)
        val calculationValue = this.activity?.getSharedPreferences("BTM_APP", 0)?.getInt("CALCULATION_VALUE", 0)
        val memoryValue = this.activity?.getSharedPreferences("BTM_APP", 0)?.getInt("MEMORY_VALUE", 0)
        val perceptionValue = this.activity?.getSharedPreferences("BTM_APP", 0)?.getInt("PERCEPTION_VALUE", 0)
        val passValue = this.activity?.getSharedPreferences("BTM_APP", 0)?.getInt("PASS_VALUE", 0)
        val failValue = this.activity?.getSharedPreferences("BTM_APP", 0)?.getInt("FAIL_VALUE", 0)

        Log.d("최종값",
            "분석: $analysisValue, 직감: $intuitionValue, 계산: $calculationValue, 기억: $memoryValue, 지각: $perceptionValue, 맞은갯수: $passValue, 틀린갯수: $failValue")

        when(isDiagnosticExperience) {
            true -> setVisitedUi()
            false -> setDefaultUi()
        }

        binding.diagnosisButton.setOnClickListener {
            startDiagnosis()
        }

        binding.defaultDiagnosisButton.setOnClickListener {
            isDiagnosticExperience = true
            startDiagnosis()
        }

    }

    private fun setDefaultUi() {
        binding.defaultLayout.visibility = View.VISIBLE
        binding.visibleLayout.visibility = View.GONE
        Glide
            .with(this)
            .load(R.raw.diagnosis_gif)
            .into(binding.gifImage)
    }

    private fun setVisitedUi() {
        binding.defaultLayout.visibility = View.GONE
        binding.visibleLayout.visibility = View.VISIBLE
        binding.diagnosisRv.adapter = DiagnosisStatementRvAdapter()
    }

    private fun startDiagnosis() {
        this.activity?.getSharedPreferences("BTM_APP", 0)?.edit().apply {
            this?.putInt("PERCEPTION_VALUE", 0)
            this?.putInt("MEMORY_VALUE", 0)
            this?.putInt("ANALYSIS_VALUE", 0)
            this?.putInt("INTUITION_VALUE", 0)
            this?.putInt("CALCULATION_VALUE", 0)
            this?.putInt("PASS_VALUE", 0)
            this?.putInt("FAIL_VALUE", 0)
        }?.apply()

        val quizBundle = ArrayList<Intent>().apply {
            while (size < 10) {
                add(
                    listOf(
                        Intent(binding.root.context, ShadowingActivity::class.java),
                        Intent(binding.root.context, WeatherActivity::class.java),
                        Intent(binding.root.context, ArithmeticActivity::class.java),
                        Intent(binding.root.context, BlinkingActivity::class.java)
                    ).random()
                )
            }
        }

        quizBundle.forEach { intent ->
            intent.putExtra("isDiagnosis", true)
            activity?.startActivity(intent)
        }
    }

}