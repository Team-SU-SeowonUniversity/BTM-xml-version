package team.su.btmxmlversion.dto

import com.google.gson.annotations.SerializedName

data class SignupInfirmRequestBody(
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("name") val infirmName: String
)
