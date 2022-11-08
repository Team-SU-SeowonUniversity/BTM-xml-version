package team.su.btmxmlversion.main.infirmMain.dementiaDiagnosis

import android.content.Intent
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentDementiaDiagnosisBinding
import team.su.btmxmlversion.main.infirmMain.dementiaDiagnosis.adapter.DiagnosisStatementRvAdapter
import team.su.btmxmlversion.main.infirmMain.quiz.analysis.weather.WeatherActivity
import team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic.ArithmeticActivity
import team.su.btmxmlversion.main.infirmMain.quiz.perception.blinking.BlinkingActivity
import team.su.btmxmlversion.main.infirmMain.quiz.intuition.shadowing.ShadowingActivity
import team.su.btmxmlversion.main.infirmMain.quiz.memory.HwatuCard.HwatuCardActivity

class DementiaDiagnosisFragment:
    BaseFragment<FragmentDementiaDiagnosisBinding>(FragmentDementiaDiagnosisBinding::bind, R.layout.fragment_dementia_diagnosis) {

    private var isDiagnosticExperience: Boolean = false

    override fun onResume() {
        super.onResume()

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

        val perceptionQuiz = listOf( // 지각 영역 퀴즈들
            Intent(binding.root.context, BlinkingActivity::class.java),
        )
        val memoryQuiz = listOf( // 기억 영역 퀴즈들
            Intent(binding.root.context, HwatuCardActivity::class.java),
        )
        val analysisQuiz = listOf( // 분석 영역 퀴즈들
            Intent(binding.root.context, WeatherActivity::class.java),
        )
        val intuitionQuiz = listOf( // 직감 영역 퀴즈들
            Intent(binding.root.context, ShadowingActivity::class.java),
        )
        val calculationQuiz = listOf( // 계산 영역 퀴즈들
            Intent(binding.root.context, ArithmeticActivity::class.java),
        )

        val quizBundle = listOf( // 각 영역별 랜덤으로 2개의 퀴즈를 뽑아 10개의 퀴즈로 묶음
            Intent(binding.root.context, DiagnosisResultActivity::class.java),
            perceptionQuiz.random(),
            perceptionQuiz.random(),
            memoryQuiz.random(),
            memoryQuiz.random(),
            analysisQuiz.random(),
            analysisQuiz.random(),
            intuitionQuiz.random(),
            intuitionQuiz.random(),
            calculationQuiz.random(),
            calculationQuiz.random()
        )

        quizBundle.forEach { intent ->
            intent.putExtra("isDiagnosis", true)
            activity?.startActivity(intent)
        }
    }

}