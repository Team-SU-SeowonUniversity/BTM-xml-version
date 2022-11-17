package team.su.btmxmlversion.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.su.btmxmlversion.models.DiagnosisHistoryResponse
import team.su.btmxmlversion.ui.main.infirmMain.dementiaDiagnosis.DementiaDiagnosisCallback
import team.su.btmxmlversion.ui.main.infirmMain.dementiaDiagnosis.service.DiagnosisService

class DiagnosisRepository(
    private val diagnosisService: DiagnosisService
) {

    fun tryLoginInfirm(phoneNumber: String, dementiaDiagnosisCallback: DementiaDiagnosisCallback) {
        diagnosisService.diagnosisHistory(phoneNumber).enqueue(object :
            Callback<DiagnosisHistoryResponse> {
            override fun onResponse(
                call: Call<DiagnosisHistoryResponse>,
                response: Response<DiagnosisHistoryResponse>
            ) {
                val body = requireNotNull(response.body())

                dementiaDiagnosisCallback.getDiagnosisHistory(body)
            }

            override fun onFailure(call: Call<DiagnosisHistoryResponse>, t: Throwable) {
                dementiaDiagnosisCallback.getRetrofitException()
            }
        })
    }

}