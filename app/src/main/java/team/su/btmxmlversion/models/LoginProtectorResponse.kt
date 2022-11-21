package team.su.btmxmlversion.models

import com.google.gson.annotations.SerializedName

data class LoginProtectorResponse(
    @SerializedName("result_code") val result_code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("isInstitution") val isInstitution: Boolean,
    @SerializedName("facilityName") val facilityName: String?,
    @SerializedName("representative") val representative: String?,
)
