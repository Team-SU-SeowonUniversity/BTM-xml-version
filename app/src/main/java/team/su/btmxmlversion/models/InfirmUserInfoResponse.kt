package team.su.btmxmlversion.models

import com.google.gson.annotations.SerializedName

data class InfirmUserInfoResponse(
    @SerializedName("result_code") val result_code: Int,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("name") val name: String,
    @SerializedName("managerEmail") val managerEmail: String?,
    @SerializedName("managerName") val managerName: String?,
    @SerializedName("facilityName") val facilityName: String?,
    @SerializedName("typeScore") val typeScore: TypeScores,
)
