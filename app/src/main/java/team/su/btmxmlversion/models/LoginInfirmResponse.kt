package team.su.btmxmlversion.models

import com.google.gson.annotations.SerializedName

data class LoginInfirmResponse(
    @SerializedName("result_code") val result_code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("name") val name: String,
)
