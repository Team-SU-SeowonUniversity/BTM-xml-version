package team.su.btmxmlversion.dto

import com.google.gson.annotations.SerializedName

data class LoginInfirmRequestBody(
    @SerializedName("phoneNumber") val phoneNumber: String
)
