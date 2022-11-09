package team.su.btmxmlversion.main.infirmMain.dementiaDiagnosis

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentDementiaDiagnosisBinding
import team.su.btmxmlversion.main.infirmMain.dementiaDiagnosis.adapter.DiagnosisStatementRvAdapter
import team.su.btmxmlversion.main.infirmMain.dementiaDiagnosis.data.DiagnosisStatement
import team.su.btmxmlversion.main.infirmMain.quiz.analysis.weather.WeatherActivity
import team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic.ArithmeticActivity
import team.su.btmxmlversion.main.infirmMain.quiz.perception.blinking.BlinkingActivity
import team.su.btmxmlversion.main.infirmMain.quiz.intuition.shadowing.ShadowingActivity
import team.su.btmxmlversion.main.infirmMain.quiz.memory.HwatuCard.HwatuCardActivity

class DementiaDiagnosisFragment:
    BaseFragment<FragmentDementiaDiagnosisBinding>(FragmentDementiaDiagnosisBinding::bind, R.layout.fragment_dementia_diagnosis) {

    override fun onResume() {
        super.onResume()
        val sharedPreferencesBTMAPP = this.activity?.getSharedPreferences("BTM_APP",0)
        val diagnosisResult = this.activity?.getSharedPreferences("DIAGNOSIS_RESULT",0)

        when(diagnosisResult?.getInt("NUMBER",0)) {
            0 -> {
                setDefaultUi()
            }
            else -> {
                setVisitedUi()
            }
        }

        binding.diagnosisButton.setOnClickListener {
            startDiagnosis(sharedPreferences = sharedPreferencesBTMAPP)
        }

        binding.defaultDiagnosisButton.setOnClickListener {
            sharedPreferencesBTMAPP?.edit()?.putBoolean("isFirstDiagnosis",true)?.apply()
            startDiagnosis(sharedPreferences = sharedPreferencesBTMAPP)
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
        binding.diagnosisRv.adapter = DiagnosisStatementRvAdapter(setAddUserData())
    }

    private fun setAddUserData(): List<DiagnosisStatement> {
        val item = arrayListOf<DiagnosisStatement>()

        val numberOfItems = this.activity?.getSharedPreferences("DIAGNOSIS_RESULT",0)?.getInt("NUMBER", 0)

        for (number in 1..numberOfItems!!) {
            item.add(
                DiagnosisStatement(
                    resultNumber = number,
                    diagnosisTime = this.activity?.getSharedPreferences("DIAGNOSIS_RESULT",0)?.getString("${number}_ITEM_RESULT_TIME", ""),
                    pass = this.activity?.getSharedPreferences("DIAGNOSIS_RESULT",0)?.getInt("${number}_ITEM_PASS", 0),
                    fail = this.activity?.getSharedPreferences("DIAGNOSIS_RESULT",0)?.getInt("${number}_ITEM_FAIL", 0),
                )
            )
        }

        return item
    }

    @SuppressLint("NewApi")
    private fun startDiagnosis(sharedPreferences: SharedPreferences?) {
        sharedPreferences?.edit().apply {
            this?.putInt("PERCEPTION_VALUE", 0)
            this?.putInt("MEMORY_VALUE", 0)
            this?.putInt("ANALYSIS_VALUE", 0)
            this?.putInt("INTUITION_VALUE", 0)
            this?.putInt("CALCULATION_VALUE", 0)
            this?.putInt("PASS_VALUE", 0)
            this?.putInt("FAIL_VALUE", 0)
        }?.apply()

        val quiz = mapOf(
            "지각" to listOf(
                Intent(binding.root.context, BlinkingActivity::class.java),
            ),
            "기억" to listOf(
                Intent(binding.root.context, HwatuCardActivity::class.java),
            ),
            "직감" to listOf(
                Intent(binding.root.context, ShadowingActivity::class.java),
            ),
            "계산" to listOf(
                Intent(binding.root.context, ArithmeticActivity::class.java),
            ),
            "분석" to listOf(
                Intent(binding.root.context, WeatherActivity::class.java),
            ),
        )

        val quizBundle = arrayListOf( // 각 영역별 랜덤으로 2개의 퀴즈를 뽑아 10개의 퀴즈로 묶음
            quiz["지각"]?.random(),
            quiz["지각"]?.random(),
            quiz["직감"]?.random(),
            quiz["직감"]?.random(),
            quiz["분석"]?.random(),
            quiz["분석"]?.random(),
            quiz["계산"]?.random(),
            quiz["계산"]?.random(),
            quiz["기억"]?.random(),
            quiz["기억"]?.random(),
        ).shuffled().toMutableList()
            .apply { add(0, Intent(binding.root.context, DiagnosisResultActivity::class.java)) }
            .toList()

        quizBundle.forEach { intent ->
            intent?.putExtra("isDiagnosis", true)
            activity?.startActivity(intent)
        }
    }

}