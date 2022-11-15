package team.su.btmxmlversion.models

import com.google.gson.annotations.SerializedName

data class DiagnosisHistory(
    @SerializedName("diagnosisTime") val diagnosisTime: String,
    @SerializedName("fail") val fail: Int,
    @SerializedName("pass") val pass: Int,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("resultNumber") val resultNumber: Int,
)
