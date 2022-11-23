package team.su.btmxmlversion.dto

import com.google.gson.annotations.SerializedName

data class EmailChangeRequestBody(
    @SerializedName("email") val email: String,
    @SerializedName("changeEmail") val changeEmail: String
)
