package team.su.btmxmlversion.ui.main.infirmMain.dementiaDiagnosis

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseActivity
import team.su.btmxmlversion.databinding.ActivityDiagnosisResultPageBinding
import team.su.btmxmlversion.dto.UpdateDiagnosisDementiaRequestBody
import team.su.btmxmlversion.network.CommonDataServiceLocator
import team.su.btmxmlversion.repository.DiagnosisRepository
import team.su.btmxmlversion.repository.InfirmUserRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DiagnosisResultActivity: BaseActivity<ActivityDiagnosisResultPageBinding>(ActivityDiagnosisResultPageBinding::inflate) {

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("BTM_APP",0)
        val uuid = sharedPreferences?.getString("uuid", "").toString()
        val intuitionResult = sharedPreferences.getInt("INTUITION_VALUE", 0) / 2f
        val analysisResult = sharedPreferences.getInt("ANALYSIS_VALUE", 0) / 2f
        val calculationResult = sharedPreferences.getInt("CALCULATION_VALUE", 0) / 2f
        val memoryResult = sharedPreferences.getInt("MEMORY_VALUE", 0) / 2f
        val perceptionResult = sharedPreferences.getInt("PERCEPTION_VALUE", 0) / 2f // 지각 영역 최종 평균 점수
        val passResult = sharedPreferences.getInt("PASS_VALUE", 0)
        val failResult = sharedPreferences.getInt("FAIL_VALUE", 0)
        val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        val result = UpdateDiagnosisDementiaRequestBody(
            phoneNumber = uuid,
            intuitionResult = intuitionResult,
            analysisResult = analysisResult,
            calculationResult = calculationResult,
            memoryResult = memoryResult,
            perceptionResult = perceptionResult,
            diagnosisTime = currentTime,
            passNum = passResult,
            failNum = failResult
        )

        Glide
            .with(this)
            .load(R.raw.diagnosis_result_loading)
            .into(binding.resultLoadingImage)

        asyncJobStart(result)
    }

    private fun asyncJobStart(result: UpdateDiagnosisDementiaRequestBody){
        lifecycleScope.launch {
            val job = async(Dispatchers.IO){
                val response = DiagnosisRepository(CommonDataServiceLocator.diagnosisService)
                    .trySetDiagnosisResult(result)
                runOnUiThread {
                    showCustomToast(response.message)
                }
            }
            val job2 = async(Dispatchers.IO) {
                var addChar = "결과 집계중."
                for (i in 0 .. 2){
                    runOnUiThread {
                        binding.resultCounting.text = addChar
                        addChar += '.'
                    }
                    delay(500)
                }
            }
            job.await()
            job2.await()
            finish()
        }
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

}