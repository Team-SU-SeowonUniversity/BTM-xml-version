package team.su.btmxmlversion.models

import com.google.gson.annotations.SerializedName

data class SignupInstitutionResponse(
    @SerializedName("result_code") val result_code: Int,
    @SerializedName("message") val message: String
)
