package team.su.btmxmlversion.main.infirmMain.dementiaDiagnosis

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseActivity
import team.su.btmxmlversion.databinding.ActivityDiagnosisResultPageBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DiagnosisResultActivity: BaseActivity<ActivityDiagnosisResultPageBinding>(ActivityDiagnosisResultPageBinding::inflate) {

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intuitionValue = getSharedPreferences("BTM_APP", 0).getInt("INTUITION_VALUE", 0) / 2f
        val analysisValue = getSharedPreferences("BTM_APP", 0).getInt("ANALYSIS_VALUE", 0) / 2f
        val calculationValue = getSharedPreferences("BTM_APP", 0).getInt("CALCULATION_VALUE", 0) / 2f
        val memoryValue = getSharedPreferences("BTM_APP", 0).getInt("MEMORY_VALUE", 0) / 2f
        val perceptionValue = getSharedPreferences("BTM_APP", 0).getInt("PERCEPTION_VALUE", 0) / 2f
        val passValue = getSharedPreferences("BTM_APP", 0).getInt("PASS_VALUE", 0)
        val failValue = getSharedPreferences("BTM_APP", 0).getInt("FAIL_VALUE", 0)

        val intuitionScore = getSharedPreferences("SCORE", 0).getFloat("INTUITION_SCORE", 0f)
        val analysisScore = getSharedPreferences("SCORE", 0).getFloat("ANALYSIS_SCORE", 0f)
        val calculationScore = getSharedPreferences("SCORE", 0).getFloat("CALCULATION_SCORE", 0f)
        val memoryScore = getSharedPreferences("SCORE", 0).getFloat("MEMORY_SCORE", 0f)
        val perceptionScore = getSharedPreferences("SCORE", 0).getFloat("PERCEPTION_SCORE", 0f)

        val diagnosisNumber = getSharedPreferences("DIAGNOSIS_RESULT", 0).getInt("NUMBER", 0)
        val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))

        getSharedPreferences("DIAGNOSIS_RESULT", 0).edit().apply {
            this.putInt("NUMBER", diagnosisNumber + 1)
            this.putInt("${diagnosisNumber + 1}_ITEM_PASS", passValue)
            this.putInt("${diagnosisNumber + 1}_ITEM_FAIL", failValue)
            this.putString("${diagnosisNumber + 1}_ITEM_RESULT_TIME", currentTime)
        }.apply()

        getSharedPreferences("SCORE", 0).edit().apply {
            if (intuitionScore == 0f && analysisScore == 0f && calculationScore == 0f && memoryScore == 0f && perceptionScore == 0f) {
                this.putFloat("INTUITION_SCORE", intuitionValue)
                this.putFloat("ANALYSIS_SCORE", analysisValue)
                this.putFloat("CALCULATION_SCORE", calculationValue)
                this.putFloat("MEMORY_SCORE", memoryValue)
                this.putFloat("PERCEPTION_SCORE", perceptionValue)
            } else {
                this.putFloat("INTUITION_SCORE", (intuitionValue + intuitionScore) / 2f)
                this.putFloat("ANALYSIS_SCORE", (analysisValue + analysisScore) / 2f)
                this.putFloat("CALCULATION_SCORE", (calculationValue + calculationScore) / 2f)
                this.putFloat("MEMORY_SCORE", (memoryValue + memoryScore) / 2f)
                this.putFloat("PERCEPTION_SCORE", (perceptionValue + perceptionScore) / 2f)
            }
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

    override fun onBackPressed() {
        //super.onBackPressed()
    }

}