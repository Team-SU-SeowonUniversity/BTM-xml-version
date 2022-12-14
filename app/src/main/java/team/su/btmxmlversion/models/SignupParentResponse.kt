package team.su.btmxmlversion.models

import com.google.gson.annotations.SerializedName

data class SignupParentResponse(
    @SerializedName("result_code") val result_code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String
)
