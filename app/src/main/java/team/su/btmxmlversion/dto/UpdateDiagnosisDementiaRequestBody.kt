package team.su.btmxmlversion.dto

import com.google.gson.annotations.SerializedName

data class UpdateDiagnosisDementiaRequestBody(
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("intuitionResult") val intuitionResult: Float,
    @SerializedName("analysisResult") val analysisResult: Float,
    @SerializedName("calculationResult") val calculationResult: Float,
    @SerializedName("memoryResult") val memoryResult: Float,
    @SerializedName("perceptionResult") val perceptionResult: Float,
    @SerializedName("diagnosisTime") val diagnosisTime: String,
    @SerializedName("passNum") val passNum: Int,
    @SerializedName("failNum") val failNum: Int,
)
