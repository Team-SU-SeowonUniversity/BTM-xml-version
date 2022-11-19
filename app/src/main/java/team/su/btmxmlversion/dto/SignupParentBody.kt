package team.su.btmxmlversion.dto

import com.google.gson.annotations.SerializedName

data class SignupParentBody(
    @SerializedName("email") val email: String,
    @SerializedName("passWard") val passWard: String,
    @SerializedName("name") val name: String,
)
