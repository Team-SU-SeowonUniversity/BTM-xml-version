package team.su.btmxmlversion.ui.main.infirmMain.dementiaDiagnosis

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentDementiaDiagnosisBinding
import team.su.btmxmlversion.models.DiagnosisHistoryResponse
import team.su.btmxmlversion.network.CommonDataServiceLocator
import team.su.btmxmlversion.repository.DiagnosisRepository
import team.su.btmxmlversion.ui.main.infirmMain.dementiaDiagnosis.adapter.DiagnosisStatementRvAdapter
import team.su.btmxmlversion.ui.main.infirmMain.dementiaDiagnosis.data.DiagnosisStatement
import team.su.btmxmlversion.ui.main.infirmMain.quiz.analysis.weather.WeatherActivity
import team.su.btmxmlversion.ui.main.infirmMain.quiz.calculation.arithmetic.ArithmeticActivity
import team.su.btmxmlversion.ui.main.infirmMain.quiz.intuition.shadowing.ShadowingActivity
import team.su.btmxmlversion.ui.main.infirmMain.quiz.memory.HwatuCard.HwatuCardActivity
import team.su.btmxmlversion.ui.main.infirmMain.quiz.perception.blinking.BlinkingActivity

class DementiaDiagnosisFragment :
    BaseFragment<FragmentDementiaDiagnosisBinding>(
        FragmentDementiaDiagnosisBinding::bind,
        R.layout.fragment_dementia_diagnosis
    ), DementiaDiagnosisCallback
{
    private var sharedPreferencesBTMAPP: SharedPreferences? = null

    override fun onResume() {
        super.onResume()

        sharedPreferencesBTMAPP = this.activity?.getSharedPreferences("BTM_APP", 0)
        val usingPhoneNumber = sharedPreferencesBTMAPP?.getString("uuid", "").toString()

        DiagnosisRepository(CommonDataServiceLocator.diagnosisService)
            .tryGetDiagnosisHistory(usingPhoneNumber, this)

        binding.diagnosisButton.setOnClickListener {
            startDiagnosis(sharedPreferences = sharedPreferencesBTMAPP)
        }

        binding.defaultDiagnosisButton.setOnClickListener {
            startDiagnosis(sharedPreferences = sharedPreferencesBTMAPP)
        }
    }

    private fun setAddDiagnosisStatementData(response: DiagnosisHistoryResponse): List<DiagnosisStatement> {
        val item = arrayListOf<DiagnosisStatement>()
        val result = response.result

        for (i in response.result.indices) {
            item.add(
                DiagnosisStatement(
                    resultNumber = result[i].resultNumber,
                    diagnosisTime = result[i].diagnosisTime,
                    pass = result[i].pass,
                    fail = result[i].fail,
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
            "??????" to listOf(
                Intent(binding.root.context, BlinkingActivity::class.java),
            ),
            "??????" to listOf(
                Intent(binding.root.context, HwatuCardActivity::class.java),
            ),
            "??????" to listOf(
                Intent(binding.root.context, ShadowingActivity::class.java),
            ),
            "??????" to listOf(
                Intent(binding.root.context, ArithmeticActivity::class.java),
            ),
            "??????" to listOf(
                Intent(binding.root.context, WeatherActivity::class.java),
            ),
        )

        val quizBundle = arrayListOf(
            // ??? ????????? ???????????? 2?????? ????????? ?????? 10?????? ????????? ??????
            quiz["??????"]?.random(),
            quiz["??????"]?.random(),
            quiz["??????"]?.random(),
            quiz["??????"]?.random(),
            quiz["??????"]?.random(),
            quiz["??????"]?.random(),
            quiz["??????"]?.random(),
            quiz["??????"]?.random(),
            quiz["??????"]?.random(),
            quiz["??????"]?.random(),
        ).shuffled().toMutableList()
            .apply { add(0, Intent(binding.root.context, DiagnosisResultActivity::class.java)) }
            .toList()

        quizBundle.forEach { intent ->
            intent?.putExtra("isDiagnosis", true)
            activity?.startActivity(intent)
        }
    }

    override fun getDiagnosisHistory(response: DiagnosisHistoryResponse) {
        showLoadingDialog(binding.root.context)

        when (response.result_code) {
            100 -> {
                binding.defaultLayout.visibility = View.INVISIBLE
                binding.visibleLayout.visibility = View.VISIBLE
                binding.diagnosisRv.adapter =
                    DiagnosisStatementRvAdapter(setAddDiagnosisStatementData(response))
            }
            200 -> {
                binding.defaultLayout.visibility = View.VISIBLE
                binding.visibleLayout.visibility = View.INVISIBLE
                Glide
                    .with(this)
                    .load(R.raw.diagnosis_gif)
                    .into(binding.gifImage)
            }
        }

        dismissLoadingDialog()
    }

    override fun getRetrofitException() {
        showCustomToast("?????? ??????")
    }

}