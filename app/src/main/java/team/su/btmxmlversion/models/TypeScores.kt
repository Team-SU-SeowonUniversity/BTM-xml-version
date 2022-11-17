package team.su.btmxmlversion.models

import com.google.gson.annotations.SerializedName

data class TypeScores(
    @SerializedName("intuitionScore") val intuitionScore: Float,
    @SerializedName("analysisScore") val analysisScore: Float,
    @SerializedName("calculationScore") val calculationScore: Float,
    @SerializedName("memoryScore") val memoryScore: Float,
    @SerializedName("perceptionScore") val perceptionScore: Float,
)
