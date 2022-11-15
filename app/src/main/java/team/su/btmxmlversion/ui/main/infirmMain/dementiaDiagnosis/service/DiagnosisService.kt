package team.su.btmxmlversion.ui.main.infirmMain.dementiaDiagnosis.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import team.su.btmxmlversion.models.DiagnosisHistoryResponse

interface DiagnosisService {

    @GET("/infirm/diagnosis-history")
    fun diagnosisHistory(
        @Query("phoneNumber") phoneNumber: String,
    ) : Call<DiagnosisHistoryResponse>

}