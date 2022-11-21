package team.su.btmxmlversion.dto

import com.google.gson.annotations.SerializedName

data class InterlockInfoRequestBody(
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("infirmName") val infirmName: String,
    @SerializedName("email") val email: String,
    @SerializedName("protectorName") val protectorName: String,
    @SerializedName("facilityName") val facilityName: String?,
)
