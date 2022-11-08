package team.su.btmxmlversion.main.infirmMain.dementiaDiagnosis

import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseActivity
import team.su.btmxmlversion.databinding.ActivityDiagnosisResultPageBinding

class DiagnosisResultActivity: BaseActivity<ActivityDiagnosisResultPageBinding>(ActivityDiagnosisResultPageBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intuitionValue = getSharedPreferences("BTM_APP", 0).getInt("INTUITION_VALUE", 0) / 2f
        val analysisValue = getSharedPreferences("BTM_APP", 0).getInt("ANALYSIS_VALUE", 0) / 2f
        val calculationValue = getSharedPreferences("BTM_APP", 0).getInt("CALCULATION_VALUE", 0) / 2f
        val memoryValue = getSharedPreferences("BTM_APP", 0).getInt("MEMORY_VALUE", 0) / 2f
        val perceptionValue = getSharedPreferences("BTM_APP", 0).getInt("PERCEPTION_VALUE", 0) / 2f

        val intuitionScore = getSharedPreferences("BTM_APP", 0).getFloat("INTUITION_SCORE", 0f)
        val analysisScore = getSharedPreferences("BTM_APP", 0).getFloat("ANALYSIS_SCORE", 0f)
        val calculationScore = getSharedPreferences("BTM_APP", 0).getFloat("CALCULATION_SCORE", 0f)
        val memoryScore = getSharedPreferences("BTM_APP", 0).getFloat("MEMORY_SCORE", 0f)
        val perceptionScore = getSharedPreferences("BTM_APP", 0).getFloat("PERCEPTION_SCORE", 0f)

        getSharedPreferences("BTM_APP", 0).edit().apply {
            this.putFloat("INTUITION_SCORE", (intuitionValue + intuitionScore) / 2f)
            this.putFloat("ANALYSIS_SCORE", (analysisValue + analysisScore) / 2f)
            this.putFloat("CALCULATION_SCORE", (calculationValue + calculationScore) / 2f)
            this.putFloat("MEMORY_SCORE", (memoryValue + memoryScore) / 2f)
            this.putFloat("PERCEPTION_SCORE", (perceptionValue + perceptionScore) / 2f)
        }.apply()

        Glide
            .with(this)
            .load(R.raw.diagnosis_result_loading)
            .into(binding.resultLoadingImage)

        CoroutineScope(Dispatchers.Main).launch {
            binding.resultCounting.text = "결과 집계중."
            delay(500)
            binding.resultCounting.text = "결과 집계중.."
            delay(500)
            binding.resultCounting.text = "결과 집계중..."
            delay(500)
            finish()
        }

    }

}