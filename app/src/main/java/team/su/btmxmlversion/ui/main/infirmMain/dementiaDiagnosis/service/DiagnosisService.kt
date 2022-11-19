package team.su.btmxmlversion.ui.main.infirmMain.dementiaDiagnosis.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import team.su.btmxmlversion.dto.UpdateDiagnosisDementiaRequestBody
import team.su.btmxmlversion.models.DiagnosisHistoryResponse
import team.su.btmxmlversion.models.UpdateDiagnosisDementiaResponse

interface DiagnosisService {

    @GET("/infirm/diagnosis-history")
    fun diagnosisHistory(
        @Query("phoneNumber") phoneNumber: String,
    ) : Call<DiagnosisHistoryResponse>

    @POST("/infirm/update-diagnosis-dementia")
    suspend fun updateDiagnosisDementia(
        @Body diagnosisResult: UpdateDiagnosisDementiaRequestBody
    ) : UpdateDiagnosisDementiaResponse

}