package team.su.btmxmlversion.models

import com.google.gson.annotations.SerializedName

data class DiagnosisHistoryResponse(
    @SerializedName("result") val result: List<DiagnosisHistory>,
    @SerializedName("result_code") val result_code: Int
)
